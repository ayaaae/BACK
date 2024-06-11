package com.pfe.servicegestionprojets.Repository;

import com.pfe.servicegestionprojets.Entities.Projet;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetRepository extends JpaRepository<Projet,Long> {
    List<Projet> findByStatus(String status);

    @Query("SELECT p FROM Projet p WHERE p.dateDebut >= :startDate AND p.dateFin <= :endDate")
    List<Projet> findByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


}
