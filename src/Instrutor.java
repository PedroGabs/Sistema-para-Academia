public class Instrutor extends Pessoa{
    private String curso;
    private Aluno aluno;

    public String getCurso(){
        return curso;
    }

    public void setCurso(String curso){
        this.curso = curso;
    }

    public Aluno getAluno(){
        return aluno;
    }

    public void setAluno(Aluno aluno){
        this.aluno = aluno;
    }
}
