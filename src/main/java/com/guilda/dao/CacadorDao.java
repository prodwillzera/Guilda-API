/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilda.dao;

import com.guilda.model.Cacador;
import com.guilda.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nmare
 */
public class CacadorDao {

    public void criar(Cacador cacador) throws SQLException {
        String sql = "INSERT INTO cacadores (nome, classe, nivel, cidade_origem) VALUES (?, ?, ?, ?)";
    
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cacador.getNome());
            stmt.setString(2, cacador.getClasse());
            stmt.setInt(3, cacador.getNivel());
            stmt.setString(4, cacador.getCidadeOrigem());
            stmt.executeUpdate();
            System.out.println("Cacador criado com sucesso! Pequeno mano");
        }
    }

    public List<Cacador> listarTodos() throws SQLException {
        List<Cacador> cacadores = new ArrayList<>();
        String sql = "SELECT * FROM cacadores";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cacador c = new Cacador(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("classe"),
                    rs.getInt("nivel"),
                    rs.getString("cidade_origem")
                );
            cacadores.add(c);
            }
        }
        return cacadores;
    }

    public Cacador buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM cacadores WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            return new Cacador(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("classe"),
                            rs.getInt("nivel"),
                            rs.getString("cidade_origem")
                            );
                        }
                }
        }
        return null;
    }

    public void atualizar(Cacador cacador) throws SQLException {
        String sql = "UPDATE cacadores SET nome = ?, classe = ?, nivel = ?, cidade_origem = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cacador.getNome());
                stmt.setString(2, cacador.getClasse());
                stmt.setInt(3, cacador.getNivel());
                stmt.setString(4, cacador.getCidadeOrigem());
                stmt.setInt(5, cacador.getId());
                stmt.executeUpdate();
                System.out.println("Cacador atualizado com sucesso!");
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM cacadores WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
                System.out.println("Cacador removido com sucesso! LILBRO");
        }
    }
}