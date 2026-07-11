/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilda;

import com.guilda.handler.CacadorHandler;
import com.guilda.handler.MonstroHandler;
import com.guilda.handler.MissaoHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author nmare
 */
public class Main {
  public static void main(String[] args) throws IOException {

    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
    CacadorHandler cacadorHandler = new CacadorHandler();
    MonstroHandler monstroHandler = new MonstroHandler();
    MissaoHandler missaoHandler = new MissaoHandler();

    server.createContext("/cacadores", exchange -> {
        String metodo = exchange.getRequestMethod();
        String[] path = exchange.getRequestURI().getPath().split("/");
        cacadorHandler.handle(exchange, metodo, path);
    });

    server.createContext("/monstros", exchange -> {
        String metodo = exchange.getRequestMethod();
        String[] path = exchange.getRequestURI().getPath().split("/");
        monstroHandler.handle(exchange, metodo, path);
    });

    server.createContext("/missoes", exchange -> {
        String metodo = exchange.getRequestMethod();
        String[] path = exchange.getRequestURI().getPath().split("/");
        missaoHandler.handle(exchange, metodo, path);
    });

    server.createContext("/", exchange -> {
        String resposta = "Bem-vindo a API da Guilda dos Caçadores!\n" + "Endpoints disponíveis:\n" +
            "- GET /cacadores\n" + "- POST /cacadores\n" +
            "- GET /monstros\n" + "- POST /monstros\n" + "- GET /missoes\n" + "- POST /missoes\n";
        exchange.sendResponseHeaders(200, resposta.getBytes().length);
        exchange.getResponseBody().write(resposta.getBytes());
        exchange.close();
    });
    server.setExecutor(null);
    server.start();
    System.out.println("Servidor rodando em http://localhost:8080");
  }
}