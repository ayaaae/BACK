package com.pfe.servicetache.Repository;

import com.pfe.servicetache.Entities.BackLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BacklogRepository  extends JpaRepository<BackLog,Long> {
    List<BackLog> findBackLogByIdProjet (long idprojet);
}
