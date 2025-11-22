package org.teiacoltec.poo.tp4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * Representa uma turma com participantes e atividades.
 */
public class Turma {
    private int id;
    private String nome;
    private String descricao;
    private Date inicio;
    private Date fim;
    private final List<Pessoa> participantes;
    private Turma turmaPai;
    private final List<Turma> turmasFilhas;
    private final List<Atividade> atividades;

    public Turma(int id, String nome, String descricao, Date inicio, Date fim) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
        this.participantes = new ArrayList<>();
        this.turmasFilhas = new ArrayList<>();
        this.atividades = new ArrayList<>();
    }

    public Turma(Turma turmaPai) {
        this.turmaPai = turmaPai;
        this.participantes = new ArrayList<>();
        this.turmasFilhas = new ArrayList<>();
        this.atividades = new ArrayList<>();
    }

    public Pessoa[] obtemListaParticipantes() { return participantes.toArray(Pessoa[]::new); }
    public void adicionarParticipante(Pessoa pessoa) throws PessoaJaParticipanteException { if (participa(pessoa)) throw new PessoaJaParticipanteException("A pessoa ja e participante desta turma."); participantes.add(pessoa); }
    public void removerParticipante(Pessoa pessoa) throws PessoaNaoEncontradaException { if (!participa(pessoa)) throw new PessoaNaoEncontradaException("A pessoa nao foi encontrada nesta turma."); participantes.remove(pessoa); }
    public boolean participa(Pessoa pessoa) { return participantes.contains(pessoa); }
    public void associaSubturma(Turma subturma) throws SubturmaJaAssociadaException { if (turmasFilhas.contains(subturma)) throw new SubturmaJaAssociadaException("A subturma ja esta associada a esta turma."); turmasFilhas.add(subturma); subturma.turmaPai = this; }
    public void desassociaSubturma(Turma subturma) throws SubturmaNaoAssociadaException { if (!turmasFilhas.contains(subturma)) throw new SubturmaNaoAssociadaException("A subturma nao esta associada a esta turma."); turmasFilhas.remove(subturma); if (subturma.turmaPai == this) subturma.turmaPai = null; }

    public Professor[] obtemListaProfessores(boolean completa) {
        Stream<Professor> s = participantes.stream().filter(p -> p instanceof Professor).map(p -> (Professor) p);
        if (completa && turmaPai != null) s = Stream.concat(s, Stream.of(turmaPai.obtemListaProfessores(true)));
        return s.toArray(Professor[]::new);
    }
    public Aluno[] obtemListaAlunos(boolean completa) {
        Stream<Aluno> s = participantes.stream().filter(p -> p instanceof Aluno).map(p -> (Aluno) p);
        if (completa && turmaPai != null) s = Stream.concat(s, Stream.of(turmaPai.obtemListaAlunos(true)));
        return s.toArray(Aluno[]::new);
    }
    public Monitor[] obtemListaMonitores(boolean completa) {
        Stream<Monitor> s = participantes.stream().filter(p -> p instanceof Monitor).map(p -> (Monitor) p);
        if (completa && turmaPai != null) s = Stream.concat(s, Stream.of(turmaPai.obtemListaMonitores(true)));
        return s.toArray(Monitor[]::new);
    }
    public void associaAtividade(Atividade atividade) throws AtividadeJaAssociadaATurmaException { if (atividades.contains(atividade)) throw new AtividadeJaAssociadaATurmaException("Atividade ja associada a turma."); atividades.add(atividade); }
    public void desassociaAtividade(Atividade atividade) throws AtividadeNaoAssociadaATurmaException { if (!atividades.contains(atividade)) throw new AtividadeNaoAssociadaATurmaException("Atividade nao esta associada a turma."); atividades.remove(atividade); }
    public Atividade[] obtemAtividadesDaTurma(boolean completa) { Stream<Atividade> s = atividades.stream(); if (completa && turmaPai != null) s = Stream.concat(s, Stream.of(turmaPai.obtemAtividadesDaTurma(true))); return s.toArray(Atividade[]::new); }
    public Atividade[] obtemAtividadesDaTurma(boolean completa, Date inicio, Date fim) { return Stream.of(obtemAtividadesDaTurma(completa)).filter(a -> !a.getInicio().before(inicio) && !a.getFim().after(fim)).toArray(Atividade[]::new); }
    public static Turma obtemTurmaPorID(int id, List<Turma> turmas) { return turmas.stream().filter(t -> t.getId() == id).findFirst().orElse(null); }
    public static Turma[] obtemTurmasDaPessoa(Pessoa pessoa, List<Turma> turmas) { return turmas.stream().filter(t -> t.participa(pessoa)).toArray(Turma[]::new); }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Date getInicio() { return inicio; }
    public void setInicio(Date inicio) { this.inicio = inicio; }
    public Date getFim() { return fim; }
    public void setFim(Date fim) { this.fim = fim; }
    public Turma getTurmaPai() { return turmaPai; }
    public List<Turma> getTurmasFilhas() { return turmasFilhas; }
    public List<Atividade> getAtividades() { return atividades; }
    public List<Pessoa> getParticipantes() { return participantes; }

    @Override
    public String toString() { return "Turma: " + nome + " (ID: " + id + ")"; }
}