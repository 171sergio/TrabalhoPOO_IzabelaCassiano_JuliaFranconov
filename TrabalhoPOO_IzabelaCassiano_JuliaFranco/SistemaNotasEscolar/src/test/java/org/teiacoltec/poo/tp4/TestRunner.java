package org.teiacoltec.poo.tp4;

import org.teiacoltec.poo.tp4.*;

public class TestRunner {

    public static void main(String[] args) {
        AppContext ctx = new AppContext();
        testAutenticacao(ctx);
        testTurmaCRUD(ctx);
        testNotasCRUD(ctx);
        if (ctx.jdbcConnection != null) {
            testNotasCarga(ctx);
        }
        System.out.println("Todos os testes passaram.");
    }

    private static void testAutenticacao(AppContext ctx) {
        try {
            Pessoa p = ctx.autenticacao.autenticar("prof.joao", "123456", ctx.pessoaDAO.listarTodos());
            if (p == null) {
                throw new RuntimeException("Autenticacao falhou");
            }
        } catch (Exception e) {
            throw new RuntimeException("Teste de autenticacao falhou", e);
        }
    }

    private static void testTurmaCRUD(AppContext ctx) {
        int count = ctx.turmaDAO.listarTodos().size();
        Turma t = new Turma(999, "Teste", "Desc", new java.util.Date(), new java.util.Date());
        ctx.turmaDAO.salvar(t);
        if (ctx.turmaDAO.listarTodos().size() != count + 1) {
            throw new RuntimeException("Salvar turma falhou");
        }
        ctx.turmaDAO.remover(t);
        if (ctx.turmaDAO.listarTodos().size() != count) {
            throw new RuntimeException("Remover turma falhou");
        }
    }

    private static void testNotasCRUD(AppContext ctx) {
        Aluno a = new Aluno("98765432100", "Teste Aluno", new java.util.Date(), "t@e.com", "Rua X", "ALUTEST", "CC", "aluno.test", "123");
        ctx.pessoaDAO.salvar(a);
        Atividade atv = new Atividade(1000, "Prova", "Desc", new java.util.Date(), new java.util.Date(), 10f);
        ctx.atividadeDAO.salvar(atv);
        Nota n = new Nota(a, atv, 8.5f, "ok");
        ctx.notaDAO.salvar(n);
        Nota buscada = ctx.notaDAO.buscarPorAlunoEAtividade(a, atv);
        if (buscada == null || Math.abs(buscada.getValor() - 8.5f) > 0.001) {
            throw new RuntimeException("Salvar/Buscar nota falhou");
        }
        ctx.notaDAO.remover(n);
        if (ctx.notaDAO.buscarPorAlunoEAtividade(a, atv) != null) {
            throw new RuntimeException("Remover nota falhou");
        }
    }

    private static void testNotasCarga(AppContext ctx) {
        Aluno a = new Aluno("11122233344", "Carga Aluno", new java.util.Date(), "c@e.com", "Rua Y", "ALUCARGA", "CC", "aluno.carga", "123");
        ctx.pessoaDAO.salvar(a);
        Atividade atv = new Atividade(2000, "Prova Carga", "Desc", new java.util.Date(), new java.util.Date(), 10f);
        ctx.atividadeDAO.salvar(atv);
        for (int i = 0; i < 1000; i++) {
            ctx.notaDAO.salvar(new Nota(a, atv, (i % 10), ""));
        }
        java.util.List<Nota> notas = ctx.notaDAO.listarPorAluno(a);
        if (notas.isEmpty()) {
            throw new RuntimeException("Carga de notas falhou");
        }
    }
}
