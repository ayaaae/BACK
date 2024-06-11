package com.pfe.servicegestionprojets.Service;

import com.pfe.servicegestionprojets.Entities.Projet;
import com.pfe.servicegestionprojets.Model.Employee;
import com.pfe.servicegestionprojets.Repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjetServiceImplementation implements IService<Projet>{
    @Autowired
    private ProjetRepository Projet_repo;
    @Autowired
    EmployeeServiceClient employeeServiceClient;

    @Override
    public List<Projet> All() {

        List<Projet> projets =Projet_repo.findAll();
        for(Projet p : projets){

            p.setEmpployees(employeeServiceClient.LesEmployee(p.getId()));
        }


        return projets;
    }

    @Override
    public void Add(Projet projet) {
        Projet_repo.save(projet);
    }

    @Override
    public long Addprojet(Projet x) {
        return Projet_repo.save(x).getId();
    }

    @Override
    public void Update(Projet projet) {
        Projet_repo.save(projet);
    }

    @Override

    public Projet Get(long id) {
        Projet p = ((Projet) Projet_repo.findById(id).get());
        if(p!=null){
            p.setEmpployees(employeeServiceClient.LesEmployee(p.getId()));
        }
        return p;
    }

    @Override
    public void Delete(long id) {
        Projet_repo.deleteById(id);
    }

    public List<Projet> findByStatus(String status) {
        return Projet_repo.findByStatus(status);
    }

    public List<Projet> findByDateRange(Date startDate, Date endDate) {
        return Projet_repo.findByDateRange(startDate, endDate);
    }

    public List<Optional<Projet>> getProjectsByEmployeeNameAndSurname(String firstName, String lastName) {
        ResponseEntity<Employee> response = employeeServiceClient.getEmployeeByPrenomAndNom(firstName, lastName);
        if (response.getStatusCode() == HttpStatus.OK) {
            Employee employee = response.getBody();
            if (employee != null) {
                List<Long> projectIds = extractProjectIds(employee.getProjetids());
                List<Optional<Projet>> projects = new ArrayList<>();
                for (Long projectId : projectIds) {
                    Optional<Projet> project = Projet_repo.findById(projectId);
                    if (project != null) {
                        projects.add(project);
                    }
                }
                return projects;
            }
        }
        return Collections.emptyList();
    }

    private List<Long> extractProjectIds(String projectIdsString) {
        List<Long> projectIds = new ArrayList<>();
        if (projectIdsString != null && !projectIdsString.isEmpty()) {
            String[] ids = projectIdsString.split(",");
            for (String id : ids) {
                if (!id.trim().isEmpty()) {
                    try {
                        projectIds.add(Long.parseLong(id.trim()));
                    } catch (NumberFormatException e) {
                        // Log or handle the exception as needed
                        System.err.println("Invalid project ID: " + id);
                    }
                }
            }
        }
        return projectIds;
    }

}
