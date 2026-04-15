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
    }

    public void listarAlunos() {
        for (Aluno a : alunos) {
            System.out.println(
                a.getNome() + " - Plano: " + a.getPlano().getTipo()
            );
        }
    }

    public void mostrarInstrutor() {
        if (instrutor != null) {
            System.out.println("Instrutor: " + instrutor.getNome());
        } else {
            System.out.println("Nenhum instrutor definido.");
        }
    }
}