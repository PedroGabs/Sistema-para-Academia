public class Aluno extends Pessoa{
    private Instrutor instrutor;
    private String curso;

    public Instrutor getInstrutor(){
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor){
        this.instrutor = instrutor;
    }

    public String getCurso(){
        return curso;
    }

    public void setCurso(String curso){
        this.curso = curso;
    }
}