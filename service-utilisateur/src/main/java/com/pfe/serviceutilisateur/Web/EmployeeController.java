package com.pfe.serviceutilisateur.Web;



import com.pfe.serviceutilisateur.Entities.Employee;
import com.pfe.serviceutilisateur.Repository.EmployeeRepository;
import com.pfe.serviceutilisateur.Service.EmployeeServiceImplementation;
import com.pfe.serviceutilisateur.Service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class EmployeeController {







    @Autowired
    private EmployeeServiceImplementation EmployeeCrud;

    //Employee crud---------------------------------------------------------------------------------------------------
    @GetMapping("/ListEmployee")
    public List<Employee> LesEmployee() {
        System.out.println ("les employees :"+EmployeeCrud.All().size());
        return (List<Employee>) EmployeeCrud.All();

    }



    @RequestMapping("/GetEmployee/{code}")
    public Employee getEmployee(@PathVariable long code) {
        return (Employee) EmployeeCrud.Get(code);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/UpdateEmployee/{code}")
    public Employee UpdateEmployee(@PathVariable long code, @RequestBody Employee User) {
        User.setId(code);
        System.out.println ("Employee Updated");
        return (Employee) EmployeeCrud.Update(User);
    }



    @RequestMapping(method = RequestMethod.PUT, value = "/UpdateEmployeeprojetcs/{code}")
    public Employee UpdateEmployeeProjects(@PathVariable long code, @RequestBody Employee User) {
        User.setProjetids(User.getProjetids()+","+code+",");
        System.out.println ("Employee Updated");
        return (Employee) EmployeeCrud.Update(User);
    }






    @RequestMapping(method = RequestMethod.DELETE, value = "/DeleteEmployee/{code}")
    public String DeleteEmployee(@PathVariable long code) {
        if(EmployeeCrud.Get(code)!=null){
            EmployeeCrud.Delete(code);
            return ("employee deleted successfully");
        }
        return ("employee does not exist !!");
    }




}
