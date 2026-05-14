package model;

import java.util.ArrayList;
import java.util.List;

public class Instrutor extends Pessoa implements Auditavel{
    private List<Aluno> alunos = new ArrayList<>();
    private List<String> historico = new ArrayList<>();
    
    public Instrutor(String nome, int idade) {
        super(nome, idade);
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Total de alunos: " + alunos.size());
    }

    @Override
    public void registrarLog(String acao) {
        historico.add(acao);
        System.out.println("[LOG - Instrutor] " + getNome() + ": " + acao);
    }

    @Override
    public String obterHistorico() {
        return historico.toString();
    }

    @Override
    public void exibirPermissoes() {
        System.out.println("[Instrutor] Permissões: cadastrar alunos, criar treinos, visualizar pagamentos.");
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    @Override
    public String toString() {
        return "Instrutor: " + getNome() +
               " | Total de alunos: " + alunos.size();
    }
}