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

    public Usuario findByEmail(String email) {
        if (email == null)
            return null;
        
        return find("UPPER(email) LIKE ?1 ", "%" + email.toUpperCase() + "%").firstResult();
    }

    public Usuario findByEmailAndSenha(String email, String senha) {
        if (email == null || senha == null)
            return null;

        return find("email = ?1 AND senha = ?2 ", email, senha).firstResult();
    }
}