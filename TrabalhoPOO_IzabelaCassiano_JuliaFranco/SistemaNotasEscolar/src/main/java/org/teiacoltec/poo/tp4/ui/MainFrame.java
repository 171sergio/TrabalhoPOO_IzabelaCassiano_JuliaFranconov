package org.teiacoltec.poo.tp4.ui;

import org.teiacoltec.poo.tp4.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class MainFrame extends JFrame {

    private final AppContext ctx;

    public MainFrame(AppContext ctx) {
        super("Sistema de Notas - Principal");
        this.ctx = ctx;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setJMenuBar(criarMenu());

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        JLabel titulo = new JLabel("Sistema de Gestão Escolar");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        String nomeUsuario = (ctx.autenticacao.getUsuarioLogado() != null)
                ? ctx.autenticacao.getUsuarioLogado().getNome() : "Usuário";
        JLabel subtitulo = new JLabel("Bem-vindo, " + nomeUsuario);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel info = new JLabel("Utilize o menu acima para gerenciar Turmas, Alunos e Notas.");
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        painelPrincipal.add(subtitulo);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 40)));
        painelPrincipal.add(info);
        add(painelPrincipal, BorderLayout.CENTER);
        pack();
        setSize(800, 600);
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
        JMenu notas = new JMenu("Notas");
        JMenuItem lancarNota = new JMenuItem("Lançar Nota");
        lancarNota.addActionListener(e -> lancarNota());
        JMenuItem boletim = new JMenuItem("Ver Boletim");
        boletim.addActionListener(e -> verBoletim());
        notas.add(lancarNota);
        notas.add(boletim);
        bar.add(usuarios);
        bar.add(turmas);
        bar.add(atividades);
        bar.add(notas);
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
        if (nome == null || login == null || senha == null) {
            return;
        }
        if (ctx.pessoaDAO.buscarPorLogin(login) != null) {
            JOptionPane.showMessageDialog(this, "Login já existe", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
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
        try {
            valor = Float.parseFloat(JOptionPane.showInputDialog(this, "Valor (pontos):", "10"));
        } catch (Exception ignored) {
        }
        int novoId = ctx.atividadeDAO.listarTodos().stream().mapToInt(Atividade::getId).max().orElse(0) + 1;
        Atividade a = new Atividade(novoId, nome == null ? "Sem nome" : nome, descricao == null ? "" : descricao, new Date(), new Date(), valor);
        ctx.atividadeDAO.salvar(a);
        JOptionPane.showMessageDialog(this, "Atividade criada: " + a.toString());
    }

    private void lancarNota() {
        List<Pessoa> alunos = new ArrayList<>();
        for (Pessoa p : ctx.pessoaDAO.listarTodos()) {
            if (p instanceof Aluno) {
                alunos.add(p);
            }
        }
        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum aluno cadastrado.");
            return;
        }

        Object[] alunosArray = alunos.stream().map(Pessoa::getNome).toArray();
        String nomeAluno = (String) JOptionPane.showInputDialog(this, "Selecione o Aluno:", "Lançar Nota",
                JOptionPane.QUESTION_MESSAGE, null, alunosArray, alunosArray[0]);
        if (nomeAluno == null) {
            return;
        }

        Aluno alunoSelecionado = (Aluno) alunos.stream()
                .filter(a -> a.getNome().equals(nomeAluno))
                .findFirst()
                .orElse(null);
        if (alunoSelecionado == null) {
            return;
        }

        List<Atividade> atividades = ctx.atividadeDAO.listarTodos();
        if (atividades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma atividade cadastrada.");
            return;
        }

        Object[] atvArray = atividades.stream().map(Atividade::getNome).toArray();
        String nomeAtv = (String) JOptionPane.showInputDialog(this, "Selecione a Atividade:", "Lançar Nota",
                JOptionPane.QUESTION_MESSAGE, null, atvArray, atvArray[0]);
        if (nomeAtv == null) {
            return;
        }

        Atividade atvSelecionada = atividades.stream()
                .filter(a -> a.getNome().equals(nomeAtv))
                .findFirst()
                .orElse(null);
        if (atvSelecionada == null) {
            return;
        }

        String valorStr = JOptionPane.showInputDialog(this, "Digite a nota (Max: " + atvSelecionada.getValor() + "):");
        if (valorStr == null) {
            return;
        }
        try {
            float valor = Float.parseFloat(valorStr);
            if (valor < 0 || valor > atvSelecionada.getValor()) {
                JOptionPane.showMessageDialog(this, "Nota inválida! Deve ser entre 0 e " + atvSelecionada.getValor());
                return;
            }
            Nota novaNota = new Nota(alunoSelecionado, atvSelecionada, valor, "");
            ctx.notaDAO.salvar(novaNota);
            JOptionPane.showMessageDialog(this, "Nota lançada com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite um número válido.");
        }
    }

    private void verBoletim() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== BOLETIM GERAL ===\n\n");
        List<Nota> notas = ctx.notaDAO.listarTodos();
        if (notas.isEmpty()) {
            sb.append("Nenhuma nota registrada.");
        } else {
            for (Nota n : notas) {
                sb.append(String.format("Aluno: %-15s | Atividade: %-15s | Nota: %.2f\n",
                        n.getAluno().getNome(), n.getAtividade().getNome(), n.getValor()));
            }
        }
        mostrarMsg(sb.toString(), "Boletim");
    }

    private void mostrarMsg(String msg, String titulo) {
        JTextArea textArea = new JTextArea(msg);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        JOptionPane.showMessageDialog(this, scrollPane, titulo, JOptionPane.PLAIN_MESSAGE);
    }
}
