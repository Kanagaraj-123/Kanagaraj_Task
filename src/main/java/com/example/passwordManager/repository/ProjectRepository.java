package com.example.passwordManager.repository;

import com.example.passwordManager.Entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository   extends JpaRepository<Projects,Integer> {

    Projects findByDomainName(String domainName);

}
