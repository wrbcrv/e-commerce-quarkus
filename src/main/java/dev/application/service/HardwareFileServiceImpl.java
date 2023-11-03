package dev.application.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HardwareFileServiceImpl implements FileService {

    private final String PATH_USER = System.getProperty("user.home")
            + File.separator + "api"
            + File.separator + "images"
            + File.separator + "hardware" + File.separator;

    @Override
    public String save(byte[] image, String imageName) throws IOException {
        String mimeType = Files.probeContentType(new File(imageName).toPath());
        List<String> mimeTypeList = Arrays.asList("image/png", "image/jpg", "image/gif");

        if (!mimeTypeList.contains(mimeType)) 
            throw new IOException("Tipo de imagem não suportado.");

        if (image.length > (1024 * 1024 * 10))
            throw new IOException("Arquivo muito grande.");

        File directory = new File(PATH_USER);
        if (!directory.exists());
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