package com.pfe.servicegestionprojets.Model;

import com.pfe.servicegestionprojets.enumirator.Profession;
import com.pfe.servicegestionprojets.enumirator.role_utilisateur;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Employee {
    private Long id;
    private String nom;
    private String prenom;
    private String Projetids;
    private String role;



}
