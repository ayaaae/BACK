package com.pfe.serviceresource.Model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Projet {
    private long id;
    private String nom;
    private String Description;
    private Date Date_Debut;
    private Date Date_Fin;
    }
