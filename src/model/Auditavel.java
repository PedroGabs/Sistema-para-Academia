package model;

public interface Auditavel {
    void registrarLog(String acao);
    String obterHistorico();
}