package br.edu.ufersa.cc.sd.services;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ScreenService {

    private final JTextArea output;

    public void await() {
        JOptionPane.showMessageDialog(output, "Clique em \"Ok\" para continuar.");
    }

    public void awaitAndBack() {
        JOptionPane.showMessageDialog(null, "Clique em \"Ok\" para voltar.");
    }

    public boolean confirm(final String command) {
        return JOptionPane.showConfirmDialog(output, command) == 1;
    }

    public String read(final String command) {
        return JOptionPane.showInputDialog(command);
    }

    public ScreenService print(final Object text) {
        output.setText(output.getText() + text);
        return this;
    }

    public ScreenService println() {
        output.setText(output.getText() + "\n");
        return this;
    }

    public ScreenService println(final Object text) {
        output.setText(output.getText() + text + "\n");
        return this;
    }

    public ScreenService clear() {
        output.setText("");
        return this;
    }

    public ScreenService choose(final String command, final Map<String, Runnable> options) {
        final var keys = options.keySet().toArray();
        final var option = (String) JOptionPane.showInputDialog(null, command, "Escolher",
                JOptionPane.QUESTION_MESSAGE, null, keys, null);

        options.get(option).run();

        return this;
    }

    public JPanel addButton(final JPanel frame, final String text, final Runnable action) {
        final var button = new JButton(text);
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                action.run();
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // Ignorar
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // Ignorar
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // Ignorar
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // Ignorar
            }
        });

        frame.add(button);
        return frame;
    }

}
