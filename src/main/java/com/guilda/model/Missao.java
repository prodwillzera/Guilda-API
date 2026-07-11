/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilda.model;

/**
 *
 * @author nmare
 */
public class Missao {
    private int id;
    private String nome;
    private int monstroId;
    private int cacadorId;
    private String status;

    public Missao() {}

    public Missao(int id, String nome, int monstroId, int cacadorId, String status) {
        this.id = id;
        this.nome = nome;
        this.monstroId = monstroId;
        this.cacadorId = cacadorId;
        this.status = status;
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

    public int getMonstroId() {
        return monstroId;
    }

    public void setMonstroId(int monstroId) {
        this.monstroId = monstroId;
    }

    public int getCacadorId() {
        return cacadorId;
    }

    public void setCacadorId(int cacadorId) {
        this.cacadorId = cacadorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Missao{" +"id=" + id +", nome='" + nome + '\'' + ", monstroId=" + monstroId + ", cacadorId=" + cacadorId +", status='" + status + '\'' +'}';
    }
}