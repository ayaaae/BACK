package com.pfe.serviceechancier.Entities;

import com.pfe.serviceechancier.Model.Sprint;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.util.List;

public class AchievementsDesSprints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_archive;
    private long id_sprint;
    @Transient
    private Sprint sprint;

}
