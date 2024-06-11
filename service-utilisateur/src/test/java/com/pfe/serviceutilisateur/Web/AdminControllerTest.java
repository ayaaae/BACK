package com.pfe.serviceutilisateur.Web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pfe.serviceutilisateur.Entities.Admin;
import com.pfe.serviceutilisateur.Security.ApplicationConfig;
import com.pfe.serviceutilisateur.Service.AdminServiceImplementation;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
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
public class AdminControllerTest {

    @Autowired
    private MockMvc moc;

    @MockBean
    private AdminServiceImplementation adminCrudService;





    private ObjectMapper OM = new ObjectMapper();
    ObjectWriter OW = OM.writer();
    Admin admin1 = new Admin();
    Admin admin2 = new Admin();
    @Ignore("Class not ready for tests bc of spring security")

@Test
    @WithMockUser(username = "test@gmail.com",password = "123")
    public void lesUtilisateur() throws Exception {


        admin1.setId(1);
        admin1.setEmail("admin1@gmail.com");

        admin2.setId(2);
        admin2.setEmail("admin2@gmail.com");

        List<Admin> admins = new ArrayList<Admin>();
        admins.add(admin1);
        admins.add(admin2);
        when(adminCrudService.All()).thenReturn(admins);

        moc.perform(get("/ListAdmin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].email", is("admin2@gmail.com")));

    }
    @Ignore("Class not ready for tests bc of spring security")
    @Test
    @WithMockUser(username = "test@gmail.com",password = "123")

    void add() throws Exception {

        admin1.setEmail("test@gmail.com");
        admin1.setMot_de_passe("test");
        admin1.setNom("test");
        admin1.setPrenom("test");
        when(adminCrudService.Add(admin1)).thenReturn(admin1);
        String admin_as_string = OW.writeValueAsString(admin1);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/public/AddAdmin")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(admin_as_string);
        moc.perform(mockRequest).andExpect(status().isOk());
    }
    @Ignore("Class not ready for tests bc of spring security")
    @Test
    @WithMockUser(username = "test@gmail.com",password = "123")
    void getUser() throws Exception {


        admin1.setId(1);
        admin1.setEmail("test@gmail.com");
        admin1.setMot_de_passe("test");
        admin1.setNom("test");
        admin1.setPrenom("test");

        when(adminCrudService.Get(1)).thenReturn(admin1);

        moc.perform(get("/GetAdmin/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("email", is("test@gmail.com")));


    }
    @Ignore("Class not ready for tests bc of spring security")
    @Test
    @WithMockUser(username = "test@gmail.com",password = "123")
    void updateUser() throws Exception {
        admin1.setId(1);
        admin1.setEmail("test@gmail.com");
        admin1.setMot_de_passe("test");
        admin1.setNom("test");
        admin1.setPrenom("test");

        when(adminCrudService.Get(1)).thenReturn(admin1);
        when(adminCrudService.Add(admin1)).thenReturn(admin1);
        String admin_as_string = OW.writeValueAsString(admin1);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/UpdateAdmin/1")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(admin_as_string);

moc.perform(mockRequest).andExpect(status().isOk());
    }
    @Ignore("Class not ready for tests bc of spring security")


    void deleteAdmin() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/DeleteAdmin/1")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        moc.perform(mockRequest).andExpect(status().isOk());


    }


}