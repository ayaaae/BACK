package com.pfe.serviceabcense.Web;


import com.pfe.serviceabcense.Entities.demandeAbssence;
import com.pfe.serviceabcense.Service.abcenseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.pfe.serviceabcense.enume.state.CANCELED;

@RestController
@CrossOrigin
@RequestMapping("/demandeAbssence")
public class DemandeController  {


    private final abcenseService abssenceService;

    public DemandeController(abcenseService abssenceService){
        this.abssenceService=abssenceService;
    }

    @GetMapping("/Alldemande")
    public ResponseEntity<List<demandeAbssence>> getAlldemandeAbssence(){
        List<demandeAbssence> demmandes=abssenceService.findAllAbssence();
        return new ResponseEntity<>(demmandes, HttpStatus.OK);
    }

    @GetMapping("/demande/{idAbssence}")
    public ResponseEntity<demandeAbssence> getAbssence(@PathVariable Long idAbssence){
        demandeAbssence demmandes=abssenceService.findAbssenceById(idAbssence);
        if (demmandes != null) {
            return ResponseEntity.ok(demmandes);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/createdemmande")
    public ResponseEntity<demandeAbssence> addAbssence(@RequestBody demandeAbssence demmandes){
        demandeAbssence newdemande=abssenceService.addAbssence(demmandes);
        return new ResponseEntity<>(newdemande,HttpStatus.CREATED);
    }




    @PutMapping("/update/{idAbssence}")
    public ResponseEntity<demandeAbssence> updateAbssence(@PathVariable Long idAbssence, @RequestBody demandeAbssence demmandes){
        demandeAbssence existingAbssence = abssenceService.findAbssenceById(idAbssence);

        if (existingAbssence!=null) {
            demmandes.setId(idAbssence);
            demandeAbssence updatedAbssence = abssenceService.updateAbssence(demmandes);
            return ResponseEntity.ok(updatedAbssence);
        } else {
            return ResponseEntity.notFound().build();
        }

    }




    @PutMapping("/annuler/{idAbssence}")
    public ResponseEntity<demandeAbssence> updateAbssence(@PathVariable Long idAbssence){
        demandeAbssence existingAbssence = abssenceService.findAbssenceById(idAbssence);

        if (existingAbssence!=null) {
            existingAbssence.setEtat(CANCELED);

            demandeAbssence updatedAbssence = abssenceService.updateAbssence(existingAbssence);
            return ResponseEntity.ok(updatedAbssence);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/delete/{idAbssence}")
    public ResponseEntity<Void> deleteAbssence(@PathVariable Long idAbssence){
        abssenceService.deleteAbssenceById(idAbssence);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<demandeAbssence>> getRequestsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<demandeAbssence> demandeAbssences = abssenceService.findByDateRange(startDate, endDate);
        if (demandeAbssences.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(demandeAbssences);
        }
    }
  
   
}
