package com.pfe.servicetache.Web;

import com.pfe.servicetache.Entities.BackLog;
import com.pfe.servicetache.Entities.Sprint;
import com.pfe.servicetache.Service.BacklogService;
import com.pfe.servicetache.Service.SprintService;
import com.pfe.servicetache.enume.Etat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sprint")
public class SprintController  implements HealthIndicator {

    private final SprintService sprintService;
    @Autowired
    private BacklogService backlogService;
    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping("/AllSprints")
    public ResponseEntity<List<Sprint>> getAllSprints(){
        List<Sprint> sprints=sprintService.findAllSprint();
        return new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    @GetMapping("/maxids")
    public long maxid(){
        List<Sprint> sprints=sprintService.findAllSprint();
        long max=0;
        for(Sprint s :sprints){
            if(max<=s.getId_sprint()){
                max=s.getId_sprint();
            }
        }
        return max;
    }

    @GetMapping("/sprint/{idSprint}")
    public ResponseEntity<Sprint> getSprint(@PathVariable Long idSprint){
        Sprint sprint=sprintService.findSprintById(idSprint);
        if (sprint != null) {
            return ResponseEntity.ok(sprint);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/findProjectSprints/{idprojet}")
    public ResponseEntity<List<Sprint>> getProjetSprints(@PathVariable Long idprojet){
        List<Sprint> sprints=sprintService.findSprintByIdProjet(idprojet);
        return new ResponseEntity<>(sprints, HttpStatus.OK);

    }
    @PostMapping("/createSprint")
    public ResponseEntity<Sprint> addSprint(@RequestBody Sprint sprint){
        Sprint newSprint=sprintService.addSprint(sprint);
        return new ResponseEntity<>(newSprint,HttpStatus.CREATED);
    }


    //end sprint
    @PostMapping("/endSprint")
    public ResponseEntity<Sprint> endSprint(@RequestBody Sprint sprint){
        Sprint s = sprintService.findSprintById(sprint.getId_sprint());
        Sprint sprinttosave=sprint;
        sprinttosave.setId_sprint(0);
        Sprint newSprint = new Sprint();
        if(sprint.getNom_sprint().isEmpty()){



            for(BackLog b:s.getUserStories()){
                if(b.getEtat().toString()!="FAIT"){
                    BackLog bk =b;
                    bk.setSprint(null);
                    backlogService.updateBacklog(bk);

                }

            }
       }else{

            newSprint=sprintService.addSprint(sprinttosave);
            for(BackLog b:s.getUserStories()){
                if(b.getEtat().toString()!="FAIT"){
                    BackLog bk =b;
                    bk.setSprint(newSprint);
                    backlogService.updateBacklog(bk);

                }

           }

       }
        s.setEtatsprint(Etat.valueOf("FAIT"));
        sprintService.updateSprint(s);


        return new ResponseEntity<>(newSprint,HttpStatus.CREATED);
    }


    @PutMapping("/update/{idSprint}")
    public ResponseEntity<Sprint> updateSprint(@PathVariable Long idSprint, @RequestBody Sprint sprint){
        Sprint existingSprint = sprintService.findSprintById(idSprint);

        if (existingSprint!=null) {
            sprint.setId_sprint(idSprint);
            Sprint updatedSprint = sprintService.updateSprint(sprint);
            return ResponseEntity.ok(updatedSprint);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{idSprint}")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long idSprint){
        sprintService.deleteSprintById(idSprint);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @Override
    public Health health() {
        List<Sprint> sprints=sprintService.findAllSprint();
        if (sprints.isEmpty())
            return Health.down().build();

        return Health.up().build();
    }
}
