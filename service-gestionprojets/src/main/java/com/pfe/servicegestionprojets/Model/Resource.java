package com.pfe.servicegestionprojets.Model;


import com.pfe.servicegestionprojets.enumirator.TypeResouce;
import com.pfe.servicegestionprojets.enumirator.Uniter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class Resource {
    private long id;
    private String nom;
    private String Description;
    private TypeResouce type;
    private int quantiter;
    private Uniter unite;
    private Float cout;





}
