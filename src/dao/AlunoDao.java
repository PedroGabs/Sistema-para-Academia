package dao;

import model.Aluno;
import model.Plano;
import database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

   public void inserir(Aluno aluno) {
    String sql = "INSERT INTO alunos (nome, cpf, endereco, data_nascimento, telefone, email, id_plano) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, aluno.getNome());
        stmt.setString(2, aluno.getCpf());
        stmt.setString(3, aluno.getEndereco());
        stmt.setDate(4, Date.valueOf(aluno.getDataNascimento()));
        stmt.setString(5, aluno.getTel());
        stmt.setString(6, aluno.getEmail());
        stmt.setLong(7, aluno.getIdPlano());

        // DEBUG — mostra exatamente o que vai ser enviado
        System.out.println("DEBUG nome: "          + aluno.getNome());
        System.out.println("DEBUG cpf: "           + aluno.getCpf());
        System.out.println("DEBUG endereco: "      + aluno.getEndereco());
        System.out.println("DEBUG dataNasc: "      + aluno.getDataNascimento());
        System.out.println("DEBUG tel: "           + aluno.getTel());
        System.out.println("DEBUG email: "         + aluno.getEmail());
        System.out.println("DEBUG idPlano: "       + aluno.getIdPlano());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) aluno.setId((long) rs.getInt(1));

        System.out.println("Aluno inserido com sucesso! ID gerado: " + aluno.getId());

    } catch (SQLException e) {
        System.err.println("Erro ao inserir aluno: " + e.getMessage());
        e.printStackTrace();
    }
}

    public List<Aluno> buscarTodos() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT a.*, p.nome AS nome_plano, p.preco FROM alunos a JOIN planos p ON a.id_plano = p.id";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Aluno a = new Aluno(rs.getString("nome"), 0);
                a.setId((long) rs.getInt("id"));
                a.setCpf(rs.getString("cpf"));
                a.setTel(rs.getString("telefone"));
                a.setEmail(rs.getString("email"));
                a.setIdPlano((long) rs.getInt("id_plano"));

                Plano p = new Plano();
                p.setNome(rs.getString("nome_plano"));
                p.setValor(rs.getDouble("preco"));
                a.setPlano(p);

                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar alunos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE alunos SET nome=?, cpf=?, endereco=?, telefone=?, email=?, id_plano=? WHERE id=?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEndereco());
            stmt.setString(4, aluno.getTel());
            stmt.setString(5, aluno.getEmail());
            stmt.setLong(6, aluno.getIdPlano());
            stmt.setLong(7, aluno.getId());
            stmt.executeUpdate();
            System.out.println("Aluno atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM alunos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("Aluno removido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar aluno: " + e.getMessage());
        }
    }
}