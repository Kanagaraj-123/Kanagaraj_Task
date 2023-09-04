package com.example.passwordManager.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String domainName;
    private String userName;
    private String password;
    private int portNumber;



}
