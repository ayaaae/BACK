package com.pfe.serviceresource.Model;

import com.pfe.serviceresource.enume.Etat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter

public class Sprint {

    private long id;
    private String nom;
    private String Description;
    private Etat etat;
    private Date Marge_Retard;
    private Date Date_Debut;
    private Date Date_Fin;






}
