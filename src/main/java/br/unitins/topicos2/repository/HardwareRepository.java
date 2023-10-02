package br.unitins.topicos2.repository;

import java.util.List;

import br.unitins.topicos2.model.Hardware;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HardwareRepository implements PanacheRepository<Hardware> {
    
    public List<Hardware> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%").list();
    }
}
