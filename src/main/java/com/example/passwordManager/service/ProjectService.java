package com.example.passwordManager.service;

import com.example.passwordManager.Entity.Files;
import com.example.passwordManager.Entity.Projects;
import com.example.passwordManager.repository.FileRepository;
import com.example.passwordManager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    FileRepository fileRepository;

    public ResponseEntity<Projects> addDetails( Projects projectDetails)
    {
        projectRepository.save(projectDetails);
        return ResponseEntity.ok().build();
    }
    public Projects fetchProjectDetails(String domainName)
    {
         return projectRepository.findByDomainName(domainName);

    }
    @Transactional
    public Files saveFile(Files files) {
        return fileRepository.save(files);
    }

    public Optional<Files> getFile(Long fileId) {
       return  fileRepository.findById(fileId);
    }

}
