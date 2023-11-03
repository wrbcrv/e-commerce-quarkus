package dev.application.service;

import java.io.File;
import java.io.IOException;

public interface FileService {

    String save(byte[] image, String imageName) throws IOException;

    File download(String fileName);
}