package br.unitins.topicos2.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Hardware extends Produto {

    @ManyToOne
    private Marca marca;
    private String modelo;
    private LocalDate lancamento;

    public String getModelo() {
        return modelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
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