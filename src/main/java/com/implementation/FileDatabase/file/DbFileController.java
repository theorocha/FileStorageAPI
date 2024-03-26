package com.implementation.FileDatabase.file;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
public class DbFileController {

   @Autowired
   DbFileService dbFileService;

    //Download By Id;
    @GetMapping("/downloadFile/{fileId:.+}")
    @Operation(summary = "Realiza download de um arquivo pelo Id.")
    public ResponseEntity<Resource> downloadFileById(@PathVariable String fileId, HttpServletRequest request) {
        // Load file as Resource
        DbFile databaseFile = dbFileService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
                .body(new ByteArrayResource(databaseFile.getData()));
    }

    //Post file;
    @PostMapping("/uploadFile")
    @Operation(summary = "Realiza upload de um arquivo.")
    public DbFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        DbFile fileName = dbFileService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();

        return new DbFileResponse(fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    //Post Multiple Files;
    @PostMapping("/uploadMultipleFiles")
    @Operation(summary = "Realiza upload de vários arquivos.")
    public List<DbFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
}
