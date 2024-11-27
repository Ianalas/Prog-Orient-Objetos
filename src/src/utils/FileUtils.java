package utils;

import classAbstract.Pessoa;
import models.Livro;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileUtils {
    private static final String FILE_PATH = "./src/database/cadastros.csv";
    private static final String FILE_PATH_BOOK = "./src/database/livros.csv";
    static List<Livro> livros = new ArrayList<>();

    static {
        livros.addAll(lerArquivos());
    }

    public static void setStatusBook(String titulo, short ano){

        for(Livro livro: livros){
            if(livro.getAno() == ano && livro.getTitulo().equalsIgnoreCase(titulo)){
                livro.setFlag(true);
                salvarListaAtualizada(livros);
            }
        }
    }
    public static void setStatusBook(String titulo, short ano, boolean status){

        for(Livro livro: livros){
            if(livro.getAno() == ano && livro.getTitulo().equalsIgnoreCase(titulo)){
                livro.setFlag(status);
                salvarListaAtualizada(livros);
            }
        }
    }

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
            livros.add(livro);

        } catch (IOException e) {
            System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
        }
    }

    public static void removerLivroPorTitulo(String titulo) {
        Map<String, Livro> mapLivros = livros.stream()
            .collect(
                    Collectors.toMap(Livro::getTitulo, Function.identity())
            );


        if (mapLivros.isEmpty()) {
            System.out.println("Lista de livros está vazia. Certifique-se de carregar os arquivos primeiro.");
            return;
        }
        Livro livroBook = mapLivros.get(titulo);


        if(livroBook != null ) {
            Livro livroRemovido = livros.remove(index);
            System.out.println("Livro \"" + livroRemovido.getTitulo() + "\" removido da lista.");
            salvarListaAtualizada(livros);
            System.out.println("Arquivo atualizado!");
        }else{
            System.out.println("Livro com título \"" + titulo + "\" não encontrado.");
        }
    }

    private static void salvarListaAtualizada(List<Livro> livrosAtualizados) {


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_BOOK, false))) {

            for (Livro livro : livrosAtualizados) {
                writer.write(livro.toCSV());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }

    public static List<Livro> lerArquivos() {
        livros.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH_BOOK))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");

                if (partes.length == 4) {
                    String titulo = partes[0];
                    String autor = partes[1];
                    short ano = Short.parseShort(partes[2]);
                    boolean disponibilidade = Boolean.parseBoolean(partes[3]);

                    Livro livro = new Livro(titulo, autor, ano, disponibilidade);
                    livros.add(livro);
                }
            }
            System.out.println(livros);
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
