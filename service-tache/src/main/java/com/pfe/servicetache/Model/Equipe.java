package com.pfe.servicetache.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.List;

@Data
public class Equipe {
    private long id_equipe;
    private String description;
    private String nom_equipe;
    private Projet projet;
    private List<Employee> employees;
}
