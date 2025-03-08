package br.edu.ufersa.cc.sd;

import br.edu.ufersa.cc.sd.repositories.OrderRepository;
import br.edu.ufersa.cc.sd.server.ServerSimulator;
import br.edu.ufersa.cc.sd.services.OrderService;
import br.edu.ufersa.cc.sd.services.SocketService;
import br.edu.ufersa.cc.sd.views.HomeView;

public class Main {

    private static final SocketService SOCKET_SERVICE = new SocketService();
    private static final OrderRepository REPOSITORY = new OrderRepository(SOCKET_SERVICE);
    private static final OrderService ORDER_SERVICE = new OrderService(REPOSITORY);

    public static void main(String[] args) throws InterruptedException {
        // Preparar servidor e cliente
        final var server = new ServerSimulator();
        final var client = new HomeView(ORDER_SERVICE);

        // Dividir em threads
        final var serverThread = new Thread(server);
        final var clientThread = new Thread(client);

        // Iniciar servidor e aguardar ele estar ativo
        serverThread.start();
        serverThread.join();

        System.out.println("Servidor ativo");

        // Iniciar cliente e aguardar ele terminar
        clientThread.start();
        clientThread.join();

        // Parar servidor
        server.stop();
    }

}
