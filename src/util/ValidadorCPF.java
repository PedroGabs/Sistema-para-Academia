package util;

public class ValidadorCPF {

    public static boolean validar(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");
        // Verifica se tem 11 dígitos
        if (cpf.length() != 11) return false;
        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) return false;

        // Loop para calcular os dígitos verificadores
        int soma = 0;
        for (int i = 0; i < 9; i++)
            soma += (cpf.charAt(i) - '0') * (10 - i);
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) primeiroDigito = 0;
        //loop para calcular o segundo dígito 
        soma = 0;
        for (int i = 0; i < 10; i++)
            soma += (cpf.charAt(i) - '0') * (11 - i);
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) segundoDigito = 0;

        return (cpf.charAt(9) - '0') == primeiroDigito
            && (cpf.charAt(10) - '0') == segundoDigito;
    }
}