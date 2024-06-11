package com.pfe.servicetache.Repository;

import com.pfe.servicetache.Entities.Taches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Taches,Long> {
}
