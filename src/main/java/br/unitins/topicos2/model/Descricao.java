package br.unitins.topicos2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Descricao extends DefaultEntity {
    
    @ManyToOne
    private Hardware hardware;
    private String conteudo;

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }
}
