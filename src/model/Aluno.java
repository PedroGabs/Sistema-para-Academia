package model;

public class Aluno extends Pessoa {

    private Instrutor instrutor;
    private Plano     plano;

    public Aluno() { super("", 0); }

    public Aluno(String nome, int idade) { super(nome, idade); }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Instrutor: " + (instrutor != null ? instrutor.getNome() : "Nenhum"));
        System.out.println("Plano:     " + (plano     != null ? plano.getNome()     : "Sem plano"));
    }

    @Override
    public void exibirPermissoes() {
        System.out.println("[Aluno] Permissões: acessar academia, visualizar treinos, pagar mensalidade.");
    }

    public void pagarMensalidade() {
        System.out.println(getNome() + " realizou pagamento padrão.");
    }

    public void pagarMensalidade(double valor) {
        System.out.println(getNome() + " pagou R$ " + valor);
    }

    public Instrutor getInstrutor()              { return instrutor; }
    public void      setInstrutor(Instrutor i)   {
        this.instrutor = i;
        if (i != null && !i.getAlunos().contains(this)) i.adicionarAluno(this);
    }

    public Plano getPlano()             { return plano; }
    public void  setPlano(Plano plano)  { this.plano = plano; }

    @Override
    public String toString() {
        return "Aluno: " + getNome()
             + " | Idade: "     + getIdade()
             + " | Instrutor: " + (instrutor != null ? instrutor.getNome() : "Nenhum")
             + " | Plano: "     + (plano     != null ? plano.getNome()     : "Sem plano");
    }
}
