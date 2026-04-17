import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Aluno> alunos = new ArrayList<>();
    private static Instrutor instrutor;

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    definirInstrutor();
                    break;
                case 4:
                    visualizarInstrutor();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE ACADEMIA ===");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Listar Alunos");
        System.out.println("3. Definir Instrutor");
        System.out.println("4. Ver Instrutor");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarAluno() {
        System.out.println("\n--- CADASTRAR ALUNO ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        // ✅ corrigido (passando dados)
        Aluno aluno = new Aluno(nome, idade);
        alunos.add(aluno);

        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void listarAlunos() {
        System.out.println("\n--- LISTA DE ALUNOS ---");

        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            for (Aluno a : alunos) {
                System.out.println(a); // agora vai funcionar com toString()
            }
        }
    }

    private static void definirInstrutor() {
        System.out.println("\n--- DEFINIR INSTRUTOR ---");

        System.out.print("Nome do instrutor: ");
        String nome = scanner.nextLine();

        // ✅ corrigido (passando nome)
        instrutor = new Instrutor(nome);

        System.out.println("Instrutor definido com sucesso!");
    }

    private static void visualizarInstrutor() {
        System.out.println("\n--- INSTRUTOR ---");

        if (instrutor == null) {
            System.out.println("Nenhum instrutor definido.");
        } else {
            System.out.println(instrutor);
        }
    }
}