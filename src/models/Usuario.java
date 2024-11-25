package models;

import classAbstract.Pessoa;

public class Usuario extends Pessoa {

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
        return  super.toCSV(); // ou adicione outros atributos
    }
}
