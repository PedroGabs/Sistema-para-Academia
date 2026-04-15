public class Aluno extends Pessoa{
    private Instrutor instrutor;
    private Plano plano;

    public Instrutor getInstrutor(){
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor){
        this.instrutor = instrutor;
    }

    public Plano getPlano(){
        return plano;
    }

    public void setPlano(Plano plano){
        this.plano = plano;
    }
}