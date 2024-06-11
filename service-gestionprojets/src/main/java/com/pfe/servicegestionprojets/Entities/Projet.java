package com.pfe.servicegestionprojets.Entities;

import com.pfe.servicegestionprojets.Model.Employee;

import com.pfe.servicegestionprojets.Model.Resource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Entity @Getter @Setter
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String description;
    private String status;
    private Date dateDebut;
    private Date dateFin;

    @Transient
    private List<Employee> empployees;
    @Transient
    private List<Resource> resourcess;


}
