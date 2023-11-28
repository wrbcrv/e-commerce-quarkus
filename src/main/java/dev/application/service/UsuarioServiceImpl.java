package dev.application.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import dev.application.dto.EnderecoDTO;
import dev.application.dto.TelefoneDTO;
import dev.application.dto.UsuarioDTO;
import dev.application.dto.UsuarioResponseDTO;
import dev.application.model.Endereco;
import dev.application.model.Perfil;
import dev.application.model.Telefone;
import dev.application.model.Usuario;
import dev.application.repository.UsuarioRepository;
import dev.application.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

  @Inject
  UsuarioRepository usuarioRepository;

  @Inject
  HashServiceImpl hashServiceImpl;

  @Override
  @Transactional
  public UsuarioResponseDTO create(@Valid UsuarioDTO usuarioDTO) throws ConstraintViolationException {
    Usuario usuario = new Usuario();

    usuario.setNome(usuarioDTO.nome());
    usuario.setSobrenome(usuarioDTO.sobrenome());
    usuario.setCpf(usuarioDTO.cpf());
    usuario.setRg(usuarioDTO.rg());
    usuario.setLogin(usuarioDTO.login());
    usuario.setSenha(hashServiceImpl.getHashSenha(usuarioDTO.senha()));

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

    usuario.setPerfil(Perfil.valueOf(usuarioDTO.idPerfil()));

    usuarioRepository.persist(usuario);

    return UsuarioResponseDTO.valueOf(usuario);
  }

  @Override
  @Transactional
  public UsuarioResponseDTO update(UsuarioDTO usuarioDTO, Long id) throws ConstraintViolationException {
    Usuario usuarioExistente = usuarioRepository.findById(id);

    usuarioExistente.setNome(usuarioDTO.nome());
    usuarioExistente.setSobrenome(usuarioDTO.sobrenome());
    usuarioExistente.setCpf(usuarioDTO.cpf());
    usuarioExistente.setRg(usuarioDTO.rg());
    usuarioExistente.setLogin(usuarioDTO.login());
    usuarioExistente.setSenha(hashServiceImpl.getHashSenha(usuarioDTO.senha()));

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

    usuarioRepository.persist(usuarioExistente);

    return UsuarioResponseDTO.valueOf(usuarioExistente);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    usuarioRepository.deleteById(id);
  }

  @Override
  public List<UsuarioResponseDTO> findAll(int page, int pageSize) {
    List<Usuario> list = usuarioRepository.findAll().page(page, pageSize).list();

    return list.stream().map(e -> UsuarioResponseDTO.valueOf(e)).collect(Collectors.toList());
  }

  @Override
  public UsuarioResponseDTO findById(Long id) {
    Usuario usuario = usuarioRepository.findById(id);
    if (usuario == null)
      throw new NotFoundException("Usuario não encontrado.");

    return UsuarioResponseDTO.valueOf(usuario);
  }

  @Override
  public UsuarioResponseDTO findByLogin(String login) {
    Usuario usuario = usuarioRepository.findByLogin(login);

    if (usuario == null)
      throw new NotFoundException("Usuário não encontrado.");

    return UsuarioResponseDTO.valueOf(usuario);
  }

  @Override
  public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
    Usuario usuario = usuarioRepository.findByLoginAndSenha(login, senha);
    if (usuario == null)
      throw new ValidationException("login", "Login ou senha inválido");

    return UsuarioResponseDTO.valueOf(usuario);
  }

  @Override
  public List<UsuarioResponseDTO> findByNome(String nome, int page, int pageSize) {
    List<Usuario> list = usuarioRepository.findByNome(nome).page(page, pageSize).list();

    return list.stream().map(e -> UsuarioResponseDTO.valueOf(e)).collect(Collectors.toList());
  }

  @Override
  @Transactional
  public UsuarioResponseDTO saveImage(Long id, String imageName) {
    Usuario entity = usuarioRepository.findById(id);
    entity.setImageName(imageName);

    return UsuarioResponseDTO.valueOf(entity);
  }

  @Override
  @Transactional
  public UsuarioResponseDTO createTelefones(Long usuarioId, List<TelefoneDTO> telefonesDTO) {
    Usuario usuarioExistente = usuarioRepository.findById(usuarioId);

    if (usuarioExistente == null)
      throw new NotFoundException("Usuario não encontrado.");

    List<Telefone> telefonesExistente = usuarioExistente.getTelefones();

    for (TelefoneDTO telefoneDTO : telefonesDTO) {
      Telefone novoTelefone = new Telefone();
      novoTelefone.setDdd(telefoneDTO.ddd());
      novoTelefone.setNumero(telefoneDTO.numero());
      telefonesExistente.add(novoTelefone);
    }

    usuarioRepository.persist(usuarioExistente);

    return UsuarioResponseDTO.valueOf(usuarioExistente);
  }

  @Override
  @Transactional
  public UsuarioResponseDTO updateTelefones(Long usuarioId, List<TelefoneDTO> telefonesDTO) {
    Usuario usuarioExistente = usuarioRepository.findById(usuarioId);

    if (usuarioExistente == null)
      throw new NotFoundException("Usuario não encontrado.");

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

    usuarioRepository.persist(usuarioExistente);

    return UsuarioResponseDTO.valueOf(usuarioExistente);
  }

  @Override
  @Transactional
  public UsuarioResponseDTO removeTelefones(Long usuarioId, Long telefoneId) {
    Usuario usuario = usuarioRepository.findById(usuarioId);

    if (usuario == null) {
      throw new NotFoundException("Usuario não encontrado.");
    }

    List<Telefone> telefones = usuario.getTelefones();

    Iterator<Telefone> iterator = telefones.iterator();
    while (iterator.hasNext()) {
      Telefone telefone = iterator.next();
      if (telefone.getId().equals(telefoneId)) {
        iterator.remove();
        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
      }
    }

    throw new NotFoundException("Telefone não encontrado para este usuário.");
  }

  @Override
  @Transactional
  public UsuarioResponseDTO createEnderecos(Long usuarioId, List<EnderecoDTO> enderecosDTO) {
    Usuario usuarioExistente = usuarioRepository.findById(usuarioId);

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

    usuarioRepository.persist(usuarioExistente);

    return UsuarioResponseDTO.valueOf(usuarioExistente);
  }

  @Override
  @Transactional
  public UsuarioResponseDTO updateEnderecos(Long usuarioId, List<EnderecoDTO> enderecosDTO) {
    Usuario usuarioExistente = usuarioRepository.findById(usuarioId);

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

    usuarioRepository.persist(usuarioExistente);

    return UsuarioResponseDTO.valueOf(usuarioExistente);
  }

  @Override
  @Transactional
  public UsuarioResponseDTO removeEnderecos(Long usuarioId, Long enderecoId) {
    Usuario usuario = usuarioRepository.findById(usuarioId);

    if (usuario == null) {
      throw new NotFoundException("Usuario não encontrado.");
    }

    List<Endereco> enderecos = usuario.getEnderecos();

    Iterator<Endereco> iterator = enderecos.iterator();
    while (iterator.hasNext()) {
      Endereco endereco = iterator.next();
      if (endereco.getId().equals(enderecoId)) {
        iterator.remove();
        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
      }
    }

    throw new NotFoundException("Endereco não encontrado para este usuário.");
  }

  @Override
  public long count() {
    return usuarioRepository.count();
  }

  @Override
  public long countByNome(String nome) {
    return usuarioRepository.findByNome(nome).count();
  }
}