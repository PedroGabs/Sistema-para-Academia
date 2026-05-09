package model;

import java.util.ArrayList;
import java.util.List;

public class Instrutor extends Pessoa {

    private List<Aluno> alunos = new ArrayList<>();

    public Instrutor() { super("", 0); }

    public Instrutor(String nome, int idade) { super(nome, idade); }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Total de alunos: " + alunos.size());
    }

    @Override
    public void exibirPermissoes() {
        System.out.println("[Instrutor] Permissões: cadastrar alunos, criar treinos, visualizar pagamentos.");
    }

    public List<Aluno> getAlunos()          { return alunos; }
    public void adicionarAluno(Aluno aluno) { alunos.add(aluno); }

    @Override
    public String toString() {
        return "Instrutor: " + getNome() + " | Total de alunos: " + alunos.size();
    }
}
