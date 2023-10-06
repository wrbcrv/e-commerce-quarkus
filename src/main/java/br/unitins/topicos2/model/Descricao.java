package br.unitins.topicos2.model;

import jakarta.persistence.Entity;

@Entity
public class Descricao extends DefaultEntity {
    
    private String conteudo;

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
