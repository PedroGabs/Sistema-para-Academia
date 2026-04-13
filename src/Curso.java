import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Curso {

    private String nome;
    private Instrutor instrutor;
    private List<Aluno> alunos = new ArrayList<>();
    private List<Presenca> presencas = new ArrayList<>();

    // adicionar aluno
    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    // registrar presença
    public void registrarPresenca(Aluno aluno, boolean presente) {
        Presenca p = new Presenca(aluno, LocalDate.now(), presente);
        presencas.add(p);
    }

    // listar presença de um aluno
    public void mostrarPresencaAluno(Aluno aluno) {
        for (Presenca p : presencas) {
            if (p.getAluno().equals(aluno)) {
                System.out.println(
                    aluno.getNome() + " - " +
                    p.getData() + " - " +
                    (p.isPresente() ? "Presente" : "Faltou")
                );
            }
        }
    }

    // getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }
}