import java.util.ArrayList;
import java.util.List;

public class Academia {

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