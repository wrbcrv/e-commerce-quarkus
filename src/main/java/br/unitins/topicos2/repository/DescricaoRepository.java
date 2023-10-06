package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.Descricao;
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