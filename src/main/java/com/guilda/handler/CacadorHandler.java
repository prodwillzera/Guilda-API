/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilda.handler;

import com.guilda.dao.CacadorDao;
import com.guilda.model.Cacador;
import com.guilda.util.JsonUtil;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.util.List;

/**
 *
 * @author nmare
 */
public class CacadorHandler {
    private CacadorDao dao = new CacadorDao();

    public void handle(HttpExchange exchange, String metodo, String[] path) throws IOException {

        System.out.println("Método recebido: " + metodo);
        System.out.println("Path recebido: " + java.util.Arrays.toString(path));

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
            enviarErro(exchange, 500, "Erro no servidor: " + e.getMessage());
        }
    }

    private void listarTodos(HttpExchange exchange) throws IOException {
        try {
            List<Cacador> cacadores = dao.listarTodos();
            String json = JsonUtil.toJson(cacadores);
            enviarResposta(exchange, 200, json);
        } catch (Exception e) {
            enviarErro(exchange, 500, "Erro ao listar caçadores");
        }
    }

    private void buscarPorId(HttpExchange exchange, int id) throws IOException {
        try {
          Cacador cacador = dao.buscarPorId(id);
          if (cacador != null) {
                String json = JsonUtil.toJson(cacador);
                enviarResposta(exchange, 200, json);
          } else {
                enviarErro(exchange, 404, "Caçador não encontrado");
          }
        } catch (Exception e) {
            enviarErro(exchange, 500, "Erro ao buscar caçador");
        }
    }

    private void criar(HttpExchange exchange) throws IOException {
        try {
            String body = lerCorpo(exchange);
            Cacador cacador = JsonUtil.fromJson(body, Cacador.class);
            dao.criar(cacador);
            enviarResposta(exchange, 201, "{\"mensagem\": \"Caçador criado com sucesso\"}");
        } catch (Exception e) {
            enviarErro(exchange, 400, "Erro ao criar caçador");
        }
    }

    private void atualizar(HttpExchange exchange, int id) throws IOException {
        try {
            String body = lerCorpo(exchange);

            System.out.println("JSON recebido:");
            System.out.println(body);

            Cacador cacador = JsonUtil.fromJson(body, Cacador.class);

            System.out.println("Nome: " + cacador.getNome());
            System.out.println("Classe: " + cacador.getClasse());
            System.out.println("Nivel: " + cacador.getNivel());

            cacador.setId(id);

            dao.atualizar(cacador);

            enviarResposta(exchange, 200, "{\"mensagem\": \"Caçador atualizado com sucesso\"}");

        } catch (Exception e) {
            e.printStackTrace();
            enviarErro(exchange, 400, "Erro ao atualizar caçador: " + e.getMessage());
        }
    }

    private void deletar(HttpExchange exchange, int id) throws IOException {
        try {
            dao.deletar(id);
            enviarResposta(exchange, 200, "{\"mensagem\": \"Caçador removido com sucesso\"}");
        } catch (Exception e) {
            enviarErro(exchange, 500, "Erro ao deletar caçador");
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
