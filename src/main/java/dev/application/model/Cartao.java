package dev.application.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;

@Entity
public class Cartao extends DefaultEntity {

  private Tipo tipo;
  private String numero;
  private String cvv;
  private LocalDate validade;
  private String titular;
  private String cpf;

  public Tipo getTipo() {
    return tipo;
  }

  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public LocalDate getValidade() {
    return validade;
  }

  public void setValidade(LocalDate validade) {
    this.validade = validade;
  }

  public String getTitular() {
    return titular;
  }

  public void setTitular(String titular) {
    this.titular = titular;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
}