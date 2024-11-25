package models;

import classAbstract.Pessoa;

public class Bibliotecario extends Pessoa {

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
}
