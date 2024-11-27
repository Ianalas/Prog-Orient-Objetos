package services;

import models.Usuario;

public class UserService {
    static CadastroLivroService servico = new CadastroLivroService();

    public static void receberOpcao(int opcao){

          switch (opcao){
              case 1:
                  System.out.println("Listando...");
                  servico.listar();
                  break;
              case 2:
                  System.out.println("Pedindo...");
                  Usuario.pedirLivro();
                  break;
              case 3:
                  System.out.println("Devolvendo...");
                  Usuario.devolverLivro();
                  break;
              case 0:
                  System.out.println("Volta...");
                  break;
              default:
                  System.out.println("\nOpção inválida. Tente novamente.");
          }

    }

}
