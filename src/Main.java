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

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static AlunoDao alunoDao = new AlunoDao();
    private static InstrutorDao instrutorDao = new InstrutorDao();
    private static PlanoDAO planoDAO = new PlanoDAO();
    private static InscricaoService inscricaoService = new InscricaoService();

    private static java.util.List<Aula> aulasDisponiveis = new java.util.ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Verificando conexão com o Supabase...");
        try (java.sql.Connection conn = database.Conexao.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ CONEXÃO ESTABELECIDA COM SUCESSO!");
            } else {
                System.err.println("❌ FALHA NA CONEXÃO: Verifique seu db.properties");
            }
        } catch (java.sql.SQLException e) {
            System.err.println("❌ ERRO DE SQL: " + e.getMessage());
        }

        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: cadastrarAluno();       break;
                case 2: listarAlunos();          break;
                case 3: atualizarAluno();       break;
                case 4: deletarAluno();         break;
                case 5: cadastrarInstrutor();   break;
                case 6: listarInstrutores();    break;
                case 7: cadastrarPlano();       break;
                case 8: listarPlanos();          break;
                case 9: criarAula();            break;
                case 10: inscreverEmAula();     break;
                case 0: System.out.println("Encerrando sistema..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE ACADEMIA ===");
        System.out.println("-- ALUNOS --");
        System.out.println("1.  Cadastrar Aluno");
        System.out.println("2.  Listar Alunos");
        System.out.println("3.  Atualizar Aluno");
        System.out.println("4.  Remover Aluno");
        System.out.println("-- INSTRUTORES --");
        System.out.println("5.  Cadastrar Instrutor");
        System.out.println("6.  Listar Instrutores");
        System.out.println("-- PLANOS --");
        System.out.println("7.  Cadastrar Plano");
        System.out.println("8.  Listar Planos");
        System.out.println("-- AULAS --");
        System.out.println("9.  Criar Aula");
        System.out.println("10. Inscrever Aluno em Aula");
        System.out.println("0.  Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarAluno() {
        System.out.println("\n--- CADASTRAR ALUNO ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF (000.000.000-00): ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefone: ");
        String tel = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Data de nascimento (AAAA-MM-DD): ");
        LocalDate dataNasc = LocalDate.parse(scanner.nextLine());

        List<Plano> planos = planoDAO.buscarTodos();
        if (planos.isEmpty()) {
            System.out.println("❌ Nenhum plano cadastrado. Cadastre um plano primeiro.");
            return;
        }

        System.out.println("Planos disponíveis:");
        for (Plano p : planos) {
            System.out.println("  [" + p.getId() + "] " + p.getNome() + " - R$ " + p.getValor());
        }
        System.out.print("ID do plano: ");
        int idPlano = scanner.nextInt();
        scanner.nextLine();

        Aluno aluno = new Aluno(nome, 0);
        aluno.setCpf(cpf);
        aluno.setEmail(email);
        aluno.setTel(tel);
        aluno.setEndereco(endereco);
        aluno.setDataNascimento(dataNasc);
        aluno.setIdPlano((long) idPlano);

        alunoDao.inserir(aluno);
        aluno.registrarLog("Aluno cadastrado no sistema.");
    }

    private static void listarAlunos() {
        System.out.println("\n--- LISTA DE ALUNOS ---");
        List<Aluno> alunos = alunoDao.buscarTodos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (Aluno a : alunos) {
            System.out.println(a);
            System.out.println("Histórico: " + a.obterHistorico());
            System.out.println("-------------------");
        }
    }

    private static void atualizarAluno() {
        System.out.println("\n--- ATUALIZAR ALUNO ---");

        System.out.print("ID do aluno: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Novo email: ");
        String email = scanner.nextLine();

        System.out.print("Novo telefone: ");
        String tel = scanner.nextLine();

        Aluno aluno = new Aluno(nome, 0);
        aluno.setId(id);
        aluno.setEmail(email);
        aluno.setTel(tel);
        aluno.setIdPlano(1L); 

        alunoDao.atualizar(aluno);
        aluno.registrarLog("Dados atualizados.");
    }

    private static void deletarAluno() {
        System.out.println("\n--- REMOVER ALUNO ---");
        System.out.print("ID do aluno: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        alunoDao.deletar(id);
    }

    private static void cadastrarInstrutor() {
        System.out.println("\n--- CADASTRAR INSTRUTOR ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF (000.000.000-00): ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefone: ");
        String tel = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Data de nascimento (AAAA-MM-DD): ");
        LocalDate dataNasc = LocalDate.parse(scanner.nextLine());

        Instrutor instrutor = new Instrutor(nome, 0);
        instrutor.setCpf(cpf);
        instrutor.setEmail(email);
        instrutor.setTel(tel);
        instrutor.setEndereco(endereco);
        instrutor.setDataNascimento(dataNasc);

        instrutorDao.inserir(instrutor);
        instrutor.registrarLog("Instrutor cadastrado no sistema.");
    }

    private static void listarInstrutores() {
        System.out.println("\n--- LISTA DE INSTRUTORES ---");
        List<Instrutor> instrutores = instrutorDao.buscarTodos();
        if (instrutores.isEmpty()) {
            System.out.println("Nenhum instrutor cadastrado.");
            return;
        }
        for (Instrutor i : instrutores) {
            System.out.println(i);
            System.out.println("-------------------");
        }
    }

    private static void cadastrarPlano() {
        System.out.println("\n--- CADASTRAR PLANO ---");

        System.out.print("Nome do plano: ");
        String nome = scanner.nextLine();

        System.out.print("Valor (ex: 99.90): ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Plano plano = new Plano(nome, valor);
        planoDAO.inserir(plano);
    }

    private static void listarPlanos() {
        System.out.println("\n--- LISTA DE PLANOS ---");
        List<Plano> planos = planoDAO.buscarTodos();
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano cadastrado.");
            return;
        }
        for (Plano p : planos) {
            System.out.println(p);
            p.imprimirDetalhes(); 
            System.out.println("-------------------");
        }
    }

    private static void criarAula() {
        System.out.println("\n--- CRIAR AULA ---");

        System.out.print("Nome da aula: ");
        String nome = scanner.nextLine();

        System.out.print("Data e hora (AAAA-MM-DDTHH:MM): ");
        LocalDateTime dataHora = LocalDateTime.parse(scanner.nextLine());

        System.out.print("Capacidade máxima: ");
        int capacidade = scanner.nextInt();
        scanner.nextLine();

        List<Instrutor> instrutores = instrutorDao.buscarTodos();
        if (instrutores.isEmpty()) {
            System.out.println("❌ Nenhum instrutor cadastrado. Cadastre um instrutor primeiro.");
            return;
        }
        System.out.println("Instrutores disponíveis:");
        for (int i = 0; i < instrutores.size(); i++) {
            System.out.println("  [" + i + "] " + instrutores.get(i).getNome());
        }
        System.out.print("Número do instrutor: ");
        int idx = scanner.nextInt();
        scanner.nextLine();

        Aula aula = new Aula(nome, dataHora, capacidade, instrutores.get(idx));
        aulasDisponiveis.add(aula);
        System.out.println("✅ Aula '" + nome + "' criada com " + capacidade + " vagas!");
    }

    private static void inscreverEmAula() {
        System.out.println("\n--- INSCREVER ALUNO EM AULA ---");

        if (aulasDisponiveis.isEmpty()) {
            System.out.println("❌ Nenhuma aula criada ainda.");
            return;
        }

        List<Aluno> alunos = alunoDao.buscarTodos();
        if (alunos.isEmpty()) {
            System.out.println("❌ Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("Alunos:");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println("  [" + i + "] " + alunos.get(i).getNome()
                + " | Plano: " + (alunos.get(i).getPlano() != null ? alunos.get(i).getPlano().getNome() : "Nenhum"));
        }
        System.out.print("Número do aluno: ");
        int idxAluno = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Aulas disponíveis:");
        for (int i = 0; i < aulasDisponiveis.size(); i++) {
            Aula a = aulasDisponiveis.get(i);
            System.out.println("  [" + i + "] " + a.getNome()
                + " | " + a.getDataHora()
                + " | Vagas: " + a.vagasDisponiveis() + "/" + a.getCapacidadeMaxima());
        }
        System.out.print("Número da aula: ");
        int idxAula = scanner.nextInt();
        scanner.nextLine();

        inscricaoService.inscreverAluno(alunos.get(idxAluno), aulasDisponiveis.get(idxAula));
    }
}