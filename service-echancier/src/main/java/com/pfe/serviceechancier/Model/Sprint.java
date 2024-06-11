package com.pfe.serviceechancier.Model;


import com.pfe.serviceechancier.Enume.Etat;
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
