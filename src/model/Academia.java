package model;
import java.util.ArrayList;
import java.util.List;

public class Academia implements Relatorio{

    @Override
    public String gerarResumo() {
        return "Academia | Instrutor: " + (instrutor != null ? instrutor.getNome() : "Nenhum") +
               " | Total de alunos: " + alunos.size();
    }

    @Override
    public void imprimirDetalhes() {
        System.out.println("=== RELATÓRIO DA ACADEMIA ===");
        System.out.println(gerarResumo());
        listarAlunos();
    }

    private Instrutor instrutor;
    private List<Aluno> alunos = new ArrayList<>();

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);

        if (instrutor != null) {
            aluno.setInstrutor(instrutor);
            instrutor.adicionarAluno(aluno);
        }
    }

    public void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        //agr funciona <3
        for (Aluno a : alunos) {
            String plano = (a.getPlano() != null)
                    ? a.getPlano().getNome()
                    : "Sem plano";

            System.out.println(
                a.getNome() + " - Plano: " + plano
            );
        }
    }

    public void mostrarInstrutor() {
        if (instrutor != null) {
            System.out.println(instrutor);
        } else {
            System.out.println("Nenhum instrutor definido.");
        }
    }
}