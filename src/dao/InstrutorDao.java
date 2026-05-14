package dao;

import model.Instrutor;
import database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstrutorDao {

    public void inserir(Instrutor instrutor) {
        String sql = "INSERT INTO instrutores (nome, cpf, endereco, data_nascimento, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, instrutor.getNome());
            stmt.setString(2, instrutor.getCpf());
            stmt.setString(3, instrutor.getEndereco());
            stmt.setDate(4, Date.valueOf(instrutor.getDataNascimento()));
            stmt.setString(5, instrutor.getTel());
            stmt.setString(6, instrutor.getEmail());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) instrutor.setId((long) rs.getInt(1));

            System.out.println("Instrutor inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir instrutor: " + e.getMessage());
        }
    }

    public List<Instrutor> buscarTodos() {
        List<Instrutor> lista = new ArrayList<>();
        String sql = "SELECT * FROM instrutores";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Instrutor i = new Instrutor(rs.getString("nome"), 0);
                i.setId((long) rs.getInt("id"));
                i.setCpf(rs.getString("cpf"));
                i.setTel(rs.getString("telefone"));
                i.setEmail(rs.getString("email"));
                lista.add(i);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar instrutores: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Instrutor instrutor) {
        String sql = "UPDATE instrutores SET nome=?, cpf=?, endereco=?, telefone=?, email=? WHERE id=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, instrutor.getNome());
            stmt.setString(2, instrutor.getCpf());
            stmt.setString(3, instrutor.getEndereco());
            stmt.setString(4, instrutor.getTel());
            stmt.setString(5, instrutor.getEmail());
            stmt.setLong(6, instrutor.getId());
            stmt.executeUpdate();
            System.out.println("Instrutor atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar instrutor: " + e.getMessage());
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM instrutores WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("Instrutor removido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar instrutor: " + e.getMessage());
        }
    }
}