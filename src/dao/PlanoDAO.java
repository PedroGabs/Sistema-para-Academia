package dao;

import model.Plano;
import database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO {

    public void inserir(Plano plano) {
        String sql = "INSERT INTO planos (nome, preco, tipo_plano) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, plano.getNome());
            stmt.setDouble(2, plano.getValor());
            stmt.setString(3, plano.getNome());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) plano.setId(rs.getInt(1));

            System.out.println("Plano inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir plano: " + e.getMessage());
        }
    }

    public List<Plano> buscarTodos() {
        List<Plano> planos = new ArrayList<>();
        String sql = "SELECT * FROM planos";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Plano p = new Plano();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getDouble("preco"));
                planos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar planos: " + e.getMessage());
        }
        return planos;
    }

    public Plano buscarPorId(int id) {
        String sql = "SELECT * FROM planos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Plano p = new Plano();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getDouble("preco"));
                return p;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar plano: " + e.getMessage());
        }
        return null;
    }

    public void atualizar(Plano plano) {
        String sql = "UPDATE planos SET nome = ?, preco = ?, tipo_plano = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, plano.getNome());
            stmt.setDouble(2, plano.getValor());
            stmt.setString(3, plano.getNome());
            stmt.setInt(4, plano.getId());
            stmt.executeUpdate();
            System.out.println("Plano atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar plano: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM planos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Plano removido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar plano: " + e.getMessage());
        }
    }
}