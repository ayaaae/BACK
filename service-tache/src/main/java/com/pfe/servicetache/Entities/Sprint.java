package com.pfe.servicetache.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pfe.servicetache.Model.Employee;
import com.pfe.servicetache.Model.Projet;
import com.pfe.servicetache.enume.Etat;
import com.pfe.servicetache.enume.Etatsprint;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_sprint;
    private long idProjet;
    @Transient
    private Projet projet;
     
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sprint", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("sprint")
        private List<BackLog> userStories;
    private String nom_sprint;
    private String Description;
    @Enumerated(EnumType.STRING)
    private Etat etatsprint;
    private String Datedebut;
    private String Datefin;






}
