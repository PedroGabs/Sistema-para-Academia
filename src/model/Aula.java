package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Aula {
    private int id;
    private String nome;
    private LocalDateTime dataHora;
    private int capacidadeMaxima;
    private Instrutor instrutor;
    private List<Aluno> inscritos = new ArrayList<>();

    public Aula(String nome, LocalDateTime dataHora, int capacidadeMaxima, Instrutor instrutor) {
        this.nome = nome;
        this.dataHora = dataHora;
        this.capacidadeMaxima = capacidadeMaxima;
        this.instrutor = instrutor;
    }

    public boolean temVaga() {
        return inscritos.size() < capacidadeMaxima;
    }

    public int vagasDisponiveis() {
        return capacidadeMaxima - inscritos.size();
    }

    public void adicionarAluno(Aluno aluno) {
        inscritos.add(aluno);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public int getCapacidadeMaxima() { return capacidadeMaxima; }
    public void setCapacidadeMaxima(int capacidadeMaxima) { this.capacidadeMaxima = capacidadeMaxima; }

    public Instrutor getInstrutor() { return instrutor; }
    public void setInstrutor(Instrutor instrutor) { this.instrutor = instrutor; }

    public List<Aluno> getInscritos() { return inscritos; }
}