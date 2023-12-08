package dev.application.service.file;

import java.io.File;
import java.io.IOException;

public interface HardwareFileService {

    void save(Long id, String imageNames, byte[] images) throws IOException;

    File download(String fileName);
}