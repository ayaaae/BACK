package com.pfe.serviceabcense.Service;



import com.pfe.serviceabcense.Entities.demandeAbssence;
import com.pfe.serviceabcense.Repository.demmandeAbssenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class abcenseService {
    private final demmandeAbssenceRepository abssenceRepository;

    @Autowired
    public abcenseService( demmandeAbssenceRepository abssenceRepository){
        this.abssenceRepository = abssenceRepository;
        
    }

    public demandeAbssence addAbssence(demandeAbssence abssence){
        return abssenceRepository.save(abssence);
    }
    public demandeAbssence updateAbssence(demandeAbssence abssence){
        return abssenceRepository.save(abssence);
    }
    public List<demandeAbssence> findAllAbssence(){
        return abssenceRepository.findAll();
    }
    public demandeAbssence findAbssenceById(Long id){
        return abssenceRepository.findById(id).get();
    }
    public void deleteAbssenceById(Long id){
        abssenceRepository.deleteById(id);
    }

    public List<demandeAbssence> findByDateRange(Date startDate, Date endDate) {
        return abssenceRepository.findByDateRange(startDate, endDate);
    }



}
