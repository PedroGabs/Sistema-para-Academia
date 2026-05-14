package model;

public class Plano implements Relatorio{
    
  @Override
    public String gerarResumo() {
        return "Plano: " + nome + " | Valor: R$ " + valor;
    }

    @Override
    public void imprimirDetalhes() {
        System.out.println("=== DETALHES DO PLANO ===");
        System.out.println(gerarResumo());
    }


    private int id;
    private String nome;
    private double valor;

    public Plano() {}

    public Plano(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    @Override
    public String toString() {
        return "Plano: " + nome + " | Valor: R$ " + valor;
    }
}