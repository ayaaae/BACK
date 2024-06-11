package com.pfe.serviceutilisateur.Web;

import com.pfe.serviceutilisateur.Entities.Admin;
import com.pfe.serviceutilisateur.Entities.Employee;
import com.pfe.serviceutilisateur.Model.AuthenticationRequest;
import com.pfe.serviceutilisateur.Model.AuthenticationResponse;
import com.pfe.serviceutilisateur.Model.Employeetransfer;
import com.pfe.serviceutilisateur.Security.Crypt;
import com.pfe.serviceutilisateur.Service.AdminServiceImplementation;
import com.pfe.serviceutilisateur.Service.AuthenticationService;
import com.pfe.serviceutilisateur.Service.EmployeeServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {
  @Autowired
  private EmployeeServiceImplementation EmployeeCrud;
  @Autowired
  private AdminServiceImplementation AdminCrud;
  private final AuthenticationService service;

  @PostMapping("/authenticate")
  public AuthenticationResponse authenticate(
      @RequestBody AuthenticationRequest request
  ) {
AuthenticationRequest request2 =new AuthenticationRequest();

    request2.setEmail(Crypt.decrypt(request.getEmail(),"bXVzdGJlMTZieXRlc2tleQ=="));
    request2.setPassword(Crypt.decrypt(request.getPassword(),"bXVzdGJlMTZieXRlc2tleQ=="));
    System.out.println("decrpted user for login :"+request2.toString());

    return service.authenticate(request2);
  }


 /* @RequestMapping("/GetEmployeeTocken/{code}")
  public String getEmployeeTocken(@PathVariable long code) {
    return (String) (EmployeeCrud.Get(code)).getToken().getToken();
  }
*/


  @GetMapping("/Employeespeojet/{code}")
  public List<Employeetransfer> LesEmployee(@PathVariable long code) {
List<Employeetransfer> employees = new ArrayList<Employeetransfer>();
    for(Employee e :EmployeeCrud.All()){
      if(e.getProjetids().contains(","+code+",")){
        Employeetransfer et = new Employeetransfer();
et.setId(e.getId());et.setNom(e.getNom());et.setPrenom(e.getPrenom());et.setProjetids(e.getProjetids());et.setRole(e.getRole().toString());
        employees.add(et);
      }
    }
System.out.println("you call the emloyee srvice");
    return (List<Employeetransfer>) employees;

  }


  @RequestMapping(method = RequestMethod.POST, value = "/AddEmployee")
  public Employee AddEmployee(@RequestBody Employee User) {
    System.out.println("Employee added");
    return (Employee) EmployeeCrud.Add(User);


  }


  @RequestMapping(method = RequestMethod.POST, value = "/AddAdmin")
  public Admin Add(@RequestBody Admin User) {
    System.out.println("Admin added");
    return AdminCrud.Add(User);


  }

    @RequestMapping("/GetEmployee/{code}")
    public Employee getEmployee(@PathVariable long code) {
        return (Employee) EmployeeCrud.Get(code);
    }


    @GetMapping("/GetEmployeeByNomPrenom")
    public ResponseEntity<Employee> getEmployeeByPrenomAndNom(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        Employee employee = EmployeeCrud.findEmployeeByPrenomAndNom(firstName, lastName);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}