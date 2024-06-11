package com.pfe.servicetache.Web;

import com.pfe.servicetache.Entities.Epic;
import com.pfe.servicetache.Model.Employee;
import com.pfe.servicetache.Security.Security;
import com.pfe.servicetache.Service.EmployeeServiceClient;
import com.pfe.servicetache.Service.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/epic")
public class EpicController {
    private final EpicService epicService;

    //EmployeeServiceClient employeeServiceClient;

    public EpicController(EpicService epicService) {
        this.epicService = epicService;
    }

    @GetMapping("/AllEpics")
    public ResponseEntity<List<Epic>> getAllEpics(/*@RequestHeader (name="Authorization") String token*/){

/*
      //get token,id
        String[] parts = token.toString().split(" ");


        System.out.println("id :"+parts[parts.length-1]);
        System.out.println("tocken  :"+parts[parts.length-2]);

//test open fieing

       String tk= employeeServiceClient.getEmployeeBTockenyId(7L);
        System.out.println("employee tocken :"+tk);
        System.out.println("big test :"+Security.check(Long.valueOf(parts[parts.length-1]),parts[parts.length-2],tk));

*/

        List<Epic> epics=epicService.findAllEpics();
        return new ResponseEntity<>(epics, HttpStatus.OK);
    }
    @GetMapping("/epic/{idEpic}")
    public ResponseEntity<Epic> getEpic(@PathVariable Long idEpic){
        Epic epic=epicService.findEpicById(idEpic);
        if (epic != null) {
            return ResponseEntity.ok(epic);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/findProjectEpics/{idprojet}")
    public ResponseEntity<List<Epic>> getProjetEpics(@PathVariable Long idprojet){
        List<Epic> epics=epicService.findEpicByIdProjet(idprojet);
        return new ResponseEntity<>(epics, HttpStatus.OK);

    }

        @PostMapping("/createEpic")
    public ResponseEntity<Epic> addEpic(@RequestBody Epic epic){
        Epic newEpic=epicService.addEpic(epic);
        return new ResponseEntity<>(newEpic,HttpStatus.CREATED);
    }
    @PutMapping("/update/{idEpic}")
    public ResponseEntity<Epic> updateEpic(@PathVariable Long idEpic, @RequestBody Epic epic){
        Epic existingEpic = epicService.findEpicById(idEpic);

        if (existingEpic!=null) {
            epic.setId_epic(idEpic);
            Epic updatedEpic = epicService.updateEpic(epic);
            return ResponseEntity.ok(updatedEpic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{idEpic}")
    public ResponseEntity<Void> deleteEpic(@PathVariable Long idEpic){
        epicService.deleteEpicById(idEpic);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
