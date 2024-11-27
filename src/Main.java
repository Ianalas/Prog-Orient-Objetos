import services.AuthService;
//import services.CadastroService;
import services.CadastroService;
import utils.FileUtils;
import utils.MenuUtils;

import java.util.Scanner;

import static utils.FileUtils.lerArquivos;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        FileUtils.criarDiretorio();

        System.out.println(lerArquivos());

        do {
            MenuUtils.exibirMenuPrincipal();
            opcao = MenuUtils.lerOpcao(scanner);

            switch (opcao) {
                case 1:
                    AuthService.logar(scanner);
                    break;
                case 2:
                    CadastroService.cadastrar(scanner);
                    break;
                case 0:
                    System.out.println("\nSaindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
