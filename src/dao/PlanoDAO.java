package dao;

import database.Conexao;

import model.Plano;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO {

    // ── CREATE ────────────────────────────────────────────────────────────
    public boolean inserir(Plano plano) {
        String sql = "INSERT INTO plano (nome, valor) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, plano.getNome());
            stmt.setDouble(2, plano.getValor());

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) plano.setId(rs.getInt(1));
                }
                System.out.println("✅ Plano inserido! ID: " + plano.getId());
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("❌ Erro ao inserir plano: " + e.getMessage());
            return false;
        }
    }

    // ── READ por ID ───────────────────────────────────────────────────────
    public Plano buscarPorId(int id) {
        String sql = "SELECT * FROM plano WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return extrair(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao buscar plano: " + e.getMessage());
        }
        return null;
    }

    // ── READ todos ────────────────────────────────────────────────────────
    public List<Plano> listarTodos() {
        List<Plano> lista = new ArrayList<>();
        String sql = "SELECT * FROM plano ORDER BY nome";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs   = stmt.executeQuery(sql)) {

            while (rs.next()) lista.add(extrair(rs));
            System.out.println("✅ " + lista.size() + " plano(s) encontrado(s)");

        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar planos: " + e.getMessage());
        }
        return lista;
    }

    // ── UPDATE ────────────────────────────────────────────────────────────
    public boolean atualizar(Plano plano) {
        String sql = "UPDATE plano SET nome = ?, valor = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, plano.getNome());
            stmt.setDouble(2, plano.getValor());
            stmt.setInt   (3, plano.getId());

            boolean ok = stmt.executeUpdate() > 0;
            System.out.println(ok ? "✅ Plano atualizado!" : "⚠ Plano não encontrado.");
            return ok;

        } catch (SQLException e) {
            System.err.println("❌ Erro ao atualizar plano: " + e.getMessage());
            return false;
        }
    }

    // ── DELETE ────────────────────────────────────────────────────────────
    public boolean excluir(int id) {
        String sql = "DELETE FROM plano WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            boolean ok = stmt.executeUpdate() > 0;
            System.out.println(ok ? "✅ Plano excluído!" : "⚠ Plano não encontrado.");
            return ok;

        } catch (SQLException e) {
            System.err.println("❌ Erro ao excluir plano: " + e.getMessage());
            return false;
        }
    }

    // ── helper ────────────────────────────────────────────────────────────
    private Plano extrair(ResultSet rs) throws SQLException {
        Plano p = new Plano();
        p.setId   (rs.getInt   ("id"));
        p.setNome (rs.getString("nome"));
        p.setValor(rs.getDouble("valor"));
        return p;
    }
}
