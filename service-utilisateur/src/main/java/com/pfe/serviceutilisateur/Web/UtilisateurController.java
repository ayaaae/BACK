package com.pfe.serviceutilisateur.Web;


import com.pfe.serviceutilisateur.Entities.Admin;
import com.pfe.serviceutilisateur.Entities.Employee;
import com.pfe.serviceutilisateur.Model.Tocken;
import com.pfe.serviceutilisateur.Security.Crypt;
import com.pfe.serviceutilisateur.Security.Format;
import com.pfe.serviceutilisateur.Service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UtilisateurController {


    @Qualifier("adminServiceImplementation")
    @Autowired
    private IService AdminCrud;




    @Qualifier("employeeServiceImplementation")
    @Autowired
    private IService EmployeeCrud;

    //login key
    @PostMapping("/Utilisateur-service/login")
    public String login(@RequestBody Tocken tocken) throws Exception {


        String UserInfo="";
        System.out.println(tocken.getEmail());

        for (Admin admin : (List<Admin>) AdminCrud.All()) {

            if (admin.getEmail().equals(Crypt.decrypt(tocken.getEmail(),"bXVzdGJlMTZieXRlc2tleQ==")) && admin.getMot_de_passe().equals(Crypt.decrypt(tocken.getPassword(), "bXVzdGJlMTZieXRlc2tleQ=="))) {
                System.out.println(Format.ToJson(admin));
                UserInfo = Format.ToJson(admin);

                return  Crypt.encrypt(UserInfo,"bXVzdGJlMTZieXRlc2tleQ==");

            }
        }


        for (Employee employee : (List<Employee>) EmployeeCrud.All()) {
            System.out.println("why");
            if (employee.getEmail().equals(Crypt.decrypt(tocken.getEmail(),"bXVzdGJlMTZieXRlc2tleQ==")) && employee.getMot_de_passe().equals(Crypt.decrypt(tocken.getPassword(), "bXVzdGJlMTZieXRlc2tleQ=="))) {
                System.out.println(Format.ToJson(employee));
                UserInfo = Format.ToJson(employee);


                return  Crypt.encrypt(UserInfo,"bXVzdGJlMTZieXRlc2tleQ==");
            }
        }




        return  Crypt.encrypt(UserInfo,"bXVzdGJlMTZieXRlc2tleQ==");
    }




}
