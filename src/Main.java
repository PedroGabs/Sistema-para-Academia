import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import dao.AlunoDao;
import dao.InstrutorDao;
import dao.PlanoDAO;
import model.Aluno;
import model.Aula;
import model.Instrutor;
import model.Plano;
import service.InscricaoService;
import util.ValidadorCPF;
import util.Formatador;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static AlunoDao alunoDao = new AlunoDao();
    private static InstrutorDao instrutorDao = new InstrutorDao();
    private static PlanoDAO planoDAO = new PlanoDAO();
    private static InscricaoService inscricaoService = new InscricaoService();
    private static java.util.List<Aula> aulasDisponiveis = new java.util.ArrayList<>();

    static final String RESET  = "\u001B[0m";
    static final String BOLD   = "\u001B[1m";
    static final String CYAN   = "\u001B[36m";
    static final String GREEN  = "\u001B[32m";
    static final String RED    = "\u001B[31m";
    static final String YELLOW = "\u001B[33m";
    static final String BLUE   = "\u001B[34m";
    static final String WHITE  = "\u001B[37m";

    public static void main(String[] args) {
        limparTela();
        printBanner();
        //validando conexão com o banco de dados --- IGNORE ---
        System.out.println(CYAN + "  Verificando conexão com o Supabase..." + RESET);
        try (java.sql.Connection conn = database.Conexao.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                printSucesso("Conexão estabelecida com sucesso!");
            } else {
                printErro("Falha na conexão. Verifique seu db.properties");
            }
        } catch (java.sql.SQLException e) {
            printErro("Erro de SQL: " + e.getMessage());
        }

        pausar();
        //maquina de estado do menu principal
        int opcao;
        do {
            limparTela();
            exibirMenu();
            System.out.print(BOLD + CYAN + "  ▶ Escolha uma opção: " + RESET);
            opcao = scanner.nextInt();
            scanner.nextLine();
            limparTela();

            switch (opcao) {
                case 1:  cadastrarAluno();      break;
                case 2:  listarAlunos();        break;
                case 3:  atualizarAluno();      break;
                case 4:  deletarAluno();        break;
                case 5:  cadastrarInstrutor();  break;
                case 6:  listarInstrutores();   break;
                case 7:  cadastrarPlano();      break;
                case 8:  listarPlanos();        break;
                case 9:  criarAula();           break;
                case 10: inscreverEmAula();     break;
                case 0:
                    limparTela();
                    printBanner();
                    System.out.println(YELLOW + "  Até logo! Sistema encerrado.\n" + RESET);
                    break;
                default:
                    printErro("Opção inválida!");
            }

            if (opcao != 0) pausar();

        } while (opcao != 0);

        scanner.close();
    }

    // ─── BANNER ───────────────────────────────────────────────
    private static void printBanner() {
        System.out.println(CYAN + BOLD);
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║        🏋️  SISTEMA DE ACADEMIA  🏋️        ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        System.out.println(RESET);
    }

    // ─── MENU ─────────────────────────────────────────────────
    private static void exibirMenu() {
        printBanner();
        System.out.println(BOLD + WHITE + "  ┌─────────────────────────────────────────┐");
        System.out.println("  │             👤  ALUNOS                   │");
        System.out.println("  ├─────────────────────────────────────────┤" + RESET);
        System.out.println(WHITE + "  │  " + GREEN + "1" + WHITE + "  ➜  Cadastrar Aluno                    │");
        System.out.println("  │  " + GREEN + "2" + WHITE + "  ➜  Listar Alunos                      │");
        System.out.println("  │  " + GREEN + "3" + WHITE + "  ➜  Atualizar Aluno                    │");
        System.out.println("  │  " + GREEN + "4" + WHITE + "  ➜  Remover Aluno                      │");
        System.out.println(BOLD + "  ├─────────────────────────────────────────┤");
        System.out.println("  │             🎓  INSTRUTORES              │");
        System.out.println("  ├─────────────────────────────────────────┤" + RESET);
        System.out.println(WHITE + "  │  " + BLUE + "5" + WHITE + "  ➜  Cadastrar Instrutor               │");
        System.out.println("  │  " + BLUE + "6" + WHITE + "  ➜  Listar Instrutores                │");
        System.out.println(BOLD + "  ├─────────────────────────────────────────┤");
        System.out.println("  │             💳  PLANOS                   │");
        System.out.println("  ├─────────────────────────────────────────┤" + RESET);
        System.out.println(WHITE + "  │  " + YELLOW + "7" + WHITE + "  ➜  Cadastrar Plano                   │");
        System.out.println("  │  " + YELLOW + "8" + WHITE + "  ➜  Listar Planos                     │");
        System.out.println(BOLD + "  ├─────────────────────────────────────────┤");
        System.out.println("  │             📅  AULAS                    │");
        System.out.println("  ├─────────────────────────────────────────┤" + RESET);
        System.out.println(WHITE + "  │  " + CYAN + "9" + WHITE + "  ➜  Criar Aula                        │");
        System.out.println("  │  " + CYAN + "10" + WHITE + " ➜  Inscrever Aluno em Aula           │");
        System.out.println(BOLD + "  ├─────────────────────────────────────────┤" + RESET);
        System.out.println(WHITE + "  │  " + RED + "0" + WHITE + "  ➜  Sair                              │");
        System.out.println(BOLD + WHITE + "  └─────────────────────────────────────────┘" + RESET);
        System.out.println();
    }

    // ─── HELPERS VISUAIS ──────────────────────────────────────
    private static void printTitulo(String titulo) {
        System.out.println();
        System.out.println(BOLD + CYAN + "  ╔══════════════════════════════════════════╗");
        System.out.printf( "  ║  %-42s║%n", titulo);
        System.out.println("  ╚══════════════════════════════════════════╝" + RESET);
        System.out.println();
    }

    private static void printSucesso(String msg) {
        System.out.println(GREEN + BOLD + "  ✅ " + msg + RESET);
    }

    private static void printErro(String msg) {
        System.out.println(RED + BOLD + "  ❌ " + msg + RESET);
    }

    private static void printAviso(String msg) {
        System.out.println(YELLOW + BOLD + "  ⚠️  " + msg + RESET);
    }

    private static void printSeparador() {
        System.out.println(WHITE + "  ──────────────────────────────────────────" + RESET);
    }

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pausar() {
        System.out.println();
        System.out.print(WHITE + "  Pressione ENTER para continuar..." + RESET);
        scanner.nextLine();
    }

    private static String input(String label) {
        System.out.print(BOLD + "  " + label + RESET);
        return scanner.nextLine();
    }

    // ─── ALUNOS ───────────────────────────────────────────────
    private static void cadastrarAluno() {
        printTitulo("👤 CADASTRAR ALUNO");

        String nome = input("Nome: ");

        String cpf;
        while (true) {
            cpf = input("CPF (000.000.000-00): ");
            if (ValidadorCPF.validar(cpf)) break;
            printErro("CPF inválido! Tente novamente.");
        }

        String email    = input("Email: ");
        String tel      = input("Telefone: ");
        String endereco = input("Endereço: ");

        LocalDate dataNasc;
        while (true) {
            try {
                dataNasc = LocalDate.parse(input("Data de nascimento (AAAA-MM-DD): "));
                break;
            } catch (Exception e) {
                printErro("Data inválida! Use o formato AAAA-MM-DD.");
            }
        }

        List<Plano> planos = planoDAO.buscarTodos();
        if (planos.isEmpty()) {
            printAviso("Nenhum plano cadastrado. Cadastre um plano primeiro.");
            return;
        }

        System.out.println();
        System.out.println(BOLD + "  Planos disponíveis:" + RESET);
        printSeparador();
        for (Plano p : planos) {
            System.out.println("  [" + YELLOW + p.getId() + RESET + "] "
                + p.getNome() + " → " + GREEN + Formatador.formatarDinheiro(p.getValor()) + RESET);
        }
        printSeparador();

        int idPlano = Integer.parseInt(input("ID do plano: "));

        Aluno aluno = new Aluno(nome, 0);
        aluno.setCpf(cpf);
        aluno.setEmail(email);
        aluno.setTel(tel);
        aluno.setEndereco(endereco);
        aluno.setDataNascimento(dataNasc);
        aluno.setIdPlano((long) idPlano);

        alunoDao.inserir(aluno);
        aluno.registrarLog("Aluno cadastrado no sistema.");
        printSucesso("Aluno '" + nome + "' cadastrado com sucesso!");
    }

    private static void listarAlunos() {
        printTitulo("👤 LISTA DE ALUNOS");
        List<Aluno> alunos = alunoDao.buscarTodos();
        if (alunos.isEmpty()) {
            printAviso("Nenhum aluno cadastrado.");
            return;
        }
        for (Aluno a : alunos) {
            printSeparador();
            System.out.println("  " + BOLD + a.getNome() + RESET
                + "  (ID: " + CYAN + a.getId() + RESET + ")");
            System.out.println("  📧 " + a.getEmail()
                + "  |  📞 " + a.getTel());
            System.out.println("  💳 Plano: " + GREEN
                + (a.getPlano() != null ? a.getPlano().getNome() : "Nenhum") + RESET);
            System.out.println("  📋 Histórico: " + a.obterHistorico());
        }
        printSeparador();
    }

    private static void atualizarAluno() {
        printTitulo("✏️  ATUALIZAR ALUNO");

        List<Aluno> alunos = alunoDao.buscarTodos();
        if (alunos.isEmpty()) { printAviso("Nenhum aluno cadastrado."); return; }

        for (Aluno a : alunos)
            System.out.println("  [" + CYAN + a.getId() + RESET + "] " + a.getNome());

        Long id = Long.parseLong(input("\nID do aluno: "));
        String nome     = input("Novo nome: ");
        String email    = input("Novo email: ");
        String tel      = input("Novo telefone: ");
        String endereco = input("Novo endereço: ");

        String cpf;
        while (true) {
            cpf = input("Novo CPF (000.000.000-00): ");
            if (ValidadorCPF.validar(cpf)) break;
            printErro("CPF inválido!");
        }

        List<Plano> planos = planoDAO.buscarTodos();
        printSeparador();
        for (Plano p : planos)
            System.out.println("  [" + YELLOW + p.getId() + RESET + "] "
                + p.getNome() + " → " + GREEN + Formatador.formatarDinheiro(p.getValor()) + RESET);
        printSeparador();

        Long idPlano = Long.parseLong(input("ID do novo plano: "));

        Aluno aluno = new Aluno(nome, 0);
        aluno.setId(id);
        aluno.setEmail(email);
        aluno.setTel(tel);
        aluno.setEndereco(endereco);
        aluno.setCpf(cpf);
        aluno.setIdPlano(idPlano);

        alunoDao.atualizar(aluno);
        aluno.registrarLog("Dados atualizados.");
        printSucesso("Aluno atualizado com sucesso!");
    }

    private static void deletarAluno() {
        printTitulo("🗑️  REMOVER ALUNO");

        List<Aluno> alunos = alunoDao.buscarTodos();
        if (alunos.isEmpty()) { printAviso("Nenhum aluno cadastrado."); return; }

        for (Aluno a : alunos)
            System.out.println("  [" + RED + a.getId() + RESET + "] " + a.getNome());

        Long id = Long.parseLong(input("\nID do aluno a remover: "));
        alunoDao.deletar(id);
        printSucesso("Aluno removido com sucesso!");
    }

    // ─── INSTRUTORES ──────────────────────────────────────────
    private static void cadastrarInstrutor() {
        printTitulo("🎓 CADASTRAR INSTRUTOR");

        String nome = input("Nome: ");

        String cpf;
        while (true) {
            cpf = input("CPF (000.000.000-00): ");
            if (ValidadorCPF.validar(cpf)) break;
            printErro("CPF inválido! Tente novamente.");
        }

        String email    = input("Email: ");
        String tel      = input("Telefone: ");
        String endereco = input("Endereço: ");

        LocalDate dataNasc;
        while (true) {
            try {
                dataNasc = LocalDate.parse(input("Data de nascimento (AAAA-MM-DD): "));
                break;
            } catch (Exception e) {
                printErro("Data inválida! Use o formato AAAA-MM-DD.");
            }
        }

        Instrutor instrutor = new Instrutor(nome, 0);
        instrutor.setCpf(cpf);
        instrutor.setEmail(email);
        instrutor.setTel(tel);
        instrutor.setEndereco(endereco);
        instrutor.setDataNascimento(dataNasc);

        instrutorDao.inserir(instrutor);
        instrutor.registrarLog("Instrutor cadastrado no sistema.");
        printSucesso("Instrutor '" + nome + "' cadastrado com sucesso!");
    }

    private static void listarInstrutores() {
        printTitulo("🎓 LISTA DE INSTRUTORES");
        List<Instrutor> instrutores = instrutorDao.buscarTodos();
        if (instrutores.isEmpty()) { printAviso("Nenhum instrutor cadastrado."); return; }

        for (Instrutor i : instrutores) {
            printSeparador();
            System.out.println("  " + BOLD + i.getNome() + RESET
                + "  (ID: " + CYAN + i.getId() + RESET + ")");
            System.out.println("  📧 " + i.getEmail() + "  |  📞 " + i.getTel());
            System.out.println("  👥 Alunos: " + GREEN + i.getAlunos().size() + RESET);
        }
        printSeparador();
    }

    // ─── PLANOS ───────────────────────────────────────────────
    private static void cadastrarPlano() {
        printTitulo("💳 CADASTRAR PLANO");

        String nome  = input("Nome do plano: ");
        double valor = Double.parseDouble(input("Valor (ex: 99.90): "));

        Plano plano = new Plano(nome, valor);
        planoDAO.inserir(plano);
        printSucesso("Plano '" + nome + "' cadastrado por "
            + Formatador.formatarDinheiro(valor));
    }

    private static void listarPlanos() {
        printTitulo("💳 LISTA DE PLANOS");
        List<Plano> planos = planoDAO.buscarTodos();
        if (planos.isEmpty()) { printAviso("Nenhum plano cadastrado."); return; }

        for (Plano p : planos) {
            printSeparador();
            System.out.println("  " + BOLD + p.getNome() + RESET
                + "  (ID: " + CYAN + p.getId() + RESET + ")");
            System.out.println("  💰 Valor: " + GREEN
                + Formatador.formatarDinheiro(p.getValor()) + RESET);
        }
        printSeparador();
    }

    // ─── AULAS ────────────────────────────────────────────────
    private static void criarAula() {
        printTitulo("📅 CRIAR AULA");

        String nome = input("Nome da aula: ");

        LocalDateTime dataHora;
        while (true) {
            try {
                dataHora = LocalDateTime.parse(input("Data e hora (AAAA-MM-DDTHH:MM): "));
                break;
            } catch (Exception e) {
                printErro("Formato inválido! Use AAAA-MM-DDTHH:MM (ex: 2025-06-10T08:00)");
            }
        }

        int capacidade = Integer.parseInt(input("Capacidade máxima: "));

        List<Instrutor> instrutores = instrutorDao.buscarTodos();
        if (instrutores.isEmpty()) {
            printAviso("Nenhum instrutor cadastrado. Cadastre um instrutor primeiro.");
            return;
        }

        printSeparador();
        for (int i = 0; i < instrutores.size(); i++)
            System.out.println("  [" + BLUE + i + RESET + "] " + instrutores.get(i).getNome());
        printSeparador();

        int idx = Integer.parseInt(input("Número do instrutor: "));

        Aula aula = new Aula(nome, dataHora, capacidade, instrutores.get(idx));
        aulasDisponiveis.add(aula);
        printSucesso("Aula '" + nome + "' criada para "
            + Formatador.formatarData(dataHora) + " com " + capacidade + " vagas!");
    }

    private static void inscreverEmAula() {
        printTitulo("📅 INSCREVER ALUNO EM AULA");

        if (aulasDisponiveis.isEmpty()) { printAviso("Nenhuma aula criada ainda."); return; }

        List<Aluno> alunos = alunoDao.buscarTodos();
        if (alunos.isEmpty()) { printAviso("Nenhum aluno cadastrado."); return; }

        System.out.println(BOLD + "  Alunos:" + RESET);
        printSeparador();
        for (int i = 0; i < alunos.size(); i++)
            System.out.println("  [" + GREEN + i + RESET + "] " + alunos.get(i).getNome()
                + " | Plano: " + (alunos.get(i).getPlano() != null
                    ? alunos.get(i).getPlano().getNome() : YELLOW + "Nenhum" + RESET));
        printSeparador();

        int idxAluno = Integer.parseInt(input("Número do aluno: "));

        System.out.println();
        System.out.println(BOLD + "  Aulas disponíveis:" + RESET);
        printSeparador();
        for (int i = 0; i < aulasDisponiveis.size(); i++) {
            Aula a = aulasDisponiveis.get(i);
            System.out.println("  [" + CYAN + i + RESET + "] " + a.getNome()
                + " | " + Formatador.formatarData(a.getDataHora())
                + " | Vagas: " + GREEN + a.vagasDisponiveis() + "/" + a.getCapacidadeMaxima() + RESET);
        }
        printSeparador();

        int idxAula = Integer.parseInt(input("Número da aula: "));

        inscricaoService.inscreverAluno(alunos.get(idxAluno), aulasDisponiveis.get(idxAula));
    }
}