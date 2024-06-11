package com.pfe.servicetache.Service;

import com.pfe.servicetache.Entities.BackLog;
import com.pfe.servicetache.Entities.Epic;
import com.pfe.servicetache.Repository.EpicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpicService {
    private final EpicRepository epicRepository;


    public EpicService( EpicRepository epicRepository) {
        this.epicRepository = epicRepository;
    }
    public Epic addEpic(Epic epic){
        return epicRepository.save(epic);
    }
    public Epic updateEpic(Epic epic){
        return epicRepository.save(epic);
    }
    public List<Epic> findAllEpics(){
        return epicRepository.findAll();
    }
    public Epic findEpicById(Long id){
        return epicRepository.findById(id).get();
    }
    public void deleteEpicById(Long id){
       for(BackLog b :epicRepository.findById(id).get().getUserStories()) {
           b.setEpic(null);
       }



        epicRepository.deleteById(id);
    }

    public List<Epic> findEpicByIdProjet(Long idprojet){return epicRepository.findEpicByIdProjet(idprojet);}

}
