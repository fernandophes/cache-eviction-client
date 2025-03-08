package br.edu.ufersa.cc.sd.services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import br.edu.ufersa.cc.sd.dto.Request;
import br.edu.ufersa.cc.sd.dto.Response;
import br.edu.ufersa.cc.sd.exceptions.ConnectionException;
import br.edu.ufersa.cc.sd.exceptions.OperationException;

public class SocketService {

    private static final String HOST = "localhost";
    private static final Integer PORT = 8484;

    public <T extends Serializable> Response<T> call(final Request<T> request) {
        return call(request, request.getType());
    }

    public <I extends Serializable, O extends Serializable> Response<O> call(final Request<I> request,
            final Class<O> type) {
        try (final var socket = new Socket(HOST, PORT)) {
            final var input = new ObjectInputStream(socket.getInputStream());
            final var output = new ObjectOutputStream(socket.getOutputStream());

            output.writeObject(request);
            output.flush();

            @SuppressWarnings("unchecked")
            final var response = (Response<O>) input.readObject();

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
