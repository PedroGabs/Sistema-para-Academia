import java.util.ArrayList;
import java.util.List;

public class Instrutor extends Pessoa {
    private List<Aluno> alunos = new ArrayList<>();

    public Instrutor(String nome) {
        super(nome, 0); // ou remove idade se não usar
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