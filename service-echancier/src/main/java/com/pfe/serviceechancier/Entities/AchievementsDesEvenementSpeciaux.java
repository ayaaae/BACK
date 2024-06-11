package com.pfe.serviceechancier.Entities;

import com.pfe.serviceechancier.Model.Sprint;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.util.Date;

public class AchievementsDesEvenementSpeciaux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_archive;
    private String nom;
    private String Description;
    private Date Date_Debut;
    private Date Date_Fin;


}
