package com.pfe.servicefinanciere.Entities;

import com.pfe.servicefinanciere.Model.Projet;
import com.pfe.servicefinanciere.enume.TypeResouce;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Entity
public class Budjets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private TypeResouce typeResouce;
    private Double seuilAlert;
    private Double montant;
    private Long id_projet;
    @Transient
    private Projet projet;


}
