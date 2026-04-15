public class Plano {

    private TipoPlanos tipo;
    private double valor;

    public Plano(TipoPlanos tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public TipoPlanos getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }
}