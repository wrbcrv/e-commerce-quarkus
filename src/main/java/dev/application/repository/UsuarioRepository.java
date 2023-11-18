package dev.application.repository;

import dev.application.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public PanacheQuery<Usuario> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%" + nome + "%");
    }

    public Usuario findByLogin(String login) {
        return find("login = ?1 ", login).firstResult();
    }

    public Usuario findByLoginAndSenha(String login, String senha) {
        if (login == null || senha == null)
            return null;

        return find("login = ?1 AND senha = ?2 ", login, senha).firstResult();
    }
}