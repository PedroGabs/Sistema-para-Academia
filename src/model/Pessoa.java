package model;

public abstract class Pessoa {

    private Long   id;
    private String nome;
    private String cpf;
    private String tel;
    private String email;
    private int    idade;
    private Long   idPlano;

    public Pessoa(String nome, int idade) {
        this.nome  = nome;
        this.idade = idade;
    }

    /** Método concreto comum a todas as subclasses */
    public void exibirInfo() {
        System.out.println("Nome:  " + nome);
        System.out.println("Idade: " + idade);
        if (cpf   != null) System.out.println("CPF:   " + cpf);
        if (email != null) System.out.println("Email: " + email);
        if (tel   != null) System.out.println("Tel:   " + tel);
    }

    /** Método abstrato — cada subclasse implementa de forma diferente */
    public abstract void exibirPermissoes();

    // ── getters / setters ──────────────────────────────────────────────────
    public Long   getId()           { return id; }
    public void   setId(Long id)    { this.id = id; }

    public String getNome()               { return nome; }
    public void   setNome(String nome)    { this.nome = nome; }

    public String getCpf()                { return cpf; }
    public void   setCpf(String cpf)      { this.cpf = cpf; }

    public String getTel()                { return tel; }
    public void   setTel(String tel)      { this.tel = tel; }

    public String getEmail()              { return email; }
    public void   setEmail(String email)  { this.email = email; }

    public int  getIdade()                { return idade; }
    public void setIdade(int idade)       { this.idade = idade; }

    public Long getIdPlano()              { return idPlano; }
    public void setIdPlano(Long idPlano)  { this.idPlano = idPlano; }
}
