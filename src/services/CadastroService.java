package services;

import exceptions.UsuarioException;
import classAbstract.Pessoa;
import interfaces.IServices;
import models.Bibliotecario;
import models.Usuario;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class CadastroService implements IServices {
    private static final ArrayList<Pessoa> pessoas = new ArrayList<>();

    public static void cadastrar(Scanner scanner) throws UsuarioException {
        System.out.printf(
                "\n========== MENU ==========\n" +
                        "1. Você é um usuário\n" +
                        "2. Você é um bibliotecário\n" +
                        "0. Sair\n" +
                        "==========================\n" +
                        "Escolha uma opção: "
        );

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> cadastrarUsuario(scanner);
            case 2 -> cadastrarBibliotecario(scanner);
            case 0 -> System.out.println("Retornando ao menu principal.");
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void cadastrarUsuario(Scanner scanner) throws UsuarioException {
        try {
            System.out.println("===== Cadastro de Usuário =====");
            Usuario user = new Usuario();

            System.out.print("Nome para cadastro: ");
            user.setNome(scanner.nextLine());

            System.out.print("Email de uso: ");
            user.setEmail(scanner.nextLine());

            String senha;
            do {
                System.out.print("Escolha uma senha com no mínimo 6 dígitos: ");
                senha = scanner.nextLine();
            } while (senha.length() < 6);

            user.setSenha(senha);
            user.setId(pessoas.size());

            pessoas.add(user);
            FileUtils.salvarNoArquivo(user);
            System.out.println("Cadastro de usuário realizado.");
        } catch (Exception e) {
            throw new UsuarioException("Erro no cadastro do usuário: " + e.getMessage());
        }
    }

    private static void cadastrarBibliotecario(Scanner scanner) {
        System.out.println("===== Cadastro de Bibliotecário =====");
        Bibliotecario bibi = new Bibliotecario();

        System.out.print("Nome para cadastro: ");
        bibi.setNome(scanner.nextLine());

        System.out.print("Email de uso: ");
        bibi.setEmail(scanner.nextLine());

        System.out.print("Turno de trabalho: ");
        bibi.setTurno(scanner.nextLine());

        String senha;
        do {
            System.out.print("Escolha uma senha com no mínimo 6 dígitos: ");
            senha = scanner.nextLine();
        } while (senha.length() < 6);

        bibi.setSenha(senha);
        bibi.setId(pessoas.size());

        pessoas.add(bibi);
        FileUtils.salvarNoArquivo(bibi);
        System.out.println("Cadastro de bibliotecário realizado.");
    }

}
