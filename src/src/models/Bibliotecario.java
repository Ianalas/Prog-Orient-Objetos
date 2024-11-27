package models;

import classAbstract.Pessoa;
import interfaces.IGerenciavel;
import utils.FileUtils;

import java.util.Scanner;

public class Bibliotecario extends Pessoa implements IGerenciavel {
    Scanner prompt = new Scanner(System.in);

    private String turno;

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Bibliotecario{" +
                "id= "+ getId() + '\'' +
                "nome= "+ getNome() + '\'' +
                "email= "+ getEmail() + '\'' +
                ", turno='" + turno + '\'' +
                '}';
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + turno; // ou adicione outros atributos
    }

    @Override
    public void adicionar() {

    }

    @Override
    public void remover() {
        System.out.println("Titulo do livro que deseja remover");
        String titulo = prompt.nextLine();
        FileUtils.removerLivroPorTitulo(titulo);
    }

    @Override
    public void listar() {

    }
}
