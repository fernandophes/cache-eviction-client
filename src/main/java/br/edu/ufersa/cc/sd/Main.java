package br.edu.ufersa.cc.sd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ufersa.cc.sd.repositories.OrderRepository;
import br.edu.ufersa.cc.sd.server.ServerSimulator;
import br.edu.ufersa.cc.sd.services.OrderService;
import br.edu.ufersa.cc.sd.services.SocketService;
import br.edu.ufersa.cc.sd.views.HomeView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class.getSimpleName());

    private static final SocketService SOCKET_SERVICE = new SocketService();
    private static final OrderRepository REPOSITORY = new OrderRepository(SOCKET_SERVICE);
    private static final OrderService ORDER_SERVICE = new OrderService(REPOSITORY);

    @Override
    public void start(Stage arg0) throws Exception {
        // Preparar servidor e cliente
        final var server = new ServerSimulator();
        final var client = new HomeView(ORDER_SERVICE);

        // Dividir em threads
        final var serverThread = new Thread(server);
        final var clientThread = new Thread(client);

        // Iniciar servidor e aguardar ele estar ativo
        serverThread.start();
        serverThread.join();

        LOG.info("Servidor ativo");

        // Iniciar cliente e aguardar ele terminar
        clientThread.start();
        clientThread.join();

        // Parar servidor
        server.stop();
    }

}
