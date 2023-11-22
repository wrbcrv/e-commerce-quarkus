package dev.application.service;

import java.io.File;
import java.io.IOException;

public interface FileService {

    void save(Long id, String imageName, byte[] image) throws IOException;

    File download(String fileName);
}