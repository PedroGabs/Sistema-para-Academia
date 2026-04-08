public class Professor {
    private String instrutorNome;
    private String instrutorMatricula;
    private String instrutorCPF;
    private String instrutorCurso;
    private String instrutorContato;



    public void setInstrutorNome( String instrutorNome) {
        if (instrutorNome == null || instrutorNome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }

        if (instrutorNome.length() > 50) {
            throw new IllegalArgumentException("Limite de 50 caracteres excedido!");
        }
        this.instrutorNome = instrutorNome.trim();
    }

    public void setInstrutorMatricula(String instrutorMatricula) {
        if (instrutorMatricula == null || !instrutorMatricula.matches("\\d{8}")) {
            throw new IllegalArgumentException("Matrícula incorreta!");
        }
        this.instrutorMatricula = instrutorMatricula;
    }

    public void setInstrutorCPF(String instrutorCPF) {
        if (instrutorCPF == null) {
            throw new IllegalArgumentException("CPF inválido!");
        }

        String instrutorNumeros = instrutorCPF.replaceAll("\\D", "");

        if (instrutorNumeros.length() != 11) {
            throw new IllegalArgumentException("CPF inválido!");
        }

        this.instrutorCPF = instrutorNumeros;
    }

    public void setInstrutorContato (String instrutorContato) {
        if (instrutorContato == null) {
            throw new IllegalArgumentException("Contato inválido!");
        }

        String instrutorNumeros = instrutorContato.replaceAll("\\D", "");

        if (instrutorNumeros.length() < 10 || instrutorNumeros.length() > 11) {
            throw new IllegalArgumentException("Contato inválido!");
        }

        this.instrutorContato = instrutorNumeros;
    }

    // falta fazer o curso
}
