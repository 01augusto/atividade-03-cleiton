import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraSimples {
    private JFrame frame;
    private JTextField display;
    private StringBuilder input;
    private String operador;
    private double resultado;

    public CalculadoraSimples() {
        frame = new JFrame("Calculadora");
        display = new JTextField();
        input = new StringBuilder();
        operador = "";
        resultado = 0;


        frame.setLayout(new BorderLayout());
        frame.add(display, BorderLayout.NORTH);
        JPanel panel = new JPanel(new GridLayout(4, 4));
        frame.add(panel, BorderLayout.CENTER);


        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String botao : botoes) {
            JButton button = new JButton(botao);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    acionarBotao(e.getActionCommand());
                }
            });
            panel.add(button);
        }

        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void acionarBotao(String comando) {
        if (comando.equals("=")) {
            calcular();
        } else if (comando.equals("C")) {
            input.setLength(0);
            operador = "";
            resultado = 0;
        } else {
            input.append(comando);
        }
        display.setText(input.toString());
    }

    private void calcular() {
        String[] tokens = input.toString().split("[\\+\\-\\*/]");
        if (tokens.length < 2) return;

        double num1 = Double.parseDouble(tokens[0]);
        double num2 = Double.parseDouble(tokens[1]);
        switch (operador) {
            case "+":
                resultado = num1 + num2;
                break;
            case "-":
                resultado = num1 - num2;
                break;
            case "*":
                resultado = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    resultado = num1 / num2;
                }
                break;
        }
        display.setText(String.valueOf(resultado));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraSimples());
    }
}
