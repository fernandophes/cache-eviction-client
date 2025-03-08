package br.edu.ufersa.cc.sd.views;

import java.awt.Color;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import br.edu.ufersa.cc.sd.services.OrderService;
import br.edu.ufersa.cc.sd.services.ScreenService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HomeView implements Runnable {

    private final OrderService orderService;
    private ScreenService screen;

    @Override
    public void run() {
        final var frame = openChat();

        final var options = new LinkedHashMap<String, Runnable>();
        options.put("Listar todas", new ListAllView(orderService, screen));
        options.put("Criar nova", new ListAllView(orderService, screen));
        options.put("Detalhar", new ListAllView(orderService, screen));
        options.put("Atualizar", new ListAllView(orderService, screen));
        options.put("Excluir", new ListAllView(orderService, screen));
        options.put("Contar", new ListAllView(orderService, screen));
        options.put("Sair", frame::dispose);

        while (frame.isShowing()) {
            screen.clear();
            screen.println("orderService");
            screen.choose("O que deseja fazer?", options);
        }
    }

    private JFrame openChat() {
        // Criação da tela
        final var frame = new JFrame("Gerenciador de Ordens de Serviço");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setVisible(true);

        final var textArea = new JTextArea("Exemplo de texto\n");
        textArea.setEditable(false);
        textArea.setFont(textArea.getFont().deriveFont(22F));
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);

        final var scrollPane = new JScrollPane();
        scrollPane.setViewportView(textArea);

        frame.add(scrollPane);
        frame.setVisible(true);

        screen = new ScreenService(textArea);

        return frame;
    }

}
