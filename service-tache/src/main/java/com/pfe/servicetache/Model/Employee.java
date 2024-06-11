package com.pfe.servicetache.Model;

import com.pfe.servicetache.Entities.Comment;
import com.pfe.servicetache.enume.role_utilisateur;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Employee {
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private  String mot_de_passe;
    private role_utilisateur role;
    private Tokens token;
    private List<Comment> comments;



}