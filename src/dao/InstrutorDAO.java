package dao;

import database.Conexao;

import model.Instrutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstrutorDAO {

    // ── CREATE ────────────────────────────────────────────────────────────
    public boolean inserir(Instrutor instrutor) {
        String sql = "INSERT INTO instrutor (nome, idade, cpf, email, tel) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, instrutor.getNome());
            stmt.setInt   (2, instrutor.getIdade());
            stmt.setString(3, instrutor.getCpf());
            stmt.setString(4, instrutor.getEmail());
            stmt.setString(5, instrutor.getTel());

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) instrutor.setId(rs.getLong(1));
                }
                System.out.println("✅ Instrutor inserido! ID: " + instrutor.getId());
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("❌ Erro ao inserir instrutor: " + e.getMessage());
            return false;
        }
    }

    // ── READ por ID ───────────────────────────────────────────────────────
    public Instrutor buscarPorId(long id) {
        String sql = "SELECT * FROM instrutor WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return extrair(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao buscar instrutor: " + e.getMessage());
        }
        return null;
    }

    // ── READ todos ────────────────────────────────────────────────────────
    public List<Instrutor> listarTodos() {
        List<Instrutor> lista = new ArrayList<>();
        String sql = "SELECT * FROM instrutor ORDER BY nome";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs   = stmt.executeQuery(sql)) {

            while (rs.next()) lista.add(extrair(rs));
            System.out.println("✅ " + lista.size() + " instrutor(es) encontrado(s)");

        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar instrutores: " + e.getMessage());
        }
        return lista;
    }

    // ── UPDATE ────────────────────────────────────────────────────────────
    public boolean atualizar(Instrutor instrutor) {
        String sql = "UPDATE instrutor SET nome = ?, idade = ?, cpf = ?, email = ?, tel = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, instrutor.getNome());
            stmt.setInt   (2, instrutor.getIdade());
            stmt.setString(3, instrutor.getCpf());
            stmt.setString(4, instrutor.getEmail());
            stmt.setString(5, instrutor.getTel());
            stmt.setLong  (6, instrutor.getId());

            boolean ok = stmt.executeUpdate() > 0;
            System.out.println(ok ? "✅ Instrutor atualizado!" : "⚠ Instrutor não encontrado.");
            return ok;

        } catch (SQLException e) {
            System.err.println("❌ Erro ao atualizar instrutor: " + e.getMessage());
            return false;
        }
    }

    // ── DELETE ────────────────────────────────────────────────────────────
    public boolean excluir(long id) {
        String sql = "DELETE FROM instrutor WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            boolean ok = stmt.executeUpdate() > 0;
            System.out.println(ok ? "✅ Instrutor excluído!" : "⚠ Instrutor não encontrado.");
            return ok;

        } catch (SQLException e) {
            System.err.println("❌ Erro ao excluir instrutor: " + e.getMessage());
            return false;
        }
    }

    // ── helper ────────────────────────────────────────────────────────────
    private Instrutor extrair(ResultSet rs) throws SQLException {
        Instrutor i = new Instrutor();
        i.setId   (rs.getLong  ("id"));
        i.setNome (rs.getString("nome"));
        i.setIdade(rs.getInt   ("idade"));
        i.setCpf  (rs.getString("cpf"));
        i.setEmail(rs.getString("email"));
        i.setTel  (rs.getString("tel"));
        return i;
    }
}
