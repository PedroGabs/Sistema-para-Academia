package service;

import model.Aluno;
import model.Aula;

import java.util.ArrayList;
import java.util.List;

public class InscricaoService {

    private List<Aula> todasAulas = new ArrayList<>();

    public boolean inscreverAluno(Aluno aluno, Aula aula) {
        System.out.println("\n--- Validando inscrição de " + aluno.getNome() + " na aula: " + aula.getNome() + " ---");

        if (aluno.getPlano() == null) {
            System.out.println("❌ Aluno sem plano ativo. Inscrição negada.");
            aluno.registrarLog("Tentativa de inscrição na aula '" + aula.getNome() + "' negada: sem plano.");
            return false;
        }

        if (!aula.temVaga()) {
            System.out.println("❌ Aula sem vagas disponíveis. Inscrição negada.");
            aluno.registrarLog("Tentativa de inscrição na aula '" + aula.getNome() + "' negada: sem vagas.");
            return false;
        }

        if (temConflitoDeHorario(aluno, aula)) {
            System.out.println("❌ Conflito de horário com outra aula. Inscrição negada.");
            aluno.registrarLog("Tentativa de inscrição na aula '" + aula.getNome() + "' negada: conflito de horário.");
            return false;
        }

        aula.adicionarAluno(aluno);
        todasAulas.add(aula);
        System.out.println("✅ Inscrição realizada com sucesso!");
        System.out.println("   Vagas restantes: " + aula.vagasDisponiveis());
        aluno.registrarLog("Inscrito na aula '" + aula.getNome() + "' em " + aula.getDataHora());
        return true;
    }

    private boolean temConflitoDeHorario(Aluno aluno, Aula novaAula) {
        for (Aula aula : todasAulas) {
            if (aula.getInscritos().contains(aluno)) {
                if (aula.getDataHora().equals(novaAula.getDataHora())) {
                    return true;
                }
            }
        }
        return false;
    }
}