package com.implementation.FileDatabase.file;

import com.implementation.FileDatabase.exceptions.FileNotFoundException;
import com.implementation.FileDatabase.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class DbFileService {
    @Autowired
    private DbFileRepository dbFileRepository;

    public DbFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            // Check if the file's name contains invalid characters,
            // o que pode indicar uma tentativa de navegar para cima no sistema de arquivos.
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DbFile dbFile = new DbFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DbFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}
