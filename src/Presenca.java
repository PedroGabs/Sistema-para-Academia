import java.time.LocalDate;

public class Presenca {
    private Aluno aluno;
    private LocalDate data;
    private boolean presente;

    public Presenca(Aluno aluno, LocalDate data, boolean presente) {
        this.aluno = aluno;
        this.data = data;
        this.presente = presente;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isPresente() {
        return presente;
    }
}