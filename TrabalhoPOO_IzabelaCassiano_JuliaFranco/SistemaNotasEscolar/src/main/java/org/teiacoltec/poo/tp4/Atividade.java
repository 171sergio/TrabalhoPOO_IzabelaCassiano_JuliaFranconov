package org.teiacoltec.poo.tp4;

import java.util.Date;

/**
 * Representa uma atividade.
 */
public class Atividade {
    private int id;
    private String nome;
    private String descricao;
    private Date inicio;
    private Date fim;
    private float valor;

    public Atividade(int id, String nome, String descricao, Date inicio, Date fim, float valor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
        this.valor = valor;
    }

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
    public float getValor() { return valor; }
    public void setValor(float valor) { this.valor = valor; }

    @Override
    public String toString() { return "Atividade: " + nome + " (ID: " + id + ", Valor: " + valor + ")"; }
}