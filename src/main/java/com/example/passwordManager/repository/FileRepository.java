package com.example.passwordManager.repository;

import com.example.passwordManager.Entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<Files,Long> {
    Optional<Files> findByFileName(String fileName);
}
