/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilda.handler;

import com.guilda.dao.MissaoDao;
import com.guilda.model.Missao;
import com.guilda.util.JsonUtil;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.util.List;

/**
 *
 * @author nmare
 */
public class MissaoHandler {
    private MissaoDao dao = new MissaoDao();

    public void handle(HttpExchange exchange, String metodo, String[] path) throws IOException {
        try {
            if (metodo.equals("GET")) {
                if (path.length == 2) {
                    listarTodas(exchange);
                } else if (path.length == 3) {
                    int id = Integer.parseInt(path[2]);
                    buscarPorId(exchange, id);
                }
            } else if (metodo.equals("POST")) {
                criar(exchange);
            } else if (metodo.equals("PUT")) {
                int id = Integer.parseInt(path[2]);
                atualizar(exchange, id);
            } else if (metodo.equals("DELETE")) {
                int id = Integer.parseInt(path[2]);
                deletar(exchange, id);
            }
        } catch (Exception e) {
            enviarErro(exchange, 500, "Erro no servidor");
        }
    }

    private void listarTodas(HttpExchange exchange) throws IOException {
        try {
            List<Missao> missoes = dao.listarTodas();
            String json = JsonUtil.toJson(missoes);
            enviarResposta(exchange, 200, json);
        } catch (Exception e) {
            enviarErro(exchange, 500, "Erro ao listar missões");
        }
    }

    private void buscarPorId(HttpExchange exchange, int id) throws IOException {
        try {
            Missao missao = dao.buscarPorId(id);
            if (missao != null) {
            String json = JsonUtil.toJson(missao);
            enviarResposta(exchange, 200, json);
            } else {
            enviarErro(exchange, 404, "Missão não encontrada");
            }
        } catch (Exception e) {
            enviarErro(exchange, 500, "Erro ao buscar missão");
        }
    }

    private void criar(HttpExchange exchange) throws IOException {
        try {
            String body = lerCorpo(exchange);
            Missao missao = JsonUtil.fromJson(body, Missao.class);
            dao.criar(missao);
            enviarResposta(exchange, 201, "{\"mensagem\": \"Missão criada com sucesso\"}");
        } catch (Exception e) {
            enviarErro(exchange, 400, "Erro ao criar missão");
        }
    }

    private void atualizar(HttpExchange exchange, int id) throws IOException {
        try {
            String body = lerCorpo(exchange);
            Missao missao = JsonUtil.fromJson(body, Missao.class);
            missao.setId(id);
            dao.atualizar(missao);
            enviarResposta(exchange, 200, "{\"mensagem\": \"Missão atualizada com sucesso\"}");
        } catch (Exception e) {
            enviarErro(exchange, 400, "Erro ao atualizar missão");
        }
    }

    private void deletar(HttpExchange exchange, int id) throws IOException {
        try {
            dao.deletar(id);
            enviarResposta(exchange, 200, "{\"mensagem\": \"Missão removida com sucesso\"}");
        } catch (Exception e) {
            enviarErro(exchange, 500, "Erro ao deletar missão");
        }
    }

    private String lerCorpo(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private void enviarResposta(HttpExchange exchange, int codigo, String resposta) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(codigo, resposta.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(resposta.getBytes());
        os.close();
    }

    private void enviarErro(HttpExchange exchange, int codigo, String mensagem) throws IOException {
        String json = "{\"erro\": \"" + mensagem + "\"}";
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(codigo, json.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(json.getBytes());
        os.close();
    }
}