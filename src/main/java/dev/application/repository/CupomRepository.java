package dev.application.repository;

import dev.application.model.Cupom;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CupomRepository implements PanacheRepository<Cupom> {

    public Cupom findByCodigo(String codigo) {
        if (codigo == null)
            return null;
        return find("UPPER(codigo) LIKE ?1 ", "%" + codigo.toUpperCase() + "%").firstResult();
    }
}