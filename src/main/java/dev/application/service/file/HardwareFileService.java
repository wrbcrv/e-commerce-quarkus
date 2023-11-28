package dev.application.service.file;

import java.io.File;
import java.io.IOException;

public interface HardwareFileService {

    void save(Long id, String imageName, byte[] image) throws IOException;

    File download(String fileName);
}