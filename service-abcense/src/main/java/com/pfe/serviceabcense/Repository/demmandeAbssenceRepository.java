package com.pfe.serviceabcense.Repository;

import com.pfe.serviceabcense.Entities.demandeAbssence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface demmandeAbssenceRepository extends JpaRepository<demandeAbssence,Long> {
    @Query("SELECT d FROM demandeAbssence d WHERE d.datedebut >= :startDate AND d.datefin <= :endDate")
    List<demandeAbssence> findByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
