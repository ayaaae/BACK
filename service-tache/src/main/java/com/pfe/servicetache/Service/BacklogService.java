package com.pfe.servicetache.Service;

import com.pfe.servicetache.Entities.BackLog;
import com.pfe.servicetache.Repository.BacklogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BacklogService {

    private final BacklogRepository backlogRepository;

    public BacklogService(BacklogRepository backlogRepository) {
        this.backlogRepository = backlogRepository;
    }

    public BackLog addBacklog(BackLog backLog){
        return backlogRepository.save(backLog);
    }
    public BackLog updateBacklog(BackLog backLog){
        return backlogRepository.save(backLog);
    }
    public List<BackLog> findAllBacklogs(){
        return backlogRepository.findAll();
    }
    public BackLog findBacklogById(Long id){
        return backlogRepository.findById(id).get();
    }
    public void deleteBacklogById(Long id){
        backlogRepository.deleteById(id);
    }

    public List<BackLog> findBackLogByIdProjet(Long idprojet){return backlogRepository.findBackLogByIdProjet(idprojet);}

}
