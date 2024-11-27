package services;

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
                   break;
               case 3:
                   System.out.println("Devolvendo...");
                   break;
               case 0:
                   System.out.println("Volta...");
                   break;
               default:
                   System.out.println("\nOpção inválida. Tente novamente.");
           }

    }

}
