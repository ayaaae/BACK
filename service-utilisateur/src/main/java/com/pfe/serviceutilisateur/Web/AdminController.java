package com.pfe.serviceutilisateur.Web;
import com.pfe.serviceutilisateur.Entities.Admin;
import com.pfe.serviceutilisateur.Service.AdminServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AdminController {


    @Autowired
    private AdminServiceImplementation AdminCrud;



    //admin crud----------------------------------------------------------------------------
    @GetMapping("/ListAdmin")
    public List<Admin> LesUtilisateur() {
        System.out.println("list called");
        return (List<Admin>) AdminCrud.All();

    }



    @RequestMapping("/GetAdmin/{code}")
    public Admin getUser(@PathVariable long code) {
       System.out.println ("Admin found");
        return (Admin) AdminCrud.Get(code);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/UpdateAdmin/{code}")
    public Admin UpdateUser(@PathVariable long code, @RequestBody Admin User) {
        User.setId(code);
        System.out.println ("Admin Updated");
       return(AdminCrud.Update(User)) ;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/DeleteAdmin/{code}")
    public String DeleteUser(@PathVariable long code) {
        if(AdminCrud.Get(code)!=null){
            AdminCrud.Delete(code);
            return ("Admin deleted successfuly");

        }
        return ("Admin does not exist");
    }





}
