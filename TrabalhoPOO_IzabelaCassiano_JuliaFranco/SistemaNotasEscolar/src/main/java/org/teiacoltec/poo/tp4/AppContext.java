package org.teiacoltec.poo.tp4;

import org.teiacoltec.poo.tp4.dao.PessoaDAO;
import org.teiacoltec.poo.tp4.dao.TurmaDAO;
import org.teiacoltec.poo.tp4.dao.AtividadeDAO;
import org.teiacoltec.poo.tp4.dao.NotaDAO;
import org.teiacoltec.poo.tp4.dao.TarefaDAO;
import org.teiacoltec.poo.tp4.dao.jdbc.PessoaDAOJdbc;
import org.teiacoltec.poo.tp4.dao.jdbc.TurmaDAOJdbc;
import org.teiacoltec.poo.tp4.dao.memory.InMemoryStore;
import org.teiacoltec.poo.tp4.dao.memory.PessoaDAOInMemory;
import org.teiacoltec.poo.tp4.dao.memory.TurmaDAOInMemory;
import org.teiacoltec.poo.tp4.dao.memory.AtividadeDAOInMemory;
import org.teiacoltec.poo.tp4.dao.memory.NotaDAOInMemory;
import org.teiacoltec.poo.tp4.dao.memory.TarefaDAOInMemory;
import org.teiacoltec.poo.tp4.infra.ConnectionFactory;
import org.teiacoltec.poo.tp4.infra.SchemaInitializer;

import java.sql.Connection;
import java.util.Date;

/**
 * Contexto da aplicação: DAOs, autenticação e dados iniciais.
 */
public class AppContext {
    public final Autenticacao autenticacao = new Autenticacao();
    public PessoaDAO pessoaDAO;
    public TurmaDAO turmaDAO;
    public AtividadeDAO atividadeDAO;
    public NotaDAO notaDAO;
    public TarefaDAO tarefaDAO;
    public Connection jdbcConnection;

    public AppContext() {
        try {
            jdbcConnection = ConnectionFactory.getConnection();
            SchemaInitializer.ensureSchema(jdbcConnection);
            pessoaDAO = new PessoaDAOJdbc(jdbcConnection);
            turmaDAO = new TurmaDAOJdbc(jdbcConnection);
            InMemoryStore mem = new InMemoryStore();
            atividadeDAO = new AtividadeDAOInMemory(mem);
            notaDAO = new NotaDAOInMemory(mem);
            tarefaDAO = new TarefaDAOInMemory(mem);
        } catch (Exception e) {
            InMemoryStore mem = new InMemoryStore();
            pessoaDAO = new PessoaDAOInMemory(mem);
            turmaDAO = new TurmaDAOInMemory(mem);
            atividadeDAO = new AtividadeDAOInMemory(mem);
            notaDAO = new NotaDAOInMemory(mem);
            tarefaDAO = new TarefaDAOInMemory(mem);
        }
        seedIfEmpty();
    }

    private void seedIfEmpty() {
        if (pessoaDAO.listarTodos().isEmpty()) {
            Date hoje = new Date();
            Professor prof1 = new Professor("12345678901", "Dr. Joao Silva", hoje, "joao@escola.com", "Rua A, 123", "PROF001", "Doutorado em Computacao", "prof.joao", "123456");
            Aluno aluno1 = new Aluno("98765432100", "Maria Santos", hoje, "maria@aluno.com", "Rua B, 456", "ALU001", "Ciencia da Computacao", "maria.santos", "senha123");
            Monitor monitor1 = new Monitor("11122233344", "Pedro Oliveira", hoje, "pedro@monitor.com", "Rua C, 789", "MON001", "Ciencia da Computacao", "pedro.monitor", "monitor123");
            pessoaDAO.salvar(prof1);
            pessoaDAO.salvar(aluno1);
            pessoaDAO.salvar(monitor1);

            Turma turma1 = new Turma(1, "Programacao Orientada a Objetos", "Turma de POO 2024", hoje, hoje);
            turmaDAO.salvar(turma1);
            try {
                turmaDAO.adicionarParticipante(turma1, prof1);
                turmaDAO.adicionarParticipante(turma1, aluno1);
                turmaDAO.adicionarParticipante(turma1, monitor1);
            } catch (Exception ignored) {}

            Atividade atividade1 = new Atividade(1, "Trabalho Final", "Implementacao de sistema de notas", hoje, hoje, 10.0f);
            atividadeDAO.salvar(atividade1);
            try { turma1.associaAtividade(atividade1); } catch (Exception ignored) {}
        }
    }
}