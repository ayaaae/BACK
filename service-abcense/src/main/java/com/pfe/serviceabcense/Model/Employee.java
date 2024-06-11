package com.pfe.serviceabcense.Model;

import com.pfe.serviceabcense.enume.Profession;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

    private long id_utilisateur;
    private String nom_utilisateur;
    private String prenom_utilisateur;
    private String email;
    private  String mot_de_passe;
    private Profession profession;
    private Long id_equipe;




}