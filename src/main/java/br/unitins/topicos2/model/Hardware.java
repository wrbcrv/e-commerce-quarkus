package br.unitins.topicos2.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Hardware extends Produto {

    private String modelo;
    private LocalDate lancamento;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
    }
}