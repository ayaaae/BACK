package com.pfe.serviceresource.Entities;

import com.pfe.serviceresource.Model.Projet;
import com.pfe.serviceresource.enume.TypeResouce;
import com.pfe.serviceresource.enume.Uniter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long id_projet;
    @Transient
    private Projet projet;
    private String nom;
    private String Description;
    private TypeResouce type;
    private int quantiter;
    private Uniter unite;
    private Float cout;
    @OneToOne(mappedBy = "consomation")
    private Consomation consomation;





}
