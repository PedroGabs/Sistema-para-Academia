public class Aluno extends Pessoa {

    private Instrutor instrutor;
    private Plano plano;

    // ✅ construtor correto
    public Aluno(String nome, int idade) {
        super(nome, idade);
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;

        // 🔥 garante vínculo dos dois lados (evita duplicação)
        if (instrutor != null && !instrutor.getAlunos().contains(this)) {
            instrutor.adicionarAluno(this);
        }
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    @Override
    public String toString() {
        String nomeInstrutor = (instrutor != null) ? instrutor.getNome() : "Nenhum";
        String nomePlano = (plano != null) ? plano.getNome() : "Sem plano";

        return "Aluno: " + getNome() +
               " | Idade: " + getIdade() +
               " | Instrutor: " + nomeInstrutor +
               " | Plano: " + nomePlano;
    }
}