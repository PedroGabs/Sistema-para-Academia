import dao.AlunoDAO;
import dao.InstrutorDAO;
import dao.PlanoDAO;
import model.Aluno;
import model.Instrutor;
import model.Plano;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AlunoDAO alunoDAO = new AlunoDAO();
    private static final InstrutorDAO instrutorDAO = new InstrutorDAO();
    private static final PlanoDAO planoDAO = new PlanoDAO();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInt();

            switch (opcao) {
                case 1 -> menuAlunos();
                case 2 -> menuInstrutores();
                case 3 -> menuPlanos();
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    // ═════════ MENU PRINCIPAL ═════════
    private static void exibirMenu() {
        System.out.println("\n╔══════════════════════════╗");
        System.out.println("║   SISTEMA DE ACADEMIA    ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║ 1. Alunos                ║");
        System.out.println("║ 2. Instrutores           ║");
        System.out.println("║ 3. Planos                ║");
        System.out.println("║ 0. Sair                  ║");
        System.out.println("╚══════════════════════════╝");
        System.out.print("Opção: ");
    }

    // ═════════ ALUNOS ═════════
    private static void menuAlunos() {
        System.out.println("\n--- ALUNOS ---");
        System.out.println("1. Cadastrar  2. Listar  3. Buscar  4. Atualizar  5. Excluir  0. Voltar");
        System.out.print("Opção: ");

        switch (lerInt()) {
            case 1 -> cadastrarAluno();
            case 2 -> listarAlunos();
            case 3 -> buscarAluno();
            case 4 -> atualizarAluno();
            case 5 -> excluirAluno();
        }
    }

    private static void cadastrarAluno() {
        Aluno a = new Aluno();

        System.out.print("Nome: ");
        a.setNome(scanner.nextLine());

        System.out.print("Idade: ");
        a.setIdade(lerInt());

        System.out.print("CPF: ");
        a.setCpf(scanner.nextLine());

        System.out.print("Email: ");
        a.setEmail(scanner.nextLine());

        System.out.print("Tel: ");
        a.setTel(scanner.nextLine());

        System.out.print("ID do instrutor (0 para nenhum): ");
        long idInst = lerLong();
        if (idInst > 0) {
            Instrutor i = instrutorDAO.buscarPorId(idInst);
            if (i != null) a.setInstrutor(i);
        }

        System.out.print("ID do plano (0 para nenhum): ");
        int idPlano = (int) lerLong();
        if (idPlano > 0) {
            Plano p = planoDAO.buscarPorId(idPlano);
            if (p != null) a.setPlano(p);
        }

        alunoDAO.inserir(a);
    }

    private static void listarAlunos() {
        List<Aluno> lista = alunoDAO.listarTodos();
        lista.forEach(a -> {
            a.exibirInfo();
            System.out.println("---");
        });
    }

    private static void buscarAluno() {
        System.out.print("ID: ");
        Aluno a = alunoDAO.buscarPorId(lerLong());

        if (a != null) a.exibirInfo();
        else System.out.println("Aluno não encontrado.");
    }

    private static void atualizarAluno() {
        System.out.print("ID: ");
        Aluno a = alunoDAO.buscarPorId(lerLong());

        if (a == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) a.setNome(nome);

        System.out.print("Nova idade: ");
        String idade = scanner.nextLine();
        if (!idade.isBlank()) a.setIdade(Integer.parseInt(idade));

        alunoDAO.atualizar(a);
    }

    private static void excluirAluno() {
        System.out.print("ID: ");
        alunoDAO.excluir(lerLong());
    }

    // ═════════ INSTRUTORES ═════════
    private static void menuInstrutores() {
        System.out.println("\n--- INSTRUTORES ---");
        System.out.println("1. Cadastrar  2. Listar  3. Buscar  4. Atualizar  5. Excluir  0. Voltar");
        System.out.print("Opção: ");

        switch (lerInt()) {
            case 1 -> cadastrarInstrutor();
            case 2 -> instrutorDAO.listarTodos().forEach(i -> {
                i.exibirInfo();
                System.out.println("---");
            });
            case 3 -> buscarInstrutor();
            case 4 -> atualizarInstrutor();
            case 5 -> excluirInstrutor();
        }
    }

    private static void cadastrarInstrutor() {
        Instrutor i = new Instrutor();

        System.out.print("Nome: ");
        i.setNome(scanner.nextLine());

        System.out.print("Idade: ");
        i.setIdade(lerInt());

        System.out.print("CPF: ");
        i.setCpf(scanner.nextLine());

        System.out.print("Email: ");
        i.setEmail(scanner.nextLine());

        System.out.print("Tel: ");
        i.setTel(scanner.nextLine());

        instrutorDAO.inserir(i);
    }

    private static void buscarInstrutor() {
        System.out.print("ID: ");
        Instrutor i = instrutorDAO.buscarPorId(lerLong());

        if (i != null) i.exibirInfo();
        else System.out.println("Instrutor não encontrado.");
    }

    private static void atualizarInstrutor() {
        System.out.print("ID: ");
        Instrutor i = instrutorDAO.buscarPorId(lerLong());

        if (i == null) {
            System.out.println("Instrutor não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) i.setNome(nome);

        instrutorDAO.atualizar(i);
    }

    private static void excluirInstrutor() {
        System.out.print("ID: ");
        instrutorDAO.excluir(lerLong());
    }

    // ═════════ PLANOS ═════════
    private static void menuPlanos() {
        System.out.println("\n--- PLANOS ---");
        System.out.println("1. Cadastrar  2. Listar  3. Buscar  4. Atualizar  5. Excluir  0. Voltar");
        System.out.print("Opção: ");

        switch (lerInt()) {
            case 1 -> cadastrarPlano();
            case 2 -> planoDAO.listarTodos().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID: ");
                Plano p = planoDAO.buscarPorId((int) lerLong());
                System.out.println(p != null ? p : "Plano não encontrado.");
            }
            case 4 -> atualizarPlano();
            case 5 -> {
                System.out.print("ID: ");
                planoDAO.excluir((int) lerLong());
            }
        }
    }

    private static void cadastrarPlano() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Valor: ");
        double valor = Double.parseDouble(scanner.nextLine());

        planoDAO.inserir(new Plano(nome, valor));
    }

    private static void atualizarPlano() {
        System.out.print("ID: ");
        Plano p = planoDAO.buscarPorId((int) lerLong());

        if (p == null) {
            System.out.println("Plano não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) p.setNome(nome);

        System.out.print("Novo valor: ");
        String valor = scanner.nextLine();
        if (!valor.isBlank()) p.setValor(Double.parseDouble(valor));

        planoDAO.atualizar(p);
    }

    // ═════════ HELPERS ═════════
    private static int lerInt() {
        while (true) {
            try {
                int v = scanner.nextInt();
                scanner.nextLine(); // limpa buffer
                return v;
            } catch (Exception e) {
                System.out.print("Digite um número válido: ");
                scanner.nextLine();
            }
        }
    }

    private static long lerLong() {
        while (true) {
            try {
                long v = scanner.nextLong();
                scanner.nextLine(); // limpa buffer
                return v;
            } catch (Exception e) {
                System.out.print("Digite um número válido: ");
                scanner.nextLine();
            }
        }
    }
}