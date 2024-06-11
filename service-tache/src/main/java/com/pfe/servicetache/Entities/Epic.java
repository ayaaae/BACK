package com.pfe.servicetache.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Epic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_epic;
    private long idProjet;
    private String nom_epic;
    private String Description;
    private String couleur;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "epic", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("epic")
    private List<BackLog> userStories ;







}
