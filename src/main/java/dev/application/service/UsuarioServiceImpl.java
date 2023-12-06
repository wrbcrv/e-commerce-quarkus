package dev.application.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.application.dto.EnderecoDTO;
import dev.application.dto.UsuarioDTO;
import dev.application.dto.UsuarioResponseDTO;
import dev.application.model.Cidade;
import dev.application.model.Endereco;
import dev.application.model.Perfil;
import dev.application.model.Usuario;
import dev.application.repository.CidadeRepository;
import dev.application.repository.EnderecoRepository;
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

  @Inject
  CidadeRepository cidadeRepository;

  @Inject
  EnderecoRepository enderecoRepository;

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

    if (usuarioDTO.enderecos() != null && !usuarioDTO.enderecos().isEmpty()) {
      usuario.setEnderecos(new ArrayList<Endereco>());

      for (EnderecoDTO enderecoDTO : usuarioDTO.enderecos()) {
        Endereco endereco = new Endereco();

        usuario.setNome(enderecoDTO.nome());
        usuario.setSobrenome(enderecoDTO.sobrenome());
        endereco.setCep(enderecoDTO.cep());
        endereco.setEndereco(enderecoDTO.endereco());
        endereco.setNumero(enderecoDTO.numero());
        endereco.setBairro(enderecoDTO.bairro());
        endereco.setComplemento(enderecoDTO.complemento());
        endereco.setTelefone(enderecoDTO.telefone());

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

    List<Endereco> enderecosExistente = usuarioExistente.getEnderecos();
    List<EnderecoDTO> enderecosDTO = usuarioDTO.enderecos();

    for (EnderecoDTO enderecoDTO : enderecosDTO) {
      boolean enderecoEncontrado = false;
      for (Endereco endereco : enderecosExistente) {
        if (endereco.getId().equals(enderecoDTO.id())) {

          endereco.setCep(enderecoDTO.cep());
          endereco.setEndereco(enderecoDTO.endereco());
          endereco.setNumero(enderecoDTO.numero());
          endereco.setBairro(enderecoDTO.bairro());
          endereco.setComplemento(enderecoDTO.complemento());
          endereco.setTelefone(enderecoDTO.telefone());

          enderecoEncontrado = true;

          break;
        }
      }

      if (!enderecoEncontrado) {
        Endereco novoEndereco = new Endereco();

        novoEndereco.setCep(enderecoDTO.cep());
        novoEndereco.setEndereco(enderecoDTO.endereco());
        novoEndereco.setNumero(enderecoDTO.numero());
        novoEndereco.setBairro(enderecoDTO.bairro());
        novoEndereco.setComplemento(enderecoDTO.complemento());
        novoEndereco.setTelefone(enderecoDTO.telefone());

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
  public UsuarioResponseDTO createEnderecos(Long usuarioId, List<EnderecoDTO> enderecosDTO) {
    Usuario usuarioExistente = usuarioRepository.findById(usuarioId);

    if (usuarioExistente == null) {
      throw new NotFoundException("Usuario não encontrado.");
    }

    List<Endereco> enderecosExistente = usuarioExistente.getEnderecos();

    for (EnderecoDTO enderecoDTO : enderecosDTO) {
      Endereco novoEndereco = new Endereco();

      novoEndereco.setNome(enderecoDTO.nome());
      novoEndereco.setSobrenome(enderecoDTO.sobrenome());
      novoEndereco.setCep(enderecoDTO.cep());
      novoEndereco.setEndereco(enderecoDTO.endereco());
      novoEndereco.setNumero(enderecoDTO.numero());
      novoEndereco.setBairro(enderecoDTO.bairro());
      novoEndereco.setComplemento(enderecoDTO.complemento());
      Cidade cidade = cidadeRepository.findById(enderecoDTO.cidade().id());

      if (cidade == null)
        throw new NotFoundException("Cidade não encontrada");

      novoEndereco.setCidade(cidade);
      novoEndereco.setTelefone(enderecoDTO.telefone());

      enderecosExistente.add(novoEndereco);
    }

    usuarioRepository.persist(usuarioExistente);

    return UsuarioResponseDTO.valueOf(usuarioExistente);
  }

  @Override
  @Transactional
  public UsuarioResponseDTO updateEndereco(Long usuarioId, Long enderecoId, EnderecoDTO enderecoDTO) {
    Usuario usuarioExistente = usuarioRepository.findById(usuarioId);

    if (usuarioExistente == null) {
      throw new NotFoundException("Usuário não encontrado.");
    }

    Optional<Endereco> enderecoOptional = usuarioExistente.getEnderecos().stream()
        .filter(endereco -> endereco.getId().equals(enderecoId))
        .findFirst();

    if (enderecoOptional.isPresent()) {
      Endereco enderecoExistente = enderecoOptional.get();

      enderecoExistente.setNome(enderecoDTO.nome());
      enderecoExistente.setSobrenome(enderecoDTO.sobrenome());
      enderecoExistente.setCep(enderecoDTO.cep());
      enderecoExistente.setEndereco(enderecoDTO.endereco());
      enderecoExistente.setNumero(enderecoDTO.numero());
      enderecoExistente.setBairro(enderecoDTO.bairro());
      enderecoExistente.setComplemento(enderecoDTO.complemento());

      Cidade cidade = cidadeRepository.findById(enderecoDTO.cidade().id());

      if (cidade == null)
        throw new NotFoundException("Cidade não encontrada");

      enderecoExistente.setCidade(cidade);
      enderecoExistente.setTelefone(enderecoDTO.telefone());
    } else {
      throw new NotFoundException("Endereço não encontrado para o usuário especificado.");
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

  @Override
  public EnderecoDTO findEnderecoByUsuarioId(Long usuarioId, Long enderecoId) {
    Usuario usuario = usuarioRepository.findById(usuarioId);

    if (usuario == null) {
      throw new NotFoundException("Usuário não encontrado.");
    }

    Optional<Endereco> optional = usuario.getEnderecos().stream()
        .filter(endereco -> endereco.getId().equals(enderecoId))
        .findFirst();

    if (optional.isPresent()) {
      Endereco endereco = optional.get();
      return EnderecoDTO.valueOf(endereco); 
    } else {
      throw new NotFoundException("Endereço não encontrado para o usuário especificado.");
    }
  }
}