package com.example.estagio.controllers;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.estagio.dto.UploadFileResponseDTO;
import com.example.estagio.exceptions.UploadException;
import com.example.estagio.services.FileStorageService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/file")
public class FileController {
    
    @Autowired
    private FileStorageService service;

    @PostMapping("/uploadFile")
    public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file){
        var fileName = service.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/file/downloadFile/").path(fileName)
            .toUriString();
        return new UploadFileResponseDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultiplesFiles")
    public List<UploadFileResponseDTO> uploadMultiplesFiles(@RequestParam("files") MultipartFile[] files){
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadFile(file))
            .collect(Collectors.toList());
            
    }
    @GetMapping("/downloadFile/{filename:.+}")
    public ResponseEntity<Resource> uploadMultiplesFiles(@PathVariable String filename, HttpServletRequest request){
        Resource resource = service.loadFileAsResource(filename);
        String contentType = "";

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            throw new UploadException("File not Found");
        }
        if (contentType.isBlank()) contentType = "application/octet-stream";

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, 
                "attachment; filename =\""+ resource.getFilename() +"\"")
            .body(resource);
            
    }
    
}
