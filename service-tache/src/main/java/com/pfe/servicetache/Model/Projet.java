package com.pfe.servicetache.Model;

import com.pfe.servicetache.Entities.Sprint;
import com.pfe.servicetache.Entities.Taches;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Projet {
    private long id_projet;
    private String nom_projet;
    private String Description;
    private Date Date_Debut;
    private Date Date_Fin;
    private List<Taches> taches;
    private List<Sprint> sprints;
}
