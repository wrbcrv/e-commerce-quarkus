package dev.application.repository;

import dev.application.model.Hardware;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HardwareRepository implements PanacheRepository<Hardware> {
    
    public PanacheQuery<Hardware> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }

    public PanacheQuery<Hardware> findByModelo(String modelo) {
        if (modelo == null)
            return null;
        return find("UPPER(modelo) LIKE ?1 ", "%" + modelo.toUpperCase() + "%");
    }
}
