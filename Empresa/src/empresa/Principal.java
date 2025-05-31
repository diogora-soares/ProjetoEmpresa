package empresa;

import java.util.Scanner;

// Classe principal que contém o método main e gerencia a interação com o usuário.
// Permite gerenciar funcionários e projetos através de um menu interativo.

public class Principal {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int opcaoPrincipal = 0;

        // Loop principal do menu
        while (opcaoPrincipal != 3) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Gerenciar Funcionários");
            System.out.println("2. Gerenciar Projetos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcaoPrincipal = sc.nextInt();
            sc.nextLine(); 

            switch (opcaoPrincipal) {
                case 1:
                    menuFuncionario(sc); // Chama o menu de funcionários
                    break;
                case 2:
                    menuProjeto(sc); // Chama o menu de projetos
                    break;
                case 3:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.err.println("Opção inválida. Tente novamente.");
            }
        }
        sc.close(); // Fecha o scanner ao final
    }

    
    // Menu para gerenciar funcionários.
    // Permite inserir, alterar, excluir, consultar e listar funcionários.
    private static void menuFuncionario(Scanner sc) throws ClassNotFoundException {
        int opcaoFunc = 0;
        while (opcaoFunc != 6) {
            System.out.println("\n--- Menu Funcionário ---");
            System.out.println("1. Inserir novo funcionário");
            System.out.println("2. Alterar funcionário");
            System.out.println("3. Excluir funcionário");
            System.out.println("4. Consultar funcionário por ID");
            System.out.println("5. Listar todos os funcionários");
            System.out.println("6. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoFunc = sc.nextInt();
            sc.nextLine(); 
            System.out.println();

            switch (opcaoFunc) {
                case 1:
                    // Inserir novo funcionário
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Matrícula: ");
                    String matricula = sc.nextLine();
                    System.out.print("Departamento: ");
                    String departamento = sc.nextLine();

                    Funcionario novoFunc = new Funcionario(nome, email, matricula, departamento);
                    if (novoFunc.incluirFuncionario()) {
                        System.out.println("Funcionário inserido com sucesso! ID: " + novoFunc.getId());
                    } else {
                        System.err.println("Erro ao inserir funcionário.");
                    }
                    break;

                case 2:
                    // Alterar funcionário existente
                    System.out.print("ID do funcionário a alterar: ");
                    int idAlt = sc.nextInt();
                    sc.nextLine(); 
                    Funcionario funcAlt = Funcionario.buscarPorId(idAlt);
                    if (funcAlt != null) {
                        System.out.print("Novo nome: ");
                        funcAlt.setNome(sc.nextLine());
                        System.out.print("Novo email: ");
                        funcAlt.setEmail(sc.nextLine());
                        System.out.print("Nova matrícula: ");
                        funcAlt.setMatricula(sc.nextLine());
                        System.out.print("Novo departamento: ");
                        funcAlt.setDepartamento(sc.nextLine());

                        if (funcAlt.alterarFuncionario()) {
                            System.out.println("Funcionário alterado com sucesso!");
                        } else {
                            System.err.println("Erro ao alterar funcionário.");
                        }
                    } else {
                        System.err.println("Funcionário não encontrado.");
                    }
                    break;

                case 3:
                    // Excluir funcionário
                    System.out.print("ID do funcionário a excluir: ");
                    int idExc = sc.nextInt();
                    sc.nextLine(); 
                    Funcionario funcExc = Funcionario.buscarPorId(idExc);
                    if (funcExc != null) {
                        if (funcExc.excluirFuncionario()) {
                            System.out.println("Funcionário excluído com sucesso!");
                        } else {
                            System.err.println("Erro ao excluir funcionário.");
                        }
                    } else {
                        System.err.println("Funcionário não encontrado.");
                    }
                    break;

                case 4:
                    // Consultar funcionário por ID
                    System.out.print("ID do funcionário a consultar: ");
                    int idCons = sc.nextInt();
                    sc.nextLine(); 
                    Funcionario.consultaPorId(idCons);
                    break;

                case 5:
                    // Listar todos os funcionários
                    Funcionario.listarFuncionarios();
                    break;

                case 6:
                    System.out.println("Retornando ao menu principal.");
                    break;

                default:
                    System.err.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Menu para gerenciar projetos.
    // Permite inserir, alterar, excluir, consultar e listar projetos.
    private static void menuProjeto(Scanner sc) throws ClassNotFoundException {
        int opcaoProj = 0;
        while (opcaoProj != 6) {
            System.out.println("\n--- Menu Projeto ---");
            System.out.println("1. Inserir novo projeto");
            System.out.println("2. Alterar projeto");
            System.out.println("3. Excluir projeto");
            System.out.println("4. Consultar projeto por ID");
            System.out.println("5. Listar todos os projetos");
            System.out.println("6. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoProj = sc.nextInt();
            sc.nextLine(); 
            System.out.println();

            switch (opcaoProj) {
                case 1:
                    // Inserir novo projeto
                    System.out.print("Nome do projeto: ");
                    String nome = sc.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = sc.nextLine();
                    System.out.print("ID do funcionário responsável: ");
                    int idFunc = sc.nextInt();
                    sc.nextLine(); 

                    Projeto novoProj = new Projeto(nome, descricao, idFunc);
                    if (novoProj.incluirProjeto()) {
                        System.out.println("Projeto inserido com sucesso! ID: " + novoProj.getId());
                    } else {
                        System.err.println("Erro ao inserir projeto.");
                    }
                    break;

                case 2:
                    // Alterar projeto existente
                    System.out.print("ID do projeto a alterar: ");
                    int idAlt = sc.nextInt();
                    sc.nextLine(); 
                    Projeto projAlt = Projeto.buscarPorId(idAlt);
                    if (projAlt != null) {
                        System.out.print("Novo nome: ");
                        projAlt.setNome(sc.nextLine());
                        System.out.print("Nova descrição: ");
                        projAlt.setDescricao(sc.nextLine());
                        System.out.print("Novo ID do funcionário responsável: ");
                        projAlt.setIdFuncionario(sc.nextInt());
                        sc.nextLine(); 

                        if (projAlt.alterarProjeto()) {
                            System.out.println("Projeto alterado com sucesso!");
                        } else {
                            System.err.println("Erro ao alterar projeto.");
                        }
                    } else {
                        System.err.println("Projeto não encontrado.");
                    }
                    break;

                case 3:
                    // Excluir projeto
                    System.out.print("ID do projeto a excluir: ");
                    int idExc = sc.nextInt();
                    sc.nextLine(); 
                    Projeto projExc = Projeto.buscarPorId(idExc);
                    if (projExc != null) {
                        if (projExc.excluirProjeto()) {
                            System.out.println("Projeto excluído com sucesso!");
                        } else {
                            System.err.println("Erro ao excluir projeto.");
                        }
                    } else {
                        System.err.println("Projeto não encontrado.");
                    }
                    break;

                case 4:
                    // Consultar projeto por ID
                    System.out.print("ID do projeto a consultar: ");
                    int idCons = sc.nextInt();
                    sc.nextLine(); 
                    Projeto.consultaPorId(idCons);
                    break;

                case 5:
                    // Listar todos os projetos
                    Projeto.listarProjetos();
                    break;

                case 6:
                    System.out.println("Retornando ao menu principal.");
                    break;

                default:
                    System.err.println("Opção inválida. Tente novamente.");
            }
        }
    }
}