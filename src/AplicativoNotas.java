import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AplicativoNotas {
    private JFrame frame;
    private JTextField notaField;
    private JTextArea notasArea;
    private ArrayList<Double> notas;
    private JLabel mediaLabel;

    public AplicativoNotas() {
        frame = new JFrame("Aplicativo de Notas");
        notaField = new JTextField(10);
        notasArea = new JTextArea(10, 20);
        mediaLabel = new JLabel("Média: ");
        notas = new ArrayList<>();

        // Layout
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.add(new JLabel("Nota: "));
        panel.add(notaField);
        JButton addButton = new JButton("Adicionar Nota");
        panel.add(addButton);
        JButton calcularButton = new JButton("Calcular Média");
        panel.add(calcularButton);
        frame.add(panel, BorderLayout.NORTH);

        frame.add(new JScrollPane(notasArea), BorderLayout.CENTER);
        frame.add(mediaLabel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarNota();
            }
        });

        calcularButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularMedia();
            }
        });

        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void adicionarNota() {
        try {
            double nota = Double.parseDouble(notaField.getText());
            notas.add(nota);
            notasArea.append(nota + "\n");
            notaField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, insira uma nota válida.");
        }
    }

    private void calcularMedia() {
        if (notas.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhuma nota cadastrada.");
            return;
        }

        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        double media = soma / notas.size();
        mediaLabel.setText("Média: " + media);
        JOptionPane.showMessageDialog(frame, media >= 7.0 ? "Aprovado" : "Reprovado");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplicativoNotas());
    }
}
