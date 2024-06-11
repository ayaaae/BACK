package com.pfe.servicetache.Service;

import com.pfe.servicetache.Entities.BackLog;
import com.pfe.servicetache.Entities.Sprint;
import com.pfe.servicetache.Repository.SprintRepository;
import com.pfe.servicetache.enume.Etat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintService {
    private final SprintRepository sprintRepository;

    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public Sprint addSprint(Sprint sprint){
        return sprintRepository.save(sprint);
    }
    public Sprint updateSprint(Sprint sprint){
        return sprintRepository.save(sprint);
    }
    public List<Sprint> findAllSprint(){
        return sprintRepository.findAll();
    }
    public Sprint findSprintById(Long id){
        return sprintRepository.findById(id).get();
    }
    public void deleteSprintById(Long id){
        for(BackLog b :sprintRepository.findById(id).get().getUserStories()){

            b.setSprint(null);
        }
        sprintRepository.deleteById(id);
    }




    public List<Sprint> findSprintByIdProjet(Long idprojet){return sprintRepository.findSprintByIdProjet(idprojet);}


}
