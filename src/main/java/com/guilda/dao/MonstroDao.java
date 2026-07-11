/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilda.dao;

import com.guilda.model.Monstro;
import com.guilda.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nmare
 */
public class MonstroDao {

    public void criar(Monstro monstro) throws SQLException {
        String sql = "INSERT INTO monstros (nome, rank_dificuldade, tipo, recompensa_ouro) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, monstro.getNome());
            stmt.setString(2, monstro.getRankDificuldade());
            stmt.setString(3, monstro.getTipo());
            stmt.setInt(4, monstro.getRecompensaOuro());
            stmt.executeUpdate();
            System.out.println("Monstro criado com sucesso!");
        }
    }

    public List<Monstro> listarTodos() throws SQLException {
        List<Monstro> monstros = new ArrayList<>();
        String sql = "SELECT * FROM monstros";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Monstro m = new Monstro(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("rank_dificuldade"),
                    rs.getString("tipo"),
                    rs.getInt("recompensa_ouro")
                );
                monstros.add(m);
            }
        }
      return monstros;
    }

    public Monstro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM monstros WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Monstro(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("rank_dificuldade"),
                        rs.getString("tipo"),
                        rs.getInt("recompensa_ouro")
                    );
                }
            }
        }
        return null;
    }

    public void atualizar(Monstro monstro) throws SQLException {
        String sql = "UPDATE monstros SET nome = ?, rank_dificuldade = ?, tipo = ?, recompensa_ouro = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, monstro.getNome());
            stmt.setString(2, monstro.getRankDificuldade());
            stmt.setString(3, monstro.getTipo());
            stmt.setInt(4, monstro.getRecompensaOuro());
            stmt.setInt(5, monstro.getId());
            stmt.executeUpdate();
            System.out.println("Monstro atualizado com sucesso!");
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM monstros WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Monstro removido com sucesso!");
        }
    }
}