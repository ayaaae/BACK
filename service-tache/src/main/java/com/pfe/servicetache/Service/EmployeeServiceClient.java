package com.pfe.servicetache.Service;

import com.pfe.servicetache.Model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Service
@FeignClient(name = "service-utilisateur", url = "http://localhost:8083/SERVICE-UTILISATEUR")//add your appropriate port number

public interface EmployeeServiceClient {
    @GetMapping("/public/GetEmployeeTocken/{code}")
    String getEmployeeBTockenyId(@PathVariable("code") Long code);
}
