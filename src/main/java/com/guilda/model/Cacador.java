package com.guilda.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nmare
 */
public class Cacador {
    private int id;
    private String nome;
    private String classe;
    private int nivel;
    private String cidadeOrigem;

    public Cacador() {}

    public Cacador(int id, String nome, String classe, int nivel, String cidadeOrigem) {
        this.id = id;
        this.nome = nome;
        this.classe = classe;
        this.nivel = nivel;
        this.cidadeOrigem = cidadeOrigem;
    }

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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public void setCidadeOrigem(String cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    @Override
    public String toString() {
        return "Cacador{" +"id=" + id +", nome='" + nome + '\'' + ", classe='" + classe + '\'' + ", nivel=" + nivel +", cidadeOrigem='" + cidadeOrigem + '\'' +'}';
    }
}
