/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilda.handler;

import com.guilda.dao.MonstroDao;
import com.guilda.model.Monstro;
import com.guilda.util.JsonUtil;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.util.List;

/**
 *
 * @author nmare
 */
public class MonstroHandler {
    private MonstroDao dao = new MonstroDao();

    public void handle(HttpExchange exchange, String metodo, String[] path) throws IOException {
        try {
            if (metodo.equals("GET")) {
                if (path.length == 2) {
                    listarTodos(exchange);
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

    private void listarTodos(HttpExchange exchange) throws IOException {
        try {
            List<Monstro> monstros = dao.listarTodos();
            String json = JsonUtil.toJson(monstros);
            enviarResposta(exchange, 200, json);
        } catch (Exception e) {
            enviarErro(exchange, 500, "Erro ao listar monstros");
        }
    }

    private void buscarPorId(HttpExchange exchange, int id) throws IOException {
        try {
            Monstro monstro = dao.buscarPorId(id);
            if (monstro != null) {
                String json = JsonUtil.toJson(monstro);
                enviarResposta(exchange, 200, json);
            } else {
                enviarErro(exchange, 404, "Monstro não encontrado");
            }
        } catch (Exception e) {
            enviarErro(exchange, 500, "Erro ao buscar monstro");
        }
    }

    private void criar(HttpExchange exchange) throws IOException {
        try {
            String body = lerCorpo(exchange);
            Monstro monstro = JsonUtil.fromJson(body, Monstro.class);
            dao.criar(monstro);
            enviarResposta(exchange, 201, "{\"mensagem\": \"Monstro criado com sucesso\"}");
        } catch (Exception e) {
            e.printStackTrace();
            enviarErro(exchange, 400, "Erro ao criar monstro: " + e.getMessage());
        }
    }

    private void atualizar(HttpExchange exchange, int id) throws IOException {
        try {
            String body = lerCorpo(exchange);
            Monstro monstro = JsonUtil.fromJson(body, Monstro.class);
            monstro.setId(id);
            dao.atualizar(monstro);
            enviarResposta(exchange, 200, "{\"mensagem\": \"Monstro atualizado com sucesso\"}");
        } catch (Exception e) {
            enviarErro(exchange, 400, "Erro ao atualizar monstro");
        }
    }

    private void deletar(HttpExchange exchange, int id) throws IOException {
        try {
            dao.deletar(id);
            enviarResposta(exchange, 200, "{\"mensagem\": \"Monstro removido com sucesso\"}");
        } catch (Exception e) {
            enviarErro(exchange, 500, "Erro ao deletar monstro" + e.getMessage());
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