/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilda.model;

/**
 *
 * @author nmare
 */
public class Monstro {
    private int id;
    private String nome;
    private String rankDificuldade;
    private String tipo;
    private int recompensaOuro;

    // Construtor vazio
    public Monstro() {}

    // Construtor com parâmetros
    public Monstro(int id, String nome, String rankDificuldade, String tipo, int recompensaOuro) {
        this.id = id;
        this.nome = nome;
        this.rankDificuldade = rankDificuldade;
        this.tipo = tipo;
        this.recompensaOuro = recompensaOuro;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRankDificuldade() {
        return rankDificuldade;
    }

    public void setRankDificuldade(String rankDificuldade) {
        this.rankDificuldade = rankDificuldade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getRecompensaOuro() {
        return recompensaOuro;
    }

    public void setRecompensaOuro(int recompensaOuro) {
        this.recompensaOuro = recompensaOuro;
    }

    @Override
    public String toString() {
        return "Monstro{" +"id=" + id +", nome='" + nome + '\'' + ", rankDificuldade='" + rankDificuldade + '\'' + ", tipo='" + tipo + '\'' +", recompensaOuro=" + recompensaOuro +'}';
    }
}