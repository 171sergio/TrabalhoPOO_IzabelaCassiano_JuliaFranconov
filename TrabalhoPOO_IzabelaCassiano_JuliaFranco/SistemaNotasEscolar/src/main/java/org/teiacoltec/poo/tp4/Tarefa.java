package org.teiacoltec.poo.tp4;

import java.util.Date;

/**
 * Tarefa associada a uma atividade.
 */
public class Tarefa {
    private int id;
    private String nome;
    private String descricao;
    private Date prazo;
    private boolean concluida;
    private Atividade atividade;
    private Pessoa responsavel;
    private Date dataCriacao;
    private Date dataConclusao;
    private String observacoes;

    public Tarefa(int id, String nome, String descricao, Date prazo, Atividade atividade, Pessoa responsavel) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.prazo = prazo;
        this.atividade = atividade;
        this.responsavel = responsavel;
        this.concluida = false;
        this.dataCriacao = new Date();
        this.observacoes = "";
    }

    public Tarefa(int id, String nome, String descricao, Date prazo) { this(id, nome, descricao, prazo, null, null); }

    public void marcarComoConcluida() { this.concluida = true; this.dataConclusao = new Date(); }
    public void marcarComoNaoConcluida() { this.concluida = false; this.dataConclusao = null; }
    public boolean estaAtrasada() { if (concluida) return false; return new Date().after(prazo); }
    public long diasRestantes() { if (concluida) return 0; long dif = prazo.getTime() - new Date().getTime(); return dif / (1000 * 60 * 60 * 24); }
    public String getStatus() { if (concluida) return "Concluída"; else if (estaAtrasada()) return "Atrasada"; else return "Pendente"; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Date getPrazo() { return prazo; }
    public void setPrazo(Date prazo) { this.prazo = prazo; }
    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; if (concluida && dataConclusao == null) this.dataConclusao = new Date(); else if (!concluida) this.dataConclusao = null; }
    public Atividade getAtividade() { return atividade; }
    public void setAtividade(Atividade atividade) { this.atividade = atividade; }
    public Pessoa getResponsavel() { return responsavel; }
    public void setResponsavel(Pessoa responsavel) { this.responsavel = responsavel; }
    public Date getDataCriacao() { return dataCriacao; }
    public Date getDataConclusao() { return dataConclusao; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarefa: ").append(nome).append(" (ID: ").append(id).append(")\n");
        sb.append("Status: ").append(getStatus());
        if (responsavel != null) sb.append(" - Responsável: ").append(responsavel.getNome());
        if (diasRestantes() > 0) sb.append(" - ").append(diasRestantes()).append(" dias restantes");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) { if (this == obj) return true; if (obj == null || getClass() != obj.getClass()) return false; Tarefa t = (Tarefa) obj; return id == t.id; }
    @Override
    public int hashCode() { return Integer.hashCode(id); }
}