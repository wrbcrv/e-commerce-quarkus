package dev.application.repository;

import dev.application.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

  public PanacheQuery<Usuario> findByNome(String nome) {
    return find("UPPER(nome) LIKE UPPER(?1) ", "%" + nome + "%");
  }

  public Usuario findByLogin(String login) {
    try {
      return find("login = ?1 ", login).singleResult();
    } catch (NoResultException e) {
      e.printStackTrace();

      return null;
    }

  }

  public Usuario findByLoginAndSenha(String login, String senha) {
    try {
      return find("login = ?1 AND senha = ?2 ", login, senha).singleResult();
    } catch (NoResultException e) {
      e.printStackTrace();

      return null;
    }
  }
}