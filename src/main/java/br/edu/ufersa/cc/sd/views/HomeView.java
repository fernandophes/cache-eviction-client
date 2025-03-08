package br.edu.ufersa.cc.sd.views;

import java.util.Scanner;

import br.edu.ufersa.cc.sd.services.OrderService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HomeView implements Runnable {

    private final OrderService service;

    @Override
    public void run() {
        final var scanner = new Scanner(System.in);
        int option;

        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("GERENCIADOR DE ORDENS DE SERVIÇO\n");
            System.out.println("Confira as opções:");
            System.out.println("1 - Listar todas");
            System.out.println("2 - Criar nova");
            System.out.println("3 - Detalhar");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("6 - Contar");
            System.out.println("0 - Sair\n");

            System.out.print("Digite a opção desejada: ");
            option = scanner.nextInt();
            scanner.nextLine();

            System.out.print("\033[H\033[2J");
            System.out.flush();

            switch (option) {
                case 1:
                    new ListAllView(service).run();
                    break;
                // case 2:
                // new Create().run();
                // break;
                // case 3:
                // new Detail().run();
                // break;
                // case 4:
                // new Update().run();
                // break;
                // case 5:
                // new Delete().run();
                // break;
                // case 6:
                // new Count().run();
                // break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;

            }

            System.out.print("\nPressione ENTER para continuar...");
            scanner.nextLine();
        } while (option != 0);

        scanner.close();
        System.out.println("Sistema encerrado.");
    }

}
