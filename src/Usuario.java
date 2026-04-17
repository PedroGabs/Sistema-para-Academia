public class Usuario extends Pessoa {

    public Usuario(String nome2, int i) {
        super(nome2, i);
        //TODO Auto-generated constructor stub
    }

    private String login;
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}