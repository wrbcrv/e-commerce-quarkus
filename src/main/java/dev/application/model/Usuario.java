package dev.application.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario extends DefaultEntity {

  private String nome;
  private String sobrenome;
  private String cpf;
  private String rg;
  private String login;
  private String senha;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(name = "usuario_endereco", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_endereco"))
  private List<Endereco> enderecos;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(name = "usuario_cartao", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_cartao"))
  private List<Cartao> cartoes;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinTable(name = "usuario_favoritos", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_favorito"))
  private List<Hardware> favoritos;
  private Perfil perfil;

  private String imageName;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getRg() {
    return rg;
  }

  public void setRg(String rg) {
    this.rg = rg;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public List<Endereco> getEnderecos() {
    return enderecos;
  }

  public List<Cartao> getCartoes() {
    return cartoes;
  }

  public void setCartoes(List<Cartao> cartoes) {
    this.cartoes = cartoes;
  }

  public void setEnderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
  }

  public Perfil getPerfil() {
    return perfil;
  }

  public void setPerfil(Perfil perfil) {
    this.perfil = perfil;
  }

  public List<Hardware> getFavoritos() {
    return favoritos;
  }

  public void setFavoritos(List<Hardware> favoritos) {
    this.favoritos = favoritos;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }
}