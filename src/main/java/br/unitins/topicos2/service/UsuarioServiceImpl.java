package br.unitins.topicos2.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos2.dto.EnderecoDTO;
import br.unitins.topicos2.dto.TelefoneDTO;
import br.unitins.topicos2.dto.UsuarioDTO;
import br.unitins.topicos2.dto.UsuarioResponseDTO;
import br.unitins.topicos2.model.Endereco;
import br.unitins.topicos2.model.Telefone;
import br.unitins.topicos2.model.Usuario;
import br.unitins.topicos2.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Override
    public List<UsuarioResponseDTO> getAll(int page, int pageSize) {
        List<Usuario> list = repository.findAll().page(page, pageSize).list();

        return list.stream().map(e -> UsuarioResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UsuarioResponseDTO insert(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDTO.nome());
        usuario.setLogin(usuarioDTO.login());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setCpf(usuarioDTO.cpf());

        if (usuarioDTO.telefones() != null && !usuarioDTO.telefones().isEmpty()) {
            usuario.setTelefones(new ArrayList<Telefone>());

            for (TelefoneDTO telefoneDTO : usuarioDTO.telefones()) {
                Telefone telefone = new Telefone();
                telefone.setDdd(telefoneDTO.ddd());
                telefone.setNumero(telefoneDTO.numero());
                usuario.getTelefones().add(telefone);
            }
        }

        if (usuarioDTO.enderecos() != null && !usuarioDTO.enderecos().isEmpty()) {
            usuario.setEnderecos(new ArrayList<Endereco>());

            for (EnderecoDTO enderecoDTO : usuarioDTO.enderecos()) {
                Endereco endereco = new Endereco();
                endereco.setLogradouro(enderecoDTO.logradouro());
                endereco.setNumero(enderecoDTO.numero());
                endereco.setComplemento(enderecoDTO.complemento());
                endereco.setBairro(enderecoDTO.bairro());
                endereco.setCep(enderecoDTO.cep());
                usuario.getEnderecos().add(endereco);
            }
        }

        repository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = repository.findById(id);
        if (usuario == null)
            throw new NotFoundException("Usuario não encontrado.");

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO usuarioDTO, Long id) throws ConstraintViolationException {
        Usuario usuarioExistente = repository.findById(id);

        usuarioExistente.setNome(usuarioDTO.nome());
        usuarioExistente.setNome(usuarioDTO.nome());
        usuarioExistente.setLogin(usuarioDTO.login());
        usuarioExistente.setSenha(usuarioDTO.senha());
        usuarioExistente.setCpf(usuarioDTO.cpf());

        List<Telefone> telefonesExistente = usuarioExistente.getTelefones();
        List<TelefoneDTO> telefonesDTO = usuarioDTO.telefones();

        List<Endereco> enderecosExistente = usuarioExistente.getEnderecos();
        List<EnderecoDTO> enderecosDTO = usuarioDTO.enderecos();

        for (TelefoneDTO telefoneDTO : telefonesDTO) {
            boolean telefoneEncontrado = false;
            for (Telefone telefone : telefonesExistente) {
                if (telefone.getId().equals(telefoneDTO.id())) {
                    telefone.setDdd(telefoneDTO.ddd());
                    telefone.setNumero(telefoneDTO.numero());
                    telefoneEncontrado = true;
                    break;
                }
            }

            if (!telefoneEncontrado) {
                Telefone novoTelefone = new Telefone();
                novoTelefone.setDdd(telefoneDTO.ddd());
                novoTelefone.setNumero(telefoneDTO.numero());
                usuarioExistente.getTelefones().add(novoTelefone);
            }
        }

        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            boolean enderecoEncontrado = false;
            for (Endereco endereco : enderecosExistente) {
                if (endereco.getId().equals(enderecoDTO.id())) {
                    endereco.setLogradouro(enderecoDTO.logradouro());
                    endereco.setNumero(enderecoDTO.numero());
                    endereco.setComplemento(enderecoDTO.complemento());
                    endereco.setBairro(enderecoDTO.bairro());
                    endereco.setCep(enderecoDTO.cep());
                    enderecoEncontrado = true;
                    break;
                }
            }

            if (!enderecoEncontrado) {
                Endereco novoEndereco = new Endereco();
                novoEndereco.setLogradouro(enderecoDTO.logradouro());
                novoEndereco.setNumero(enderecoDTO.numero());
                novoEndereco.setComplemento(enderecoDTO.complemento());
                novoEndereco.setBairro(enderecoDTO.bairro());
                novoEndereco.setCep(enderecoDTO.cep());
                usuarioExistente.getEnderecos().add(novoEndereco);
            }
        }

        repository.persist(usuarioExistente);

        return UsuarioResponseDTO.valueOf(usuarioExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO createTelefones(Long usuarioId, List<TelefoneDTO> telefonesDTO) {
        Usuario usuarioExistente = repository.findById(usuarioId);

        if (usuarioExistente == null) {
            throw new NotFoundException("Usuario não encontrado.");
        }

        List<Telefone> telefonesExistente = usuarioExistente.getTelefones();

        for (TelefoneDTO telefoneDTO : telefonesDTO) {
            Telefone novoTelefone = new Telefone();
            novoTelefone.setDdd(telefoneDTO.ddd());
            novoTelefone.setNumero(telefoneDTO.numero());
            telefonesExistente.add(novoTelefone);
        }

        repository.persist(usuarioExistente);

        return UsuarioResponseDTO.valueOf(usuarioExistente);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateTelefones(Long usuarioId, List<TelefoneDTO> telefonesDTO) {
        Usuario usuarioExistente = repository.findById(usuarioId);

        if (usuarioExistente == null) {
            throw new NotFoundException("Usuario não encontrado.");
        }

        List<Telefone> telefonesExistente = usuarioExistente.getTelefones();

        Iterator<Telefone> iterator = telefonesExistente.iterator();
        while (iterator.hasNext()) {
            Telefone telefone = iterator.next();
            boolean telefoneEncontrado = false;

            for (TelefoneDTO telefoneDTO : telefonesDTO) {
                if (telefone.getId().equals(telefoneDTO.id())) {
                    telefone.setDdd(telefoneDTO.ddd());
                    telefone.setNumero(telefoneDTO.numero());

                    telefoneEncontrado = true;

                    break;
                }
            }

            if (!telefoneEncontrado) {
                iterator.remove();
            }
        }

        for (TelefoneDTO telefoneDTO : telefonesDTO) {
            boolean telefoneEncontrado = false;
            for (Telefone telefone : telefonesExistente) {
                if (telefone.getId().equals(telefoneDTO.id())) {
                    telefone.setDdd(telefoneDTO.ddd());
                    telefone.setNumero(telefoneDTO.numero());

                    telefoneEncontrado = true;

                    break;
                }
            }

            if (!telefoneEncontrado) {
                Telefone novoTelefone = new Telefone();
                novoTelefone.setDdd(telefoneDTO.ddd());
                novoTelefone.setNumero(telefoneDTO.numero());
                usuarioExistente.getTelefones().add(novoTelefone);
            }
        }

        repository.persist(usuarioExistente);

        return UsuarioResponseDTO.valueOf(usuarioExistente);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO removeTelefones(Long usuarioId, Long telefoneId) {
        Usuario usuario = repository.findById(usuarioId);

        if (usuario == null) {
            throw new NotFoundException("Usuario não encontrado.");
        }

        List<Telefone> telefones = usuario.getTelefones();

        Iterator<Telefone> iterator = telefones.iterator();
        while (iterator.hasNext()) {
            Telefone telefone = iterator.next();
            if (telefone.getId().equals(telefoneId)) {
                iterator.remove();
                repository.persist(usuario);
                return UsuarioResponseDTO.valueOf(usuario);
            }
        }

        throw new NotFoundException("Telefone não encontrado para este usuário.");
    }

    @Override
    @Transactional
    public UsuarioResponseDTO createEnderecos(Long usuarioId, List<EnderecoDTO> enderecosDTO) {
        Usuario usuarioExistente = repository.findById(usuarioId);

        if (usuarioExistente == null) {
            throw new NotFoundException("Usuario não encontrado.");
        }

        List<Endereco> enderecosExistente = usuarioExistente.getEnderecos();

        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            Endereco novoEndereco = new Endereco();
            novoEndereco.setLogradouro(enderecoDTO.logradouro());
            novoEndereco.setNumero(enderecoDTO.numero());
            novoEndereco.setComplemento(enderecoDTO.complemento());
            novoEndereco.setBairro(enderecoDTO.bairro());
            novoEndereco.setCep(enderecoDTO.cep());
            enderecosExistente.add(novoEndereco);
        }

        repository.persist(usuarioExistente);

        return UsuarioResponseDTO.valueOf(usuarioExistente);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateEnderecos(Long usuarioId, List<EnderecoDTO> enderecosDTO) {
        Usuario usuarioExistente = repository.findById(usuarioId);

        if (usuarioExistente == null) {
            throw new NotFoundException("Usuario não encontrado.");
        }

        List<Endereco> enderecosExistente = usuarioExistente.getEnderecos();

        Iterator<Endereco> iterator = enderecosExistente.iterator();
        while (iterator.hasNext()) {
            Endereco endereco = iterator.next();
            boolean enderecoEncontrado = false;

            for (EnderecoDTO enderecoDTO : enderecosDTO) {
                if (endereco.getId().equals(enderecoDTO.id())) {
                    endereco.setLogradouro(enderecoDTO.logradouro());
                    endereco.setNumero(enderecoDTO.numero());
                    endereco.setComplemento(enderecoDTO.complemento());
                    endereco.setBairro(enderecoDTO.bairro());
                    endereco.setCep(enderecoDTO.cep());

                    enderecoEncontrado = true;

                    break;
                }
            }

            if (!enderecoEncontrado) {
                iterator.remove();
            }
        }

        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            boolean enderecoEncontrado = false;
            for (Endereco endereco : enderecosExistente) {
                if (endereco.getId().equals(enderecoDTO.id())) {
                    endereco.setLogradouro(enderecoDTO.logradouro());
                    endereco.setNumero(enderecoDTO.numero());
                    endereco.setComplemento(enderecoDTO.complemento());
                    endereco.setBairro(enderecoDTO.bairro());
                    endereco.setCep(enderecoDTO.cep());

                    enderecoEncontrado = true;

                    break;
                }
            }

            if (!enderecoEncontrado) {
                Endereco novoEndereco = new Endereco();
                novoEndereco.setLogradouro(enderecoDTO.logradouro());
                novoEndereco.setNumero(enderecoDTO.numero());
                novoEndereco.setComplemento(enderecoDTO.complemento());
                novoEndereco.setBairro(enderecoDTO.bairro());
                novoEndereco.setCep(enderecoDTO.cep());
                usuarioExistente.getEnderecos().add(novoEndereco);
            }
        }

        repository.persist(usuarioExistente);

        return UsuarioResponseDTO.valueOf(usuarioExistente);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO removeEnderecos(Long usuarioId, Long enderecoId) {
        Usuario usuario = repository.findById(usuarioId);

        if (usuario == null) {
            throw new NotFoundException("Usuario não encontrado.");
        }

        List<Endereco> enderecos = usuario.getEnderecos();

        Iterator<Endereco> iterator = enderecos.iterator();
        while (iterator.hasNext()) {
            Endereco endereco = iterator.next();
            if (endereco.getId().equals(enderecoId)) {
                iterator.remove();
                repository.persist(usuario);
                return UsuarioResponseDTO.valueOf(usuario);
            }
        }

        throw new NotFoundException("Endereco não encontrado para este usuário.");
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Usuario> list = repository.findByNome(nome).page(page, pageSize).list();

        return list.stream().map(e -> UsuarioResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long countByNome(String nome) {
        return repository.findByNome(nome).count();
    }
}