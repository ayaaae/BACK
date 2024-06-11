package com.pfe.serviceutilisateur.Service;

import com.pfe.serviceutilisateur.Entities.Admin;
import com.pfe.serviceutilisateur.Entities.Employee;
import com.pfe.serviceutilisateur.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplementation implements IService<Admin>{

    @Qualifier("adminRepository")
    @Autowired
   private AdminRepository User_repo;
    @Override
    public List<Admin> All() {
        return User_repo.findAll();
    }

    @Override
    public Admin Add(Admin user) {
return(User_repo.save(user));
    }

    @Override
    public Admin Update(Admin user) {
return (User_repo.save(user));
    }

    @Override

    public Admin Get(long id) {
        return (User_repo.findById(id)).get();
    }

    @Override
    public void Delete(long id) {
User_repo.deleteById(id);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        Admin employee = new Admin();
        for (Admin e:User_repo.findAll()){
            if(e.getEmail().equals(email)){
                employee.setEmail(e.getEmail());employee.setId(e.getId());employee.setNom(e.getNom());employee.setPrenom(e.getPrenom());employee.setMot_de_passe(e.getMot_de_passe());
            }
        }


        return Optional.of(employee);
    }


}
