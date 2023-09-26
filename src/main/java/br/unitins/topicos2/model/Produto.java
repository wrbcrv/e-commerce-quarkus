package br.unitins.topicos2.model;

import jakarta.persistence.Entity;

@Entity
public class Produto extends DefaultEntity {

    private String nome;
    private Float preco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }
}