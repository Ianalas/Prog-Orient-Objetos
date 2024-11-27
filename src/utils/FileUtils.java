package utils;

import classAbstract.Pessoa;
import models.Livro;
import models.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Livro> lerArquivos() {
        List<Livro> livros = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH_BOOK))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",\\s*");

                if (partes.length == 4) {
                    String titulo = partes[0];
                    String autor = partes[1];
                    short ano = Short.parseShort(partes[2]);
                    boolean disponibilidade = Boolean.parseBoolean(partes[3]);

                    Livro livro = new Livro(titulo, autor, ano, disponibilidade);

                    livros.add(livro);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter o ano ou disponibilidade: " + e.getMessage());
        }

        return livros;
    }

    public static void criarDiretorio() {
        File diretorio = new File("./src/database");
        if (!diretorio.exists() && diretorio.mkdir()) {
            System.out.println("Diretório criado: " + diretorio.getPath());
        }
    }
}
