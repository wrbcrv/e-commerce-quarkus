package dev.application.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Hardware extends Produto {

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;
    private String modelo;
    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    private Fabricante fabricante;
    private LocalDate lancamento;
    private Categoria categoria;
    private Status status;
    @Column(length = 100000)
    private String descricao;
    private String imageName;

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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescricao() {
      return descricao;
    }

    public void setDescricao(String descricao) {
      this.descricao = descricao;
    }

    public String getImageName() {
      return imageName;
    }

    public void setImageName(String imageName) {
      this.imageName = imageName;
    }
}