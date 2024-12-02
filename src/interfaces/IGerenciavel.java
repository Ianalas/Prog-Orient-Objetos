package interfaces;

import exceptions.LivroNaoEncontradoException;

public interface IGerenciavel {
    public void adicionar();

    public void remover() throws LivroNaoEncontradoException;

    void listar();
}
