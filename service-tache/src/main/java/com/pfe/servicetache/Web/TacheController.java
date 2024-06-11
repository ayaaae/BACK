package com.pfe.servicetache.Web;

import com.pfe.servicetache.Entities.Taches;
import com.pfe.servicetache.Service.TacheService;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/Taches")
public class TacheController  implements HealthIndicator {

    private final TacheService tacheService;

    public TacheController(TacheService tacheService){
        this.tacheService=tacheService;
    }

    @GetMapping("/AllTaches")
    public ResponseEntity<List<Taches>> getAllTaches(){
        List<Taches> taches=tacheService.findAllTache();
        return new ResponseEntity<>(taches, HttpStatus.OK);
    }

    @GetMapping("/tache/{idTache}")
    public ResponseEntity<Taches> getTache(@PathVariable Long idTache){
        Taches taches=tacheService.findTacheById(idTache);
        if (taches != null) {
            return ResponseEntity.ok(taches);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/createTache")
    public ResponseEntity<Taches> addTache(@RequestBody Taches taches){
        Taches newtache=tacheService.addTache(taches);
        return new ResponseEntity<>(newtache,HttpStatus.CREATED);
    }

    @PutMapping("/update/{idTache}")
    public ResponseEntity<Taches> updateTache(@PathVariable Long idTache, @RequestBody Taches taches){
        Taches existingTache = tacheService.findTacheById(idTache);

        if (existingTache!=null) {
            taches.setId_tache(idTache);
            Taches updatedTache = tacheService.updateTache(taches);
            return ResponseEntity.ok(updatedTache);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/delete/{idTache}")
    public ResponseEntity<Void> deleteTache(@PathVariable Long idTache){
        tacheService.deleteTacheById(idTache);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/InProgress/{idTache}")
    public ResponseEntity<Taches> markTacheEnCours(@PathVariable Long idTache, @RequestBody Taches taches) {
        Taches existingTache = tacheService.findTacheById(idTache);
        if (existingTache != null) {
            taches.setId_tache(idTache);
            Taches tacheEncours = tacheService.markTacheEnCours(taches);
            return ResponseEntity.ok(tacheEncours);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/done/{idTache}")
    public ResponseEntity<Taches> markTacheFait(@PathVariable Long idTache, @RequestBody Taches taches) {
        Taches existingTache = tacheService.findTacheById(idTache);
        if (existingTache != null) {
            taches.setId_tache(idTache);
            Taches tacheFait = tacheService.markTacheFait(taches);
            return ResponseEntity.ok(tacheFait);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/todo/{idTache}")
    public ResponseEntity<Taches> markTacheAFaire(@PathVariable Long idTache, @RequestBody Taches taches) {
        Taches existingTache = tacheService.findTacheById(idTache);
        if (existingTache != null) {
            taches.setId_tache(idTache);
            Taches tacheAFaire = tacheService.markTacheAFaire(taches);
            return ResponseEntity.ok(tacheAFaire);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Override
    public Health health() {
        List<Taches> taches=tacheService.findAllTache();
        if (taches.isEmpty())
            return Health.down().build();

        return Health.up().build();
    }
}
