package com.pfe.servicetache.Service;

import com.pfe.servicetache.Entities.Taches;
import com.pfe.servicetache.Repository.TacheRepository;
import com.pfe.servicetache.enume.Etat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacheService {
    private final TacheRepository tacheRepository;

    @Autowired
    public TacheService(TacheRepository tacheRepository){
        this.tacheRepository=tacheRepository;
    }

    public Taches addTache(Taches tache){
        return tacheRepository.save(tache);
    }
    public Taches updateTache(Taches tache){
        return tacheRepository.save(tache);
    }
    public List<Taches> findAllTache(){
        return tacheRepository.findAll();
    }
    public Taches findTacheById(Long id){
        return tacheRepository.findById(id).get();
    }
    public void deleteTacheById(Long id){
        tacheRepository.deleteById(id);
    }

    public Taches markTacheEnCours(Taches tache){
        if(!"Encours".equalsIgnoreCase(tache.getEtat_tache().name())){
            tache.setEtat_tache(Etat.ENCOURS);
            return tacheRepository.save(tache);
        }else return tache;
    }
    public Taches markTacheFait(Taches tache){
        if(!"Fait".equalsIgnoreCase(tache.getEtat_tache().name())){
            tache.setEtat_tache(Etat.FAIT);
            return tacheRepository.save(tache);
        }else return tache;
    }
    public Taches markTacheAFaire(Taches tache){
        if(!"AFaire".equalsIgnoreCase(tache.getEtat_tache().name())){
            tache.setEtat_tache(Etat.AFAIRE);
            return tacheRepository.save(tache);
        }else return tache;
    }

}
