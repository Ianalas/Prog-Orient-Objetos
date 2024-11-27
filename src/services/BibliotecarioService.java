package services;

import models.Bibliotecario;

public class BibliotecarioService {
    static CadastroLivroService servico = new CadastroLivroService();
    static Bibliotecario bibliotecario = new Bibliotecario();

    public static void receberOpcao(int opcao){

        switch (opcao){
            case 1:
                System.out.println("Listando...");
                servico.listar();
                break;
            case 2:
                System.out.println("Cadastrando...");
                servico.adicionar();
                break;
            case 3:
                System.out.println("Removendo...");
                bibliotecario.remover();
                break;
            case 0:
                System.out.println("Volta...");
                break;
            default:
                System.out.println("\nOpção inválida. Tente novamente.");
        }

    }
}
