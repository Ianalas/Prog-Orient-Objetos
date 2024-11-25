package utils;

import classAbstract.Pessoa;
import models.Livro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    private static final String FILE_PATH = "./src/database/cadastros.csv";
    private static final String FILE_PATH_BOOK = "./src/database/livros.csv";

    public static void salvarNoArquivo(Pessoa pessoa) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {

            writer.write(pessoa.toCSV());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
        }
    }

    public static void salvarNoArquivo(Livro livro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_BOOK, true))) {

            writer.write(livro.toCSV());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
        }
    }

    public static void criarDiretorio() {
        File diretorio = new File("./src/database");
        if (!diretorio.exists() && diretorio.mkdir()) {
            System.out.println("Diretório criado: " + diretorio.getPath());
        }
    }
}
