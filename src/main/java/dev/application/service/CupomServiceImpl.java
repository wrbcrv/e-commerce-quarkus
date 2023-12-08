package dev.application.service;

import java.util.List;

import java.util.stream.Collectors;

import dev.application.dto.CupomDTO;
import dev.application.dto.CupomResponseDTO;
import dev.application.model.Cupom;
import dev.application.repository.CupomRepository;
import dev.application.repository.HardwareRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CupomServiceImpl implements CupomService {

    @Inject
    CupomRepository cupomRepository;

    @Inject
    HardwareRepository hardwareRepository;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public CupomResponseDTO create(CupomDTO cupomDTO) throws ConstraintViolationException {
        Cupom entity = new Cupom();

        entity.setDescricao(cupomDTO.descricao());
        entity.setCodigo(cupomDTO.codigo());
        entity.setInicio(cupomDTO.inicio());
        entity.setTermino(cupomDTO.termino());
        entity.setDesconto(cupomDTO.desconto());

        cupomRepository.persist(entity);

        return CupomResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public CupomResponseDTO update(Long id, CupomDTO cupomDTO) throws ConstraintViolationException {
        Cupom entity = cupomRepository.findById(id);

        entity.setDescricao(cupomDTO.descricao());
        entity.setCodigo(cupomDTO.codigo());
        entity.setInicio(cupomDTO.inicio());
        entity.setTermino(cupomDTO.termino());
        entity.setDesconto(cupomDTO.desconto());

        return CupomResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cupomRepository.deleteById(id);
    }

    @Override
    public List<CupomResponseDTO> findAll(int page, int pageSize) {
        List<Cupom> list = cupomRepository.findAll().page(page, pageSize).list();

        return list.stream().map(e -> CupomResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public CupomResponseDTO findById(Long id) {
        Cupom cupom = cupomRepository.findById(id);
        if (cupom == null)
            throw new NotFoundException("Cupom não encontrado.");

        return CupomResponseDTO.valueOf(cupom);
    }

    @Override
    public CupomResponseDTO findByCodigo(String codigo) {
        Cupom cupom = cupomRepository.findByCodigo(codigo);
        
        return CupomResponseDTO.valueOf(cupom);
    }

    @Override
    public long count() {
        return cupomRepository.count();
    }
}