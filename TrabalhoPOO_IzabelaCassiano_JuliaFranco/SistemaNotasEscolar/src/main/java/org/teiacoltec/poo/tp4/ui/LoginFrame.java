package org.teiacoltec.poo.tp4.ui; // Pacote correto

// Imports explícitos ajudam a resolver o erro "cannot find symbol"
import org.teiacoltec.poo.tp4.AppContext; 
import org.teiacoltec.poo.tp4.Autenticacao;
import org.teiacoltec.poo.tp4.CredenciaisInvalidasException;
import org.teiacoltec.poo.tp4.Pessoa;
import org.teiacoltec.poo.tp4.dao.PessoaDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;
// ... resto do código

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Tela de login com Swing.
 */
public class LoginFrame extends JFrame {
    private final AppContext ctx;
    private final JTextField loginField = new JTextField(20);
    private final JPasswordField senhaField = new JPasswordField(20);
    private final JLabel statusLabel = new JLabel(" ");

    public LoginFrame(AppContext ctx) {
        super("Sistema de Notas - Login");
        this.ctx = ctx;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        JPanel form = new JPanel(new GridLayout(0,1,5,5));
        form.add(new JLabel("Login:"));
        form.add(loginField);
        form.add(new JLabel("Senha:"));
        form.add(senhaField);
        JButton entrar = new JButton("Entrar");
        entrar.addActionListener(e -> tentarLogin());
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(entrar);
        add(form, BorderLayout.CENTER);
        add(actions, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(null);
    }

    private void tentarLogin() {
        String login = loginField.getText().trim();
        String senha = new String(senhaField.getPassword());
        PessoaDAO pessoaDAO = ctx.pessoaDAO;
        List<Pessoa> usuarios = pessoaDAO.listarTodos();
        try {
            Pessoa p = ctx.autenticacao.autenticar(login, senha, usuarios);
            statusLabel.setText("Bem-vindo(a), " + p.getNome());
            SwingUtilities.invokeLater(() -> {
                dispose();
                new MainFrame(ctx).setVisible(true);
            });
        } catch (CredenciaisInvalidasException ex) {
            statusLabel.setText("Credenciais inválidas");
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}