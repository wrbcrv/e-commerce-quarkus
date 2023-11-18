package dev.application.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import dev.application.dto.HardwareDTO;
import dev.application.dto.HardwareResponseDTO;
import dev.application.model.Categoria;
import dev.application.model.Hardware;
import dev.application.model.Status;
import dev.application.repository.FabricanteRepository;
import dev.application.repository.HardwareRepository;
import dev.application.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class HardwareServiceImpl implements HardwareService {

    @Inject
    HardwareRepository hardwareRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    Validator validator;

    @Inject
    FabricanteRepository fabricanteRepository;

    @Override
    @Transactional
    public HardwareResponseDTO create(HardwareDTO hardwareDTO) throws ConstraintViolationException {
        validate(hardwareDTO);

        Hardware entity = new Hardware();

        entity.setMarca(marcaRepository.findById(hardwareDTO.idMarca()));
        entity.setModelo(hardwareDTO.modelo());
        entity.setFabricante(fabricanteRepository.findById(hardwareDTO.idFabricante()));
        entity.setLancamento(hardwareDTO.lancamento());
        entity.setNome(hardwareDTO.nome());
        entity.setPreco(hardwareDTO.preco());
        entity.setEstoque(hardwareDTO.estoque());
        entity.setCategoria(Categoria.valueOf(hardwareDTO.idCategoria()));
        entity.setStatus(Status.valueOf(hardwareDTO.idStatus()));

        hardwareRepository.persist(entity);

        return HardwareResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public HardwareResponseDTO update(Long id, HardwareDTO hardwareDTO) throws ConstraintViolationException {
        validate(hardwareDTO);

        Hardware entity = hardwareRepository.findById(id);

        entity.setMarca(marcaRepository.findById(hardwareDTO.idMarca()));
        entity.setModelo(hardwareDTO.modelo());
        entity.setFabricante(fabricanteRepository.findById(hardwareDTO.idFabricante()));
        entity.setLancamento(hardwareDTO.lancamento());
        entity.setNome(hardwareDTO.nome());
        entity.setPreco(hardwareDTO.preco());
        entity.setEstoque(hardwareDTO.estoque());
        entity.setCategoria(Categoria.valueOf(hardwareDTO.idCategoria()));
        entity.setStatus(Status.valueOf(hardwareDTO.idStatus()));

        return HardwareResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        hardwareRepository.deleteById(id);
    }

    @Override
    public List<HardwareResponseDTO> findAll(int page, int pageSize) {
        List<Hardware> list = hardwareRepository.findAll().page(page, pageSize).list();

        return list.stream().map(e -> HardwareResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public HardwareResponseDTO findById(Long id) {
        Hardware hardware = hardwareRepository.findById(id);
        if (hardware == null)
            throw new NotFoundException("Hardware não encontrado.");

        return HardwareResponseDTO.valueOf(hardware);
    }

    @Override
    public List<HardwareResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Hardware> list = hardwareRepository.findByNome(nome).page(page, pageSize).list();

        return list.stream().map(e -> HardwareResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public List<HardwareResponseDTO> findByModelo(String modelo, int page, int pageSize) {
        List<Hardware> list = hardwareRepository.findByModelo(modelo).page(page, pageSize).list();

        return list.stream().map(e -> HardwareResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HardwareResponseDTO saveImage(Long id, String imageName) {
        Hardware entity = hardwareRepository.findById(id);
        entity.setImageName(imageName);

        return HardwareResponseDTO.valueOf(entity);
    }

    @Override
    public long count() {
        return hardwareRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return hardwareRepository.findByNome(nome).count();
    }

    private void validate(HardwareDTO hardwareDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<HardwareDTO>> violations = validator.validate(hardwareDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }
}