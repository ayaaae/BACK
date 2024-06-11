package com.pfe.servicetache.Web;

import com.pfe.servicetache.Entities.BackLog;
import com.pfe.servicetache.Service.BacklogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/backlog")
public class BacklogController {

    private final BacklogService backlogService;

    public BacklogController(BacklogService backlogService) {
        this.backlogService = backlogService;
    }

    @GetMapping("/AllBacklogs")
    public ResponseEntity<List<BackLog>> getBacklogs(){
        List<BackLog> backLogs=backlogService.findAllBacklogs();
        return new ResponseEntity<>(backLogs, HttpStatus.OK);
    }
    @GetMapping("/findBacklog/{idBacklog}")
    public ResponseEntity<BackLog> getBackLog(@PathVariable Long idBacklog){
        BackLog backLog=backlogService.findBacklogById(idBacklog);
        if (backLog != null) {
            return ResponseEntity.ok(backLog);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/findProjectBacklogs/{idprojet}")
    public ResponseEntity<List<BackLog>> getProjetBackLogs(@PathVariable Long idprojet){
        List<BackLog> backLogs=backlogService.findBackLogByIdProjet(idprojet);
        return new ResponseEntity<>(backLogs, HttpStatus.OK);

    }




    @GetMapping("/findProjectBacklogsSorted/{idprojet}")
    public ResponseEntity<List<BackLog>> getProjetBackLogsSorted(@PathVariable Long idprojet){

        List<BackLog> backLogs=backlogService.findBackLogByIdProjet(idprojet);
        Collections.sort(backLogs, (o1, o2) -> Integer.compare(o2.getPrioriter(), o1.getPrioriter()));
        return new ResponseEntity<>(backLogs, HttpStatus.OK);

    }





    @PostMapping("/createBacklog")
    public ResponseEntity<BackLog> addBackLog(@RequestBody BackLog backLog){
        BackLog newBackLog=backlogService.addBacklog(backLog);
        return new ResponseEntity<>(newBackLog,HttpStatus.CREATED);
    }
    @PutMapping("/update/{idBacklog}")
    public ResponseEntity<BackLog> updateBackLog(@PathVariable Long idBacklog, @RequestBody BackLog backLog){
        BackLog existingBackLog = backlogService.findBacklogById(idBacklog);
        System.out.println("update backlog");
        if (existingBackLog!=null) {
            backLog.setId_backlog(idBacklog);
            BackLog updatedBackLog = backlogService.updateBacklog(backLog);
            return ResponseEntity.ok(updatedBackLog);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/delete/{idBacklog}")
    public ResponseEntity<Void> deleteBackLog(@PathVariable Long idBacklog){
        backlogService.deleteBacklogById(idBacklog);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
