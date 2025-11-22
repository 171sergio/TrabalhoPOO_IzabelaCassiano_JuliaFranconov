package org.teiacoltec.poo.tp4;

/**
 * Executa testes unitários básicos usando assertivas simples.
 */
public class TestRunner {
    public static void main(String[] args) {
        AppContext ctx = new AppContext();
        testAutenticacao(ctx);
        testTurmaCRUD(ctx);
        System.out.println("Todos os testes passaram.");
    }

    private static void testAutenticacao(AppContext ctx) {
        try {
            Pessoa p = ctx.autenticacao.autenticar("prof.joao", "123456", ctx.pessoaDAO.listarTodos());
            if (p == null) throw new RuntimeException("Autenticacao falhou");
        } catch (Exception e) { throw new RuntimeException("Teste de autenticacao falhou", e); }
    }

    private static void testTurmaCRUD(AppContext ctx) {
        int count = ctx.turmaDAO.listarTodos().size();
        Turma t = new Turma(999, "Teste", "Desc", new java.util.Date(), new java.util.Date());
        ctx.turmaDAO.salvar(t);
        if (ctx.turmaDAO.listarTodos().size() != count + 1) throw new RuntimeException("Salvar turma falhou");
        ctx.turmaDAO.remover(t);
        if (ctx.turmaDAO.listarTodos().size() != count) throw new RuntimeException("Remover turma falhou");
    }
}