package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.DescricaoDTO;
import br.unitins.topicos2.dto.DescricaoResponseDTO;
import br.unitins.topicos2.model.Descricao;
import br.unitins.topicos2.repository.DescricaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class DescricaoServiceImpl implements DescricaoService {

    @Inject
    DescricaoRepository descricaoRepository;

    @Inject
    Validator validator;

    @Override
    public List<DescricaoResponseDTO> getAll(int page, int pageSize) {
        List<Descricao> list = descricaoRepository.findAll().page(page, pageSize).list();

        return list.stream().map(e -> DescricaoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public DescricaoResponseDTO findById(Long id) {
        Descricao descricao = descricaoRepository.findById(id);
        if (descricao == null)
            throw new NotFoundException("Descricao não encontrada.");

        return DescricaoResponseDTO.valueOf(descricao);
    }

    @Override
    @Transactional
    public DescricaoResponseDTO create(DescricaoDTO descricaoDTO) throws ConstraintViolationException {
        validar(descricaoDTO);

        Descricao entity = new Descricao();
        
        entity.setConteudo(descricaoDTO.conteudo());

        descricaoRepository.persist(entity);

        return DescricaoResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public DescricaoResponseDTO update(Long id, DescricaoDTO descricaoDTO) throws ConstraintViolationException {
        validar(descricaoDTO);

        Descricao entity = descricaoRepository.findById(id);

        entity.setConteudo(descricaoDTO.conteudo());
        
        return DescricaoResponseDTO.valueOf(entity);
    }

    private void validar(DescricaoDTO descricaoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<DescricaoDTO>> violations = validator.validate(descricaoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        descricaoRepository.deleteById(id);
    }

    @Override
    public List<DescricaoResponseDTO> findByConteudo(String conteudo, int page, int pageSize) {
        List<Descricao> list = descricaoRepository.findByConteudo(conteudo).page(page, pageSize).list();

        return list.stream().map(e -> DescricaoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return descricaoRepository.count();
    }

    @Override
    public long countByNome(String conteudo) {
        return descricaoRepository.findByConteudo(conteudo).count();
    }
}