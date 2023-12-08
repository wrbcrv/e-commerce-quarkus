package dev.application.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido extends DefaultEntity {

  private LocalDateTime data;
  @ManyToOne
  @JoinColumn(name = "id_usuario")
  private Usuario usuario;
  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pedido")
  private List<Item> itens;
  private Double total;
  @ManyToOne
  @JoinColumn(name = "id_endereco")
  private Endereco endereco;
  @ManyToOne
  @JoinColumn(name = "id_cartao")
  private Cartao cartao;
  @ManyToMany
  @JoinTable(name = "pedido_cupom_associacao", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "cupom_id"))
  private List<Cupom> cupom;

  public LocalDateTime getData() {
    return data;
  }

  public void setData(LocalDateTime data) {
    this.data = data;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public List<Item> getItens() {
    return itens;
  }

  public void setItens(List<Item> itens) {
    this.itens = itens;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public Endereco getEndereco() {
    return endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

  public Cartao getCartao() {
    return cartao;
  }

  public void setCartao(Cartao cartao) {
    this.cartao = cartao;
  }

  public List<Cupom> getCupom() {
    return cupom;
  }

  public void setCupom(List<Cupom> cupom) {
    this.cupom = cupom;
  }
}