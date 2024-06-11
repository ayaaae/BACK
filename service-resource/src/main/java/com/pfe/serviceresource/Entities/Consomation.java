package com.pfe.serviceresource.Entities;

import com.pfe.serviceresource.Model.Sprint;
import com.pfe.serviceresource.enume.TypeResouce;
import com.pfe.serviceresource.enume.Uniter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Consomation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantiter;
    private Uniter uniter;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Resource_id", referencedColumnName = "id")
    private Resource resource;
    private Long id_sprint;
    @Transient
    private Sprint sprint;





}
