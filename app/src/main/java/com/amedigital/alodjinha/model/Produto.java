package com.amedigital.alodjinha.model;

public class Produto {
    private int id;
    private String nome, urlImagem, descricao;
    private double precoDe, precoPor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoDe() {
        return precoDe;
    }

    public void setPrecoDe(double precoDe) {
        this.precoDe = precoDe;
    }

    public double getPrecoPor() {
        return precoPor;
    }

    public void setPrecoPor(double precoPor) {
        this.precoPor = precoPor;
    }
}
