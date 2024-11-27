package models;

import classAbstract.ItemBiblioteca;
import interfaces.IRegistravel;

public class Livro extends ItemBiblioteca implements IRegistravel {
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Livro(String titulo, String autor, short ano, boolean flag) {
        setTitulo(titulo);
        setAutor(autor);
        setAno(ano);
        this.flag = flag;
    }

    @Override
    public void registrarEntrada() {
        flag = true;
        System.out.println("O livro "+ getTitulo() + " está disponível!" );
    }

    @Override
    public void registrarSaida() {
        flag = false;
        System.out.println("O livro "+ getTitulo() + " NÃO está disponível!" );
    }

    @Override
    public String toString() {
        return super.toString() + ", " + flag + " }";
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + flag;
    }
}