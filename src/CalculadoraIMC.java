import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraIMC {
    private JFrame frame;
    private JTextField pesoField, alturaField;
    private JLabel resultadoLabel;

    public CalculadoraIMC() {
        frame = new JFrame("Calculadora de IMC");

        pesoField = new JTextField(10);
        alturaField = new JTextField(10);
        resultadoLabel = new JLabel("IMC: ");

        // Layout da janela
        frame.setLayout(new FlowLayout());
        frame.add(new JLabel("Peso (kg): "));
        frame.add(pesoField);
        frame.add(new JLabel("Altura (m): "));
        frame.add(alturaField);
        JButton calcButton = new JButton("Calcular IMC");
        frame.add(calcButton);
        frame.add(resultadoLabel);

        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularIMC();
            }
        });
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void calcularIMC() {
        try {
            double peso = Double.parseDouble(pesoField.getText());
            double altura = Double.parseDouble(alturaField.getText());

            if (peso <= 0 || altura <= 0) {
                JOptionPane.showMessageDialog(frame, "Valores de peso ou altura inválidos.");
                return;
            }

            double imc = peso / (altura * altura);
            String categoria = "";

            if (imc < 18.5) {
                categoria = "Baixo peso";
            } else if (imc >= 18.5 && imc < 24.9) {
                categoria = "Normal";
            } else if (imc >= 25 && imc < 29.9) {
                categoria = "Sobrepeso";
            } else {
                categoria = "Obesidade";
            }


            resultadoLabel.setText(String.format("IMC: %.2f - %s", imc, categoria));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira valores válidos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraIMC());
    }
}