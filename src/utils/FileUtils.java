package utils;

import exceptions.LivroAlugadoException;
import exceptions.LivroNaoEncontradoException;
import classAbstract.Pessoa;
import classAbstract.Utils;
import models.Livro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileUtils extends Utils {
    private static final String FILE_PATH = "./src/database/cadastros.csv";
    private static final String FILE_PATH_BOOK = "./src/database/livros.csv";
    static List<Livro> livros = new ArrayList<>();

    static {
        livros.addAll(lerArquivos());
    }

    public static void setStatusBook(String titulo, short ano) throws LivroAlugadoException{

        for(Livro livro: livros){
            if(livro.getAno() == ano && livro.getTitulo().equalsIgnoreCase(titulo)){
                if(!livro.isFlag()){
                    throw new LivroAlugadoException("Este livro já está alugado.");
                }
                livro.setFlag(true);
                salvarListaAtualizada(livros);
            }
        }
    }
    public static void setStatusBook(String titulo, short ano, boolean status) throws LivroAlugadoException {

        for(Livro livro: livros){
            if(livro.getAno() == ano && livro.getTitulo().equalsIgnoreCase(titulo)){
                if(!livro.isFlag()){
                    throw new LivroAlugadoException("Este livro Não estava alugado.");
                }
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

    public static void removerLivroPorTitulo(String titulo) throws LivroNaoEncontradoException {

        if (livros.isEmpty()) {
            throw new LivroNaoEncontradoException("A lista de livros está vazia. Certifique-se de carregar os arquivos primeiro.");
        }

        Map<String, Livro> mapLivros = livros.stream()
                .collect(Collectors.toMap(Livro::getTitulo, Function.identity()));

        Livro livroBook = mapLivros.get(titulo);

        if (livroBook != null) {
            livros.remove(livroBook);
            salvarListaAtualizada(livros);
            System.out.println("Livro \"" + livroBook.getTitulo() + "\" removido da lista e arquivo atualizado.");
        } else {
            throw new LivroNaoEncontradoException("Livro com título \"" + titulo + "\" não encontrado.");
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

    private static void salvarListaAtualizada(Map<String, Livro> livrosAtualizados) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH_BOOK, false))) {
            for (Livro livro : livrosAtualizados.values()) {
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
