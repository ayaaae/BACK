package com.pfe.servicegestionprojets.Service;

import com.pfe.servicegestionprojets.Model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Service
@FeignClient(name = "service-utilisateur", url = "http://localhost:8083/SERVICE-UTILISATEUR")

public interface EmployeeServiceClient {
    @GetMapping("/public/Employeespeojet/{code}")
     List<Employee> LesEmployee(@PathVariable long code);

    @GetMapping("/public/GetEmployeeByNomPrenom")
     ResponseEntity<Employee> getEmployeeByPrenomAndNom( @RequestParam("firstName") String firstName,
                                                         @RequestParam("lastName") String lastName);
}
