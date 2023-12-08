package dev.application.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
public class Fornecedor extends DefaultEntity {

  @Column(length = 50)
  private String nome;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(name = "fornecedor_endereco", joinColumns = @JoinColumn(name = "id_fornecedor"), inverseJoinColumns = @JoinColumn(name = "id_endereco"))
  List<Endereco> enderecos;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<Endereco> getEnderecos() {
    return enderecos;
  }

  public void setEnderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
  }
}