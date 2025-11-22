package org.teiacoltec.poo.tp4;

import java.util.Date;

/**
 * Nota de um aluno em uma atividade.
 */
public class Nota {
    private Aluno aluno;
    private Atividade atividade;
    private float valor;
    private Date dataLancamento;
    private String observacoes;

    public Nota(Aluno aluno, Atividade atividade, float valor, String observacoes) {
        this.aluno = aluno;
        this.atividade = atividade;
        this.valor = valor;
        this.observacoes = observacoes;
        this.dataLancamento = new Date();
    }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Atividade getAtividade() { return atividade; }
    public void setAtividade(Atividade atividade) { this.atividade = atividade; }
    public float getValor() { return valor; }
    public void setValor(float valor) { this.valor = valor; }
    public Date getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(Date dataLancamento) { this.dataLancamento = dataLancamento; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public float calcularPercentual() {
        if (atividade.getValor() == 0) return 0;
        return (valor / atividade.getValor()) * 100;
    }

    @Override
    public String toString() { return String.format("Nota: %.2f/%.2f (%.1f%%) - %s", valor, atividade.getValor(), calcularPercentual(), atividade.getNome()); }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Nota n = (Nota) obj;
        return aluno.equals(n.aluno) && atividade.equals(n.atividade);
    }
    @Override
    public int hashCode() { return aluno.hashCode() + atividade.hashCode(); }
}