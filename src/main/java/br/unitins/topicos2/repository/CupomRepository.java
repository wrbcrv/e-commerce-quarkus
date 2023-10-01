package br.unitins.topicos2.repository;

import java.util.List;

import br.unitins.topicos2.model.Cupom;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CupomRepository implements PanacheRepository<Cupom> {

    public List<Cupom> findByCodigo(String codigo) {
        if (codigo == null)
            return null;
        return find("UPPER(codigo) LIKE ?1 ", "%" + codigo.toUpperCase() + "%").list();
    }
}