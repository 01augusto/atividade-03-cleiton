import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CadastroContatos {
    private JFrame frame;
    private JTextField nomeField, telefoneField, emailField;
    private DefaultListModel<String> contatosListModel;

    public CadastroContatos() {
        frame = new JFrame("Cadastro de Contatos");
        nomeField = new JTextField(15);
        telefoneField = new JTextField(15);
        emailField = new JTextField(15);
        contatosListModel = new DefaultListModel<>();

        // Layout
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.add(new JLabel("Nome: "));
        panel.add(nomeField);
        panel.add(new JLabel("Telefone: "));
        panel.add(telefoneField);
        panel.add(new JLabel("E-mail: "));
        panel.add(emailField);
        JButton addButton = new JButton("Adicionar Contato");
        panel.add(addButton);
        JButton removeButton = new JButton("Remover Contato");
        panel.add(removeButton);
        frame.add(panel, BorderLayout.NORTH);

        JList<String> contatosList = new JList<>(contatosListModel);
        frame.add(new JScrollPane(contatosList), BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarContato();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerContato(contatosList);
            }
        });

        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void adicionarContato() {
        String nome = nomeField.getText();
        String telefone = telefoneField.getText();
        String email = emailField.getText();

        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos.");
        } else {
            contatosListModel.addElement(nome + " - " + telefone + " - " + email);
            nomeField.setText("");
            telefoneField.setText("");
            emailField.setText("");
        }
    }

    private void removerContato(JList<String> contatosList) {
        int index = contatosList.getSelectedIndex();
        if (index != -1) {
            contatosListModel.remove(index);
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione um contato para remover.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroContatos());
    }
}