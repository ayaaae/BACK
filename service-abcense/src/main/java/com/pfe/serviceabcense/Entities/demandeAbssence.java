package com.pfe.serviceabcense.Entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pfe.serviceabcense.Model.Employee;

import com.pfe.serviceabcense.enume.state;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
public class demandeAbssence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String motif;
    @Enumerated(EnumType.STRING)
    private state etat;
    private Date datedemand;
    private Date datedebut;
    private Date datefin;
    private Long idsource;
    private String nomcompletsource;
    private Long iddestination;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "demande", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("demande")
    private List<Notification> notifications;



}
