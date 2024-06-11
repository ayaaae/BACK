package com.pfe.servicetache.Web;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pfe.servicetache.Entities.Taches;
import com.pfe.servicetache.Service.TacheService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TacheController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class TacheControllerTest {
    @Autowired
    private MockMvc moc;

    @MockBean
    private TacheService tachesService;

    private ObjectMapper OM = new ObjectMapper();
    ObjectWriter OW = OM.writer();
    Taches taches1 = new Taches();

    Taches taches2 = new Taches();
    @Test
    void getAllTaches() throws Exception {
        taches1.setNom_tache("taches1");taches1.setDescription_tache("thiss is taches 1");
        taches2.setNom_tache("taches2");taches2.setDescription_tache("thiss is taches 2");
        List<Taches> taches = new ArrayList<Taches>();
        taches.add(taches1);
        taches.add(taches2);
        when(tachesService.findAllTache()).thenReturn(taches);

        moc.perform(get("/Taches/AllTaches")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].nom_tache", is("taches2")));
    }

    @Test
    void getTache() throws Exception {
        taches1.setId_tache(1); taches1.setNom_tache("taches1");taches1.setDescription_tache("this is taches 1");
        when(tachesService.findTacheById(1L)).thenReturn(taches1);
        moc.perform(get("/Taches/tache/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description_tache", is("this is taches 1")));


    }

    @Test
    void addTache() throws Exception {
        taches1.setId_tache(1); taches1.setNom_tache("taches1");taches1.setDescription_tache("this is taches 1");
        when(tachesService.addTache(taches1)).thenReturn(taches1);
        String taches_as_string = OW.writeValueAsString(taches1);
        moc.perform(post("/Taches/createTache")
                        .contentType(MediaType.APPLICATION_JSON).content(taches_as_string))
                .andExpect(status().isCreated());

    }

    @Test
    void updateTache() throws Exception {
        taches1.setId_tache(1); taches1.setNom_tache("taches1");taches1.setDescription_tache("this is taches 1");
        taches2.setId_tache(1); taches2.setNom_tache("taches1");taches1.setDescription_tache("this is taches 1 updated");

        when(tachesService.findTacheById(1L)).thenReturn(taches1);
        when(tachesService.updateTache(taches1)).thenReturn(taches2);
        String taches_as_string = OW.writeValueAsString(taches2);
        moc.perform(put("/Taches/update/1")
                        .contentType(MediaType.APPLICATION_JSON).content(taches_as_string))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTache() throws Exception {
        doNothing().when(tachesService).deleteTacheById(1L);

        moc.perform(delete("/Taches/delete/1"))
                .andExpect(status().isNoContent());
    }

    
}