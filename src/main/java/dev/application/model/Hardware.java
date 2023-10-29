package dev.application.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Hardware extends Produto {

    @JoinColumn(name = "id_marca")
    @ManyToOne
    private Marca marca;
    private String modelo;
    @JoinColumn(name = "id_fabricante")
    @ManyToOne
    private Fabricante fabricante;
    private LocalDate lancamento;
    @Enumerated(EnumType.STRING)
    private Integridade integridade;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

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

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
    }

    public Integridade getIntegridade() {
        return integridade;
    }

    public void setIntegridade(Integridade integridade) {
        this.integridade = integridade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}