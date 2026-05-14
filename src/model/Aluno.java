package model;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa implements Auditavel{
    private Instrutor instrutor;
    private Plano plano;
    private List<String> historico = new ArrayList<>();
    public Aluno(String nome, int idade) {
        super(nome, idade);
    }

    @Override
    public void registrarLog(String acao) {
        historico.add(acao);
        System.out.println("[LOG - Aluno] " + getNome() + ": " + acao);
    }

    @Override
    public String obterHistorico() {
        return historico.toString();
    }


    @Override
    public void exibirInfo() {
        super.exibirInfo();
        String nomeInstrutor = (instrutor != null) ? instrutor.getNome() : "Nenhum";
        String nomePlano = (plano != null) ? plano.getNome() : "Sem plano";
        System.out.println("Instrutor: " + nomeInstrutor);
        System.out.println("Plano: " + nomePlano);
    }



    @Override
    public void exibirPermissoes() {
        System.out.println("[Aluno] Permissões: acessar academia, visualizar treinos, pagar mensalidade.");
    }

    // Sobrecarga de método
    public void pagarMensalidade() {
        System.out.println(getNome() + " realizou pagamento padrão.");
    }

    public void pagarMensalidade(double valor) {
        System.out.println(getNome() + " pagou R$ " + valor);
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
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