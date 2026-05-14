package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatador {

    private static final DateTimeFormatter FORMATO_DATA =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static String formatarData(LocalDateTime data) {
        return data.format(FORMATO_DATA);
    }

    public static String formatarDinheiro(double valor) {
        return String.format("R$ %.2f", valor);
    }
}