/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilda.dao;

import com.guilda.model.Missao;
import com.guilda.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nmare
 */
public class MissaoDao {

  // CREATE
    public void criar(Missao missao) throws SQLException {
        String sql = "INSERT INTO missoes (nome, monstro_id, cacador_id, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, missao.getNome());
            stmt.setInt(2, missao.getMonstroId());
            stmt.setInt(3, missao.getCacadorId());
            stmt.setString(4, missao.getStatus());
            stmt.executeUpdate();
            System.out.println("Missao criada com sucesso!");
        }
    }

  // READ - Listar todas
    public List<Missao> listarTodas() throws SQLException {
        List<Missao> missoes = new ArrayList<>();
        String sql = "SELECT * FROM missoes";

        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Missao m = new Missao(
                   rs.getInt("id"),
                   rs.getString("nome"),
                   rs.getInt("monstro_id"),
                   rs.getInt("cacador_id"),
                   rs.getString("status")
                );
                missoes.add(m);
            }
        }
        return missoes;
    }

    public Missao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM missoes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Missao(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("monstro_id"),
                        rs.getInt("cacador_id"),
                        rs.getString("status")
                    );
                }
            }
        }
        return null;
    }

    public void atualizar(Missao missao) throws SQLException {
        String sql = "UPDATE missoes SET nome = ?, monstro_id = ?, cacador_id = ?, status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, missao.getNome());
            stmt.setInt(2, missao.getMonstroId());
            stmt.setInt(3, missao.getCacadorId());
            stmt.setString(4, missao.getStatus());
            stmt.setInt(5, missao.getId());
            stmt.executeUpdate();
            System.out.println("Missao atualizada com sucesso!");
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM missoes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
          stmt.setInt(1, id);
          stmt.executeUpdate();
          System.out.println("Missao removida com sucesso!");
        }
    }
}
