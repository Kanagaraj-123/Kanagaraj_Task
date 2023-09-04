package com.example.passwordManager.controller;

import com.example.passwordManager.Entity.Files;
import com.example.passwordManager.Entity.Projects;
import com.example.passwordManager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.Optional;

@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("/add")
    public ResponseEntity<Object> addDetails(@RequestBody Projects projectDetails) {
        projectService.addDetails(projectDetails);
        return new ResponseEntity<Object>("The details Uploded succesfully", HttpStatus.OK);
    }

    @GetMapping("/fetch/{domainName}")
    public ResponseEntity<Projects> fetchDetails(@PathVariable String domainName) {
        Projects projects = projectService.fetchProjectDetails(domainName);
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<Object> fileUpload(@RequestParam("File") MultipartFile file) {
        try {
            Files files = new Files();
            files.setFileName(file.getOriginalFilename());
            files.setFileType(file.getContentType());
           // files.setFileContent(new SerialBlob(file.getBytes())); // Import java.sql.Blob
            files.setData(file.getBytes());
            projectService.saveFile(files);
            return new ResponseEntity<>("The File Uploaded Successfully", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("Failed to upload the file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) {
        Optional<Files> fileEntity = projectService.getFile(fileId);
        if (fileEntity != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.get().getFileName()+ "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileEntity.get().getData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}




