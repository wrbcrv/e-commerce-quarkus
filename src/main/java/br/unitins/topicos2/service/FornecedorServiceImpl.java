package br.unitins.topicos2.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.EnderecoDTO;
import br.unitins.topicos2.dto.FornecedorDTO;
import br.unitins.topicos2.dto.FornecedorResponseDTO;
import br.unitins.topicos2.model.Endereco;
import br.unitins.topicos2.model.Fornecedor;
import br.unitins.topicos2.model.Hardware;
import br.unitins.topicos2.repository.EstadoRepository;
import br.unitins.topicos2.repository.FornecedorRepository;
import br.unitins.topicos2.repository.HardwareRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    EstadoRepository estadoRepository;

     @Inject
    HardwareRepository hardwareRepository;

    @Inject
    Validator validator;

    @Override
    public List<FornecedorResponseDTO> getAll(int page, int pageSize) {
        List<Fornecedor> list = fornecedorRepository.findAll().page(page, pageSize).list();

        return list.stream().map(e -> FornecedorResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public FornecedorResponseDTO findById(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id);
        if (fornecedor == null)
            throw new NotFoundException("Fornecedor não encontrada.");

        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO create(FornecedorDTO fornecedorDTO) throws ConstraintViolationException {
        validar(fornecedorDTO);

        Fornecedor entity = new Fornecedor();

        entity.setNome(fornecedorDTO.nome());

        if (fornecedorDTO.enderecos() != null && !fornecedorDTO.enderecos().isEmpty()) {
            entity.setEnderecos(new ArrayList<Endereco>());

            for (EnderecoDTO enderecoDTO : fornecedorDTO.enderecos()) {
                Endereco endereco = new Endereco();
                endereco.setLogradouro(enderecoDTO.logradouro());
                endereco.setNumero(enderecoDTO.numero());
                endereco.setComplemento(enderecoDTO.complemento());
                endereco.setBairro(enderecoDTO.bairro());
                endereco.setCep(enderecoDTO.cep());
                entity.getEnderecos().add(endereco);
            }
        }

        fornecedorRepository.persist(entity);

        return FornecedorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO update(Long id, FornecedorDTO fornecedorDTO) throws ConstraintViolationException {
        validar(fornecedorDTO);

        Fornecedor entity = fornecedorRepository.findById(id);
        entity.setNome(fornecedorDTO.nome());

        List<Endereco> enderecos = entity.getEnderecos();
        List<EnderecoDTO> enderecosDTO = fornecedorDTO.enderecos();

        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            boolean foundEndereco = false;
            for (Endereco endereco : enderecos) {
                if (endereco.getId().equals(enderecoDTO.id())) {
                    endereco.setLogradouro(enderecoDTO.logradouro());
                    endereco.setNumero(enderecoDTO.numero());
                    endereco.setComplemento(enderecoDTO.complemento());
                    endereco.setBairro(enderecoDTO.bairro());
                    endereco.setCep(enderecoDTO.cep());
                    foundEndereco = true;
                    break;
                }
            }

            if (!foundEndereco) {
                Endereco endereco = new Endereco();
                endereco.setLogradouro(enderecoDTO.logradouro());
                endereco.setNumero(enderecoDTO.numero());
                endereco.setComplemento(enderecoDTO.complemento());
                endereco.setBairro(enderecoDTO.bairro());
                endereco.setCep(enderecoDTO.cep());
                entity.getEnderecos().add(endereco);
            }
        }

        fornecedorRepository.persist(entity);

        return FornecedorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO createEnderecos(Long fornecedorId, List<EnderecoDTO> enderecosDTO) {
        Fornecedor entity = fornecedorRepository.findById(fornecedorId);

        if (entity == null) {
            throw new NotFoundException("Fornecedor não encontrado.");
        }

        List<Endereco> enderecos = entity.getEnderecos();

        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            Endereco endereco = new Endereco();
            endereco.setLogradouro(enderecoDTO.logradouro());
            endereco.setNumero(enderecoDTO.numero());
            endereco.setComplemento(enderecoDTO.complemento());
            endereco.setBairro(enderecoDTO.bairro());
            endereco.setCep(enderecoDTO.cep());
            enderecos.add(endereco);
        }

        fornecedorRepository.persist(entity);

        return FornecedorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO updateEnderecos(Long fornecedorId, List<EnderecoDTO> enderecosDTO) {
        Fornecedor entity = fornecedorRepository.findById(fornecedorId);

        if (entity == null) {
            throw new NotFoundException("Fornecedor não encontrado.");
        }

        List<Endereco> enderecos = entity.getEnderecos();

        Iterator<Endereco> iterator = enderecos.iterator();
        while (iterator.hasNext()) {
            Endereco endereco = iterator.next();
            boolean foundEndereco = false;

            for (EnderecoDTO enderecoDTO : enderecosDTO) {
                if (endereco.getId().equals(enderecoDTO.id())) {
                    endereco.setLogradouro(enderecoDTO.logradouro());
                    endereco.setNumero(enderecoDTO.numero());
                    endereco.setComplemento(enderecoDTO.complemento());
                    endereco.setBairro(enderecoDTO.bairro());
                    endereco.setCep(enderecoDTO.cep());

                    foundEndereco = true;

                    break;
                }
            }

            if (!foundEndereco) {
                iterator.remove();
            }
        }

        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            boolean foundEndereco = false;
            for (Endereco endereco : enderecos) {
                if (endereco.getId().equals(enderecoDTO.id())) {
                    endereco.setLogradouro(enderecoDTO.logradouro());
                    endereco.setNumero(enderecoDTO.numero());
                    endereco.setComplemento(enderecoDTO.complemento());
                    endereco.setBairro(enderecoDTO.bairro());
                    endereco.setCep(enderecoDTO.cep());

                    foundEndereco = true;

                    break;
                }
            }

            if (!foundEndereco) {
                Endereco endereco = new Endereco();
                endereco.setLogradouro(enderecoDTO.logradouro());
                endereco.setNumero(enderecoDTO.numero());
                endereco.setComplemento(enderecoDTO.complemento());
                endereco.setBairro(enderecoDTO.bairro());
                endereco.setCep(enderecoDTO.cep());
                entity.getEnderecos().add(endereco);
            }
        }

        fornecedorRepository.persist(entity);

        return FornecedorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO removeEnderecos(Long fornecedorId, Long enderecoId) {
        Fornecedor usuario = fornecedorRepository.findById(fornecedorId);

        if (usuario == null) {
            throw new NotFoundException("Fornecedor não encontrado.");
        }

        List<Endereco> enderecos = usuario.getEnderecos();

        Iterator<Endereco> iterator = enderecos.iterator();
        while (iterator.hasNext()) {
            Endereco endereco = iterator.next();
            if (endereco.getId().equals(enderecoId)) {
                iterator.remove();
                fornecedorRepository.persist(usuario);
                return FornecedorResponseDTO.valueOf(usuario);
            }
        }

        throw new NotFoundException("Endereco não encontrado para este usuário.");
    }

    private void validar(FornecedorDTO fornecedorDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<FornecedorDTO>> violations = validator.validate(fornecedorDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }

    @Override
    public List<FornecedorResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Fornecedor> list = fornecedorRepository.findByNome(nome).page(page, pageSize).list();

        return list.stream().map(e -> FornecedorResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return fornecedorRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return fornecedorRepository.findByNome(nome).count();
    }

    @Transactional
    public FornecedorResponseDTO associarHardware(Long fornecedorId, Long hardwareId) {
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId);
        if (fornecedor == null)
            throw new NotFoundException("Fornecedor não encontrado.");

        Hardware hardware = hardwareRepository.findById(hardwareId);
        if (hardware == null)
            throw new NotFoundException("Hardware não encontrado.");

        List<Hardware> hardwares = fornecedor.getHardwares();
        if (!hardwares.contains(hardware))
            hardwares.add(hardware);

        hardwareRepository.persist(hardware);

        return FornecedorResponseDTO.valueOf(fornecedor);
    }
}