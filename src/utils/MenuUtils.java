package utils;
import java.util.Scanner;


public class MenuUtils {

    public static void exibirMenuPrincipal(){

        System.out.printf(
            "\n========== MENU ==========\n" +
                "1. Logar-se no sistema\n" +
                "2. Cadastrar-se no sistema\n" +
                "0. Sair\n" +
                "==========================\n" +
                "Escolha uma opção: "
        );
    }

    public static void exibirMenuUsuario(){

        System.out.printf(
            "\n========== MENU ==========\n" +
                "1. Listar Livros\n" +
                "2. Pedir Livro\n" +
                "3. Devolver Livro\n" +
                "0. Sair\n" +
                "==========================\n" +
                "Escolha uma opção: "
        );
    }

    public static void exibirMenuBibliotecario(){

        System.out.printf(
            "\n========== MENU ==========\n" +
                "1. Cadastrar Livro\n" +
                "2. Remover Livro\n" +
                "3. Listar Livros\n" +
                "0. Sair\n" +
                "==========================\n" +
                "Escolha uma opção: "
        );
    }

    public static int lerOpcao(Scanner scanner){
        try{
            int opcao = scanner.nextInt();
            scanner.nextLine();

            return opcao;
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
        return 0;
    }
}
