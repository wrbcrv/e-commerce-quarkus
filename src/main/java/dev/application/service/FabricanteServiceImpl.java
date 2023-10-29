package dev.application.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import dev.application.dto.FabricanteDTO;
import dev.application.dto.FabricanteResponseDTO;
import dev.application.model.Fabricante;
import dev.application.repository.FabricanteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FabricanteServiceImpl implements FabricanteService {

    @Inject
    FabricanteRepository fabricanteRepository;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public FabricanteResponseDTO create(FabricanteDTO fabricanteDTO) throws ConstraintViolationException {
        validate(fabricanteDTO);

        Fabricante entity = new Fabricante();

        entity.setNome(fabricanteDTO.nome());
        entity.setSite(fabricanteDTO.site());

        fabricanteRepository.persist(entity);

        return FabricanteResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public FabricanteResponseDTO update(Long id, FabricanteDTO fabricanteDTO) throws ConstraintViolationException {
        validate(fabricanteDTO);

        Fabricante entity = fabricanteRepository.findById(id);

        entity.setNome(fabricanteDTO.nome());
        entity.setSite(fabricanteDTO.site());

        return FabricanteResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        fabricanteRepository.deleteById(id);
    }

    @Override
    public List<FabricanteResponseDTO> findAll(int page, int pageSize) {
        List<Fabricante> list = fabricanteRepository.findAll().page(page, pageSize).list();

        return list.stream().map(e -> FabricanteResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public FabricanteResponseDTO findById(Long id) {
        Fabricante fabricante = fabricanteRepository.findById(id);
        if (fabricante == null)
            throw new NotFoundException("Fabricante não encontrado.");

        return FabricanteResponseDTO.valueOf(fabricante);
    }

    @Override
    public List<FabricanteResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Fabricante> list = fabricanteRepository.findByNome(nome).page(page, pageSize).list();

        return list.stream().map(e -> FabricanteResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return fabricanteRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return fabricanteRepository.findByNome(nome).count();
    }

    private void validate(FabricanteDTO fabricanteDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<FabricanteDTO>> violations = validator.validate(fabricanteDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}