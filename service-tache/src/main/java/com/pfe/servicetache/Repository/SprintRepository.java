package com.pfe.servicetache.Repository;

import com.pfe.servicetache.Entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint,Long> {
    List<Sprint> findSprintByIdProjet (long idprojet);
}
