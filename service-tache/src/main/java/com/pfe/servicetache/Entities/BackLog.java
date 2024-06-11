package com.pfe.servicetache.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pfe.servicetache.Model.Employee;
import com.pfe.servicetache.enume.Etat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
public class BackLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_backlog;
    private Long idProjet;
    private String nom_backlog;
    private String Description;
    @Enumerated(EnumType.STRING)
        private Etat etat;
    private long id_employee;
    private int prioriter;
    @Transient
    private Employee employe;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "backlog", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("backlog")
    private List<Taches> taches;
   
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "sprint")
    @JsonIgnoreProperties("userStories")
    private Sprint sprint;
   
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "epic")
    @JsonIgnoreProperties("userStories")
    private Epic epic;

    @OneToMany(mappedBy = "backlog", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("backlog")
    private List<Comment> comments;

}
