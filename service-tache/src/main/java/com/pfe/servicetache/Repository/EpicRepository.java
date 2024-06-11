package com.pfe.servicetache.Repository;

import com.pfe.servicetache.Entities.Epic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpicRepository  extends JpaRepository<Epic,Long> {
    List<Epic> findEpicByIdProjet (long idprojet);
}
