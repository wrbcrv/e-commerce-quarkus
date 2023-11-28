package dev.application.service.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import dev.application.model.Usuario;
import dev.application.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioFileServiceImpl implements UsuarioFileService {

  private final String PATH_USER = System.getProperty("user.home")
      + File.separator + "api"
      + File.separator + "images" + File.separator;

  @Inject
  UsuarioRepository usuarioRepository;

  @Override
  @Transactional
  public void save(Long id, String imageName, byte[] image) throws IOException {
    Usuario usuario = usuarioRepository.findById(id);

    try {
      String newImageName = saveImage(image, imageName);
      usuario.setImageName(newImageName);
    } catch (IOException e) {
      throw e;
    }
  }

  private String saveImage(byte[] image, String imageName) throws IOException {
    String mimeType = Files.probeContentType(new File(imageName).toPath());
    List<String> mimeTypeList = Arrays.asList("image/png", "image/jpeg", "image/jpg", "image/gif");

    if (!mimeTypeList.contains(mimeType))
      throw new IOException("Tipo de imagem não suportado.");

    if (image.length > (1024 * 1024 * 10))
      throw new IOException("Arquivo muito grande.");

    File directory = new File(PATH_USER);
    if (!directory.exists())
      directory.mkdirs();

    String fileName = UUID.randomUUID() + "." + mimeType.substring(mimeType.lastIndexOf("/") + 1);
    String path = PATH_USER + fileName;

    File file = new File(path);
    if (file.exists())
      throw new IOException("O nome da imagem está repetido.");

    file.createNewFile();

    FileOutputStream fileOutputStream = new FileOutputStream(file);
    fileOutputStream.write(image);
    fileOutputStream.flush();
    fileOutputStream.close();

    return fileName;
  }

  @Override
  public File download(String fileName) {
    File file = new File(PATH_USER + fileName);

    return file;
  }
}