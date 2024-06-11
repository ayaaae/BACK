package com.pfe.serviceutilisateur.Web;

import com.pfe.serviceutilisateur.Entities.Admin;
import com.pfe.serviceutilisateur.Entities.Employee;
import com.pfe.serviceutilisateur.Service.AdminServiceImplementation;
import com.pfe.serviceutilisateur.Service.EmployeeServiceImplementation;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {


    @Autowired
    private MockMvc moc;

    @MockBean
    private EmployeeServiceImplementation employeeCrudService;

    @InjectMocks
    private EmployeeController employeeController;


    private ObjectMapper OM = new ObjectMapper();
    ObjectWriter OW = OM.writer();
    Employee employee1 = new Employee();
    Employee employee2 = new Employee();
    @Test

    void lesEmployee() throws Exception {

        employee1.setId(1);
        employee1.setEmail("employee1@gmail.com");

        employee2.setId(2);
        employee2.setEmail("employee2@gmail.com");

        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee1);
        employees.add(employee2);
        when(employeeCrudService.All()).thenReturn(employees);

        moc.perform(get("/ListEmployee")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].email", is("employee2@gmail.com")));
    }
    @Test
    void addEmployee()  throws Exception {


        employee1.setEmail("test@gmail.com");
        employee1.setMot_de_passe("test");
        employee1.setNom("test");
        employee1.setPrenom("test");
        when(employeeCrudService.Add(employee1)).thenReturn(employee1);
        String employee_as_string = OW.writeValueAsString(employee1);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/public/AddEmployee")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(employee_as_string);
        moc.perform(mockRequest).andExpect(status().isOk());
    }
    @Test
    void getEmployee()  throws Exception{
        employee1.setEmail("test@gmail.com");
        employee1.setMot_de_passe("test");
        employee1.setNom("test");
        employee1.setPrenom("test");
        when(employeeCrudService.Add(employee1)).thenReturn(employee1);
        String employee_as_string = OW.writeValueAsString(employee1);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/GetEmployee/1")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(employee_as_string);
        moc.perform(mockRequest).andExpect(status().isOk());
    }
    @Test
    void updateEmployee()  throws Exception{
        employee1.setId(1);
        employee1.setEmail("test@gmail.com");
        employee1.setMot_de_passe("test");
        employee1.setNom("test");
        employee1.setPrenom("test");

        when(employeeCrudService.Get(1)).thenReturn(employee1);
        when(employeeCrudService.Add(employee1)).thenReturn(employee1);
        String employee_as_string = OW.writeValueAsString(employee1);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/UpdateEmployee/1")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(employee_as_string);

       moc.perform(mockRequest).andExpect(status().isOk());

    }
    @Test
    void deleteEmployee() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/DeleteEmployee/1")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        moc.perform(mockRequest).andExpect(status().isOk());


    }
}