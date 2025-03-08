package br.edu.ufersa.cc.sd.repositories;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import br.edu.ufersa.cc.sd.dto.Request;
import br.edu.ufersa.cc.sd.dto.Response;
import br.edu.ufersa.cc.sd.enums.Operation;
import br.edu.ufersa.cc.sd.enums.ResponseStatus;
import br.edu.ufersa.cc.sd.exceptions.ConnectionException;
import br.edu.ufersa.cc.sd.exceptions.OperationException;
import br.edu.ufersa.cc.sd.models.Order;

public class OrderRepository {

    private static final String HOST = "localhost";
    private static final int PORT = 8484;

    public List<Order> findAll() {
        final var request = new Request(Operation.LIST, null);
        final var response = call(request);

        if (ResponseStatus.ERROR.equals(response.getStatus())) {
            throw new OperationException(response.getMessage());
        }

        return response.getOrders();
    }

    public void save(final Order order) {
        final var request = new Request(Operation.CREATE, order);
        final var response = call(request);

        if (ResponseStatus.ERROR.equals(response.getStatus())) {
            throw new OperationException(response.getMessage());
        }
    }

    public Order findByCode(final Long code) {
        final var request = new Request(Operation.READ, new Order().setCode(code));
        final var response = call(request);

        if (ResponseStatus.ERROR.equals(response.getStatus())) {
            throw new OperationException(response.getMessage());
        }

        return response.getOrders().get(0);
    }

    public void update(final Order order) {
        final var request = new Request(Operation.UPDATE, order);
        final var response = call(request);
        
        if (ResponseStatus.ERROR.equals(response.getStatus())) {
            throw new OperationException(response.getMessage());
        }
    }

    public void delete(final Order order) {
        final var request = new Request(Operation.DELETE, order);
        final var response = call(request);

        if (ResponseStatus.ERROR.equals(response.getStatus())) {
            throw new OperationException(response.getMessage());
        }
    }

    private Response call(final Request request) {
        try (final var socket = new Socket(HOST, PORT)) {
            final var input = new ObjectInputStream(socket.getInputStream());
            final var output = new ObjectOutputStream(socket.getOutputStream());

            output.writeObject(request);
            output.flush();

            final var response = (Response) input.readObject();

            input.close();
            output.close();

            return response;
        } catch (final IOException e) {
            throw new ConnectionException(e);
        } catch (final ClassNotFoundException e) {
            throw new OperationException("A resposta não pôde ser interpretada", e);
        }
    }

}
