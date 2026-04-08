public class Aluno {
    private String matricula;
    private String nomeComp;
    private int idade;
    private String cpf;
    private String contato;
    private String email;
    private String curso;
    private Professor professor;

    public Aluno(String matricula, String nomeComp, int idade, String cpf,
                 String contato, String email, String curso, Professor professor) {

        setMatricula(matricula);
        setNomeComp(nomeComp);
        setIdade(idade);
        setCpf(cpf);
        setContato(contato);
        setEmail(email);
        //setCurso(curso);
        setProfessor(professor);
    }

    public void setMatricula(String matricula) {
        if (matricula == null || !matricula.matches("\\d{8}")) {
            throw new IllegalArgumentException("Matrícula incorreta!");
        }
        this.matricula = matricula;
    }

    public void setNomeComp(String nomeComp) {
        if (nomeComp == null || nomeComp.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }

        if (nomeComp.length() > 50) {
            throw new IllegalArgumentException("Limite de 50 caracteres excedido!");
        }
        this.nomeComp = nomeComp.trim();
    }

    public void setIdade(int idade){
        if (idade < 3) {
            throw new IllegalArgumentException("Menores de 3 anos não podem ser matriculados!");
        }

        if (idade > 120) {
            throw new IllegalArgumentException("Acima de 120 só múmia meu amigo, pode não");
        }
        this.idade = idade;
    }

    public void setCpf(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("CPF inválido!");
        }

        String numeros = cpf.replaceAll("\\D", "");

        if (numeros.length() != 11) {
            throw new IllegalArgumentException("CPF inválido!");
        }

        this.cpf = numeros;
    }

    public void setContato (String contato) {
        if (contato == null) {
            throw new IllegalArgumentException("Contato inválido!");
        }

        String numeros = contato.replaceAll("\\D", "");

        if (numeros.length() < 10 || numeros.length() > 11) {
            throw new IllegalArgumentException("Contato inválido!");
        }

        this.contato = numeros;
    }

    public void setEmail (String email) {
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Email inválido!");
        }
        this.email = email;
    }

    // public void setCurso (String curso){
    // }

    public void setProfessor (Professor professor) {
        if (professor == null) {
            throw new IllegalArgumentException("Professor inválido!");
        }
        this.professor = professor;
    }

    public String getMatricula(){
        return matricula;
    }

    public String getNomeComp(){
        return nomeComp;
    }

    public int getIdade(){
        return idade;
    }

    public String getCpf(){
        return cpf;
    }

    public String getContato(){
        return contato;
    }

    public String getEmail(){
        return email;
    }

    public String getCurso(){
        return curso;
    }

    public Professor getProfessor(){
        return professor;
    }

    public boolean maiorIdade(){
        if (idade >= 18) {
            return true;
        } else {
            return false;
        }
    }
}
