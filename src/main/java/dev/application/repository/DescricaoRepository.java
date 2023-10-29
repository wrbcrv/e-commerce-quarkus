package dev.application.repository;

import dev.application.model.Descricao;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DescricaoRepository implements PanacheRepository<Descricao> {

    public PanacheQuery<Descricao> findByConteudo(String conteudo) {
        if (conteudo == null)
            return null;
        return find("UPPER(conteudo) LIKE ?1 ", "%" + conteudo.toUpperCase() + "%");
    }
}