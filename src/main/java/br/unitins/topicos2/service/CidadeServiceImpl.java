package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.CidadeDTO;
import br.unitins.topicos2.dto.CidadeResponseDTO;
import br.unitins.topicos2.model.Cidade;
import br.unitins.topicos2.repository.CidadeRepository;
import br.unitins.topicos2.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    EstadoRepository estadoRepository;

    @Inject
    Validator validator;

    @Override
    public List<CidadeResponseDTO> getAll() {
        List<Cidade> list = cidadeRepository.listAll();
        return list.stream().map(e -> CidadeResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public CidadeResponseDTO findById(Long id) {
        Cidade cidade = cidadeRepository.findById(id);
        if (cidade == null)
            throw new NotFoundException("Cidade não encontrada.");
        return CidadeResponseDTO.valueOf(cidade);
    }

    @Override
    @Transactional
    public CidadeResponseDTO create(CidadeDTO cidadeDTO) throws ConstraintViolationException {
        validar(cidadeDTO);

        Cidade entity = new Cidade();
        
        entity.setNome(cidadeDTO.nome());
        entity.setEstado(estadoRepository.findById(cidadeDTO.idEstado()));

        cidadeRepository.persist(entity);

        return CidadeResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public CidadeResponseDTO update(Long id, CidadeDTO cidadeDTO) throws ConstraintViolationException {
        validar(cidadeDTO);

        Cidade entity = cidadeRepository.findById(id);

        entity.setNome(cidadeDTO.nome());
        entity.setEstado(estadoRepository.findById(cidadeDTO.idEstado()));
        
        return CidadeResponseDTO.valueOf(entity);
    }

    private void validar(CidadeDTO cidadeDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<CidadeDTO>> violations = validator.validate(cidadeDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        cidadeRepository.deleteById(id);
    }

    @Override
    public List<CidadeResponseDTO> findByNome(String nome) {
        List<Cidade> list = cidadeRepository.findByNome(nome);
        return list.stream().map(e -> CidadeResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return cidadeRepository.count();
    }
}