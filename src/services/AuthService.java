package services;

import classAbstract.Pessoa;
import utils.FileUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AuthService {
    private static final String FILE_PATH = "./src/database/cadastros.csv";
    private static final String FILE_PATH_BOOK = "./src/database/livros.csv";

    public static boolean logar(Scanner scanner) {
        System.out.print("\nDigite seu nome para logar: ");
        String nome = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");

                if (dados[1].equalsIgnoreCase(nome)) {
                    System.out.print("\nDigite sua senha para logar: ");
                    String senha = scanner.nextLine();

                    if (dados[3].equals(senha)) {
                        System.out.println("Login bem-sucedido! Bem-vindo, " + nome + "!");
                        return true;
                    } else {
                        System.out.println("Senha incorreta. Tente novamente.");
                        return false;
                    }
                }
            }
            System.out.println("Nome não encontrado. Por favor, cadastre-se antes de tentar logar.");
        } catch (IOException e) {
            System.out.println("Erro ao acessar o arquivo: " + e.getMessage());
        }

        return false;
    }

}
