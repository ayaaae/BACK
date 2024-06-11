package com.pfe.servicegestionprojets.Web;


import com.pfe.servicegestionprojets.Entities.Projet;
import com.pfe.servicegestionprojets.Model.Employee;
import com.pfe.servicegestionprojets.Service.IService;
import com.pfe.servicegestionprojets.Service.ProjetServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projet")
public class ProjetController {


    @Qualifier("projetServiceImplementation")
    @Autowired
    private IService ProjetCrud;

    @Autowired
    private ProjetServiceImplementation projetServiceImplementation;

    @Autowired
    public void setProjetServiceImplementation(ProjetServiceImplementation projetServiceImplementation) {
        this.projetServiceImplementation = projetServiceImplementation;
    }


    @GetMapping("/list")
    public List<Projet> LesProjet(){

        return (List<Projet>)ProjetCrud.All();


    }
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void Add(@RequestBody Projet  projet)
    {
        ProjetCrud.Add((Projet)projet);
        System.out.println(projet.getNom());
        System.out.println("Projet added");

    }


    //add project and update employee


    @RequestMapping(method = RequestMethod.POST, value = "/addprojet")
    public long  Addprojet(@RequestBody Projet  projet)
    {
       long id = ProjetCrud.Addprojet(projet);
        System.out.println("Projet added" +id);
return (id);
    }














    @RequestMapping("/get/{code}")
    public  Projet getProjet(@PathVariable long code){
        System.out.println("Projet found");

        return ( Projet) ProjetCrud.Get(code);

    }


    @GetMapping("/Getbyemployee/{code}")
    public List<Projet> Projetsbyemployee(@PathVariable long code){
        Projet p2 = new Projet();
        List<Employee> emplyoees;
        ArrayList<Projet> projets = new ArrayList<Projet>();

        for (Projet p :(List<Projet>)ProjetCrud.All()){
            for(Employee e :p.getEmpployees()){
                if(e.getId()==code){projets.add(p);}}}

        for (Projet p : projets ){
            List<Employee> employees = p.getEmpployees(); // Corrected variable name
            employees.removeIf(e -> ((Employee)e).getId() == code); // Corrected lambda expression and method name
            p.setEmpployees(employees);
        }

        return projets;


    }




    @RequestMapping(method = RequestMethod.PUT,value = "/update/{code}")
    public void UpdateProjet(@PathVariable long code , @RequestBody  Projet  projet){
projet.setId(code);
        ProjetCrud.Update(projet);
        System.out.println("Projet updated");

    }
    @RequestMapping(method = RequestMethod.DELETE,value = "delete/{code}")
    public void DeleteProjet(@PathVariable long code ){

        ProjetCrud.Delete(code);
        System.out.println("Projet deletd");

    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Projet>> getProjectsByStatus(@PathVariable String status) {
        List<Projet> projects = projetServiceImplementation.findByStatus(status);
        if (projects.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(projects);
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Projet>> getProjectsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<Projet> projects = projetServiceImplementation.findByDateRange(startDate, endDate);
        if (projects.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(projects);
        }
    }

    @GetMapping("/projects-by-employee")
    public ResponseEntity<List<Optional<Projet>>> getProjectsByEmployeeNameAndSurname(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        List<Optional<Projet>> projects = projetServiceImplementation.getProjectsByEmployeeNameAndSurname(firstName, lastName);
        if (!projects.isEmpty()) {
            return ResponseEntity.ok(projects);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
