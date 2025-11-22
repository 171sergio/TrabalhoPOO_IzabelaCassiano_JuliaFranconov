package org.teiacoltec.poo.tp4.ui;

import org.teiacoltec.poo.tp4.*;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Janela principal com menus básicos.
 */
public class MainFrame extends JFrame {
    private final AppContext ctx;

    public MainFrame(AppContext ctx) {
        super("Sistema de Notas - Principal");
        this.ctx = ctx;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setJMenuBar(criarMenu());
        add(new JLabel("Bem-vindo(a) ao Sistema de Notas"), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    private JMenuBar criarMenu() {
        JMenuBar bar = new JMenuBar();
        JMenu usuarios = new JMenu("Usuários");
        JMenuItem listarUsuarios = new JMenuItem("Listar");
        listarUsuarios.addActionListener(e -> mostrarUsuarios());
        JMenuItem criarAluno = new JMenuItem("Novo Aluno");
        criarAluno.addActionListener(e -> criarAluno());
        usuarios.add(listarUsuarios);
        usuarios.add(criarAluno);

        JMenu turmas = new JMenu("Turmas");
        JMenuItem listarTurmas = new JMenuItem("Listar");
        listarTurmas.addActionListener(e -> mostrarTurmas());
        JMenuItem criarTurma = new JMenuItem("Nova Turma");
        criarTurma.addActionListener(e -> criarTurma());
        turmas.add(listarTurmas);
        turmas.add(criarTurma);

        JMenu atividades = new JMenu("Atividades");
        JMenuItem listarAtividades = new JMenuItem("Listar");
        listarAtividades.addActionListener(e -> mostrarAtividades());
        JMenuItem criarAtividade = new JMenuItem("Nova Atividade");
        criarAtividade.addActionListener(e -> criarAtividade());
        atividades.add(listarAtividades);
        atividades.add(criarAtividade);

        bar.add(usuarios);
        bar.add(turmas);
        bar.add(atividades);
        return bar;
    }

    private void mostrarUsuarios() {
        StringBuilder sb = new StringBuilder();
        for (Pessoa p : ctx.pessoaDAO.listarTodos()) {
            sb.append(p.getClass().getSimpleName()).append(": ").append(p.getNome()).append(" (Login: ").append(p.getLogin()).append(")\n");
        }
        JOptionPane.showMessageDialog(this, sb.length() == 0 ? "Nenhum usuário" : sb.toString(), "Usuários", JOptionPane.INFORMATION_MESSAGE);
    }

    private void criarAluno() {
        String nome = JOptionPane.showInputDialog(this, "Nome do aluno:");
        String matricula = JOptionPane.showInputDialog(this, "Matrícula:");
        String login = JOptionPane.showInputDialog(this, "Login:");
        String senha = JOptionPane.showInputDialog(this, "Senha:");
        if (nome == null || login == null || senha == null) return;
        if (ctx.pessoaDAO.buscarPorLogin(login) != null) { JOptionPane.showMessageDialog(this, "Login já existe", "Erro", JOptionPane.ERROR_MESSAGE); return; }
        Aluno a = new Aluno(nome, matricula, login, senha);
        ctx.pessoaDAO.salvar(a);
        JOptionPane.showMessageDialog(this, "Aluno criado: " + a.getNome());
    }

    private void mostrarTurmas() {
        StringBuilder sb = new StringBuilder();
        for (Turma t : ctx.turmaDAO.listarTodos()) {
            sb.append(t.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.length() == 0 ? "Nenhuma turma" : sb.toString(), "Turmas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void criarTurma() {
        String nome = JOptionPane.showInputDialog(this, "Nome da turma:");
        String descricao = JOptionPane.showInputDialog(this, "Descrição:");
        int novoId = ctx.turmaDAO.listarTodos().stream().mapToInt(Turma::getId).max().orElse(0) + 1;
        Turma t = new Turma(novoId, nome == null ? "Sem nome" : nome, descricao == null ? "" : descricao, new Date(), new Date());
        ctx.turmaDAO.salvar(t);
        JOptionPane.showMessageDialog(this, "Turma criada: " + t.toString());
    }

    private void mostrarAtividades() {
        StringBuilder sb = new StringBuilder();
        for (Atividade a : ctx.atividadeDAO.listarTodos()) {
            sb.append(a.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.length() == 0 ? "Nenhuma atividade" : sb.toString(), "Atividades", JOptionPane.INFORMATION_MESSAGE);
    }

    private void criarAtividade() {
        String nome = JOptionPane.showInputDialog(this, "Nome da atividade:");
        String descricao = JOptionPane.showInputDialog(this, "Descrição:");
        float valor = 10f;
        try { valor = Float.parseFloat(JOptionPane.showInputDialog(this, "Valor (pontos):", "10")); } catch (Exception ignored) {}
        int novoId = ctx.atividadeDAO.listarTodos().stream().mapToInt(Atividade::getId).max().orElse(0) + 1;
        Atividade a = new Atividade(novoId, nome == null ? "Sem nome" : nome, descricao == null ? "" : descricao, new Date(), new Date(), valor);
        ctx.atividadeDAO.salvar(a);
        JOptionPane.showMessageDialog(this, "Atividade criada: " + a.toString());
    }
}