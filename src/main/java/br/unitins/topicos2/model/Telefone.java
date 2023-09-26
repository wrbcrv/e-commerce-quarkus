package br.unitins.topicos2.model;

import jakarta.persistence.Entity;

@Entity
public class Telefone extends DefaultEntity {

    private String ddd;
    private String numero;

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}