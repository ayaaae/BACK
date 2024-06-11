package com.pfe.servicefinanciere.Entities;

import com.pfe.servicefinanciere.Model.Projet;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Entity
public class Payement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String benificiere;
    private Date DatePaiement;
    private Double monstant;
    private String motif;
    private Long id_projet;
    @Transient
    private Projet projet;


}
