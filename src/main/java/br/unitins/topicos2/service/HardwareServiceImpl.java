package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.HardwareDTO;
import br.unitins.topicos2.dto.HardwareResponseDTO;
import br.unitins.topicos2.model.Categoria;
import br.unitins.topicos2.model.Hardware;
import br.unitins.topicos2.model.Integridade;
import br.unitins.topicos2.repository.DescricaoRepository;
import br.unitins.topicos2.repository.HardwareRepository;
import br.unitins.topicos2.repository.MarcaRepository;
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
    DescricaoRepository descricaoRepository;

    @Inject
    Validator validator;

    @Override
    public List<HardwareResponseDTO> getAll(int page, int pageSize) {
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
    @Transactional
    public HardwareResponseDTO create(HardwareDTO hardwareDTO) throws ConstraintViolationException {
        validar(hardwareDTO);

        Hardware entity = new Hardware();

        entity.setMarca(marcaRepository.findById(hardwareDTO.idMarca()));
        entity.setModelo(hardwareDTO.modelo());
        entity.setLancamento(hardwareDTO.lancamento());
        entity.setDescricao(descricaoRepository.findById(hardwareDTO.idDescricao()));
        entity.setIntegridade(Integridade.valueOf(hardwareDTO.idIntegridade()));
        entity.setCategoria(Categoria.valueOf(hardwareDTO.idCategoria()));
        entity.setNome(hardwareDTO.nome());
        entity.setPreco(hardwareDTO.preco());
        entity.setEstoque(hardwareDTO.estoque());

        hardwareRepository.persist(entity);

        return HardwareResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public HardwareResponseDTO update(Long id, HardwareDTO hardwareDTO) throws ConstraintViolationException {
        validar(hardwareDTO);

        Hardware entity = hardwareRepository.findById(id);

        entity.setMarca(marcaRepository.findById(hardwareDTO.idMarca()));
        entity.setModelo(hardwareDTO.modelo());
        entity.setLancamento(hardwareDTO.lancamento());
        entity.setDescricao(descricaoRepository.findById(hardwareDTO.idDescricao()));
        entity.setIntegridade(Integridade.valueOf(hardwareDTO.idIntegridade()));
        entity.setCategoria(Categoria.valueOf(hardwareDTO.idCategoria()));
        entity.setNome(hardwareDTO.nome());
        entity.setPreco(hardwareDTO.preco());
        entity.setEstoque(hardwareDTO.estoque());

        return HardwareResponseDTO.valueOf(entity);
    }

    private void validar(HardwareDTO hardwareDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<HardwareDTO>> violations = validator.validate(hardwareDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        hardwareRepository.deleteById(id);
    }

    @Override
    public List<HardwareResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Hardware> list = hardwareRepository.findByNome(nome).page(page, pageSize).list();

        return list.stream().map(e -> HardwareResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return hardwareRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return hardwareRepository.findByNome(nome).count();
    }
}