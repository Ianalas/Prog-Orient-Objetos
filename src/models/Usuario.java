package models;

import classAbstract.Pessoa;
import exceptions.LivroAlugadoException;
import services.CadastroLivroService;

import java.util.Scanner;

public class Usuario extends Pessoa {
    static Scanner prompt = new Scanner(System.in);

    public static void pedirLivro() throws LivroAlugadoException {
        System.out.println("Escreva o título do livro");
        String titulo = prompt.nextLine();

        System.out.println("Escreva o ano de edição do livro");
        short anoEdit = prompt.nextShort();
        prompt.nextLine();

        CadastroLivroService.alugarLivro(titulo, anoEdit);

    }
    public static void devolverLivro() throws LivroAlugadoException {
        System.out.println("Escreva o título do livro");
        String titulo = prompt.nextLine();

        System.out.println("Escreva o ano de edição do livro");
        short anoEdit = prompt.nextShort();
        prompt.nextLine();

        CadastroLivroService.devolverLivro(titulo, anoEdit);
    }

    @Override
    public String toString() {
        return "Usuario {" +
                "id= " + getId() +
                " Nome= "+ getNome() +
                " Email= " + getEmail() +
                '}';
    }

    @Override
    public String toCSV() {
        return  super.toCSV();
    }
}
