public class Instrutor extends Pessoa{
    private Plano plano;
    private Aluno aluno;

    public Plano getPlano(){
        return plano;
    }

    public void setPlano(Plano plano){
        this.plano = plano;
    }

    public Aluno getAluno(){
        return aluno;
    }

    public void setAluno(Aluno aluno){
        this.aluno = aluno;
    }
}
