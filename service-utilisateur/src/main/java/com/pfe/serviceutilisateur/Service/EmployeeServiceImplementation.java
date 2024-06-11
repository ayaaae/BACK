package com.pfe.serviceutilisateur.Service;

import com.pfe.serviceutilisateur.Entities.Employee;
import com.pfe.serviceutilisateur.Repository.AdminRepository;
import com.pfe.serviceutilisateur.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements IService<Employee>{

 @Autowired
   private EmployeeRepository User_repo;
    @Override
    public List<Employee> All() {
        return User_repo.findAll();
    }

    @Override
    public Employee Add(Employee user) {
user.setProjetids(",0,");
        return(User_repo.save(user));
    }

    @Override
    public Employee Update(Employee user) {
return(User_repo.save(user));
    }

    @Override

    public Employee Get(long id) {
        return (User_repo.findById(id)).get();
    }

    @Override
    public void Delete(long id) {
User_repo.deleteById(id);
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        Employee employee = new Employee();
        for (Employee e:User_repo.findAll()){
            if(e.getEmail().equals(email)){
        employee.setEmail(e.getEmail());employee.setId(e.getId());employee.setRole(e.getRole());employee.setNom(e.getNom());employee.setPrenom(e.getPrenom());employee.setMot_de_passe(e.getMot_de_passe());employee.setProjetids(e.getProjetids());
            }
        }


        return Optional.of(employee);
    }
    public Employee findEmployeeByPrenomAndNom(String firstName,String lastName){
      return  User_repo.findEmployeeByPrenomAndNom(firstName,lastName);
    }

}
