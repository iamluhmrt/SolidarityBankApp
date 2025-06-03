package com.pucpr.bancosolidario;
import java.io.Serializable;
public class Doacao implements Serializable {
    private int id;
    private String nomeDoador;
    private String nomeItem;
    private String descricao;
    private int quantidade;

    public Doacao() {
    }

    public Doacao(String nomeDoador, String nomeItem, String descricao, int quantidade) {
        this.nomeDoador = nomeDoador;
        this.nomeItem = nomeItem;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDoador() {
        return nomeDoador;
    }

    public void setNomeDoador(String nomeDoador) {
        this.nomeDoador = nomeDoador;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
