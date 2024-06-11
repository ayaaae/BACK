package com.pfe.servicetache.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pfe.servicetache.Model.Employee;
import com.pfe.servicetache.enume.Etat;
import com.pfe.servicetache.enume.Profession;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
public class Taches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_tache;
    private String nom_tache;
    private String description_tache;
    @Enumerated(EnumType.STRING)
    private Etat etat_tache;
    private long idemployee;
    private float duree;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "backlog")
    private BackLog backlog;






}
