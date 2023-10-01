package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.CupomDTO;
import br.unitins.topicos2.dto.CupomResponseDTO;
import br.unitins.topicos2.model.Cupom;
import br.unitins.topicos2.repository.CupomRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CupomServiceImpl implements CupomService {

    @Inject
    CupomRepository cupomRepository;

    @Inject
    Validator validator;

    @Override
    public List<CupomResponseDTO> getAll() {
        List<Cupom> list = cupomRepository.listAll();
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
    @Transactional
    public CupomResponseDTO create(CupomDTO cupomDTO) throws ConstraintViolationException {
        validar(cupomDTO);

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
        validar(cupomDTO);

        Cupom entity = cupomRepository.findById(id);

        entity.setDescricao(cupomDTO.descricao());
        entity.setCodigo(cupomDTO.codigo());
        entity.setInicio(cupomDTO.inicio());
        entity.setTermino(cupomDTO.termino());
        entity.setDesconto(cupomDTO.desconto());

        return CupomResponseDTO.valueOf(entity);
    }

    private void validar(CupomDTO cupomDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<CupomDTO>> violations = validator.validate(cupomDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        cupomRepository.deleteById(id);
    }

    @Override
    public List<CupomResponseDTO> findByCodigo(String codigo) {
        List<Cupom> list = cupomRepository.findByCodigo(codigo);
        return list.stream().map(e -> CupomResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return cupomRepository.count();
    }
}