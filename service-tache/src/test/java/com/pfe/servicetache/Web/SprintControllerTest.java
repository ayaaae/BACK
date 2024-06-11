package com.pfe.servicetache.Web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import com.pfe.servicetache.Entities.Sprint;

import com.pfe.servicetache.Service.SprintService;
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

@WebMvcTest(SprintController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class SprintControllerTest {
    @Autowired
    private MockMvc moc;

    @MockBean
    private SprintService sprintService;

    private ObjectMapper OM = new ObjectMapper();
    ObjectWriter OW = OM.writer();
    Sprint sprint1 = new Sprint();

    Sprint sprint2 = new Sprint();
    @Test
    void getAllSprints() throws Exception {
        sprint1.setNom_sprint("sprint1");sprint1.setDescription("thiss is sprint 1");
        sprint2.setNom_sprint("sprint2");sprint2.setDescription("thiss is sprint 2");
        List<Sprint> sprints = new ArrayList<Sprint>();
        sprints.add(sprint1);
        sprints.add(sprint2);
        when(sprintService.findAllSprint()).thenReturn(sprints);

        moc.perform(get("/sprint/AllSprints")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].nom_sprint", is("sprint2")));
    }

    @Test
    void getSprint() throws Exception {
        sprint1.setId_sprint(1); sprint1.setNom_sprint("sprint1");sprint1.setDescription("this is sprint 1");
        when(sprintService.findSprintById(1L)).thenReturn(sprint1);
        moc.perform(get("/sprint/sprint/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("this is sprint 1")));
    }

    @Test
    void getProjetSprints() throws Exception {
        sprint1.setId_sprint(1); sprint1.setNom_sprint("sprint1");sprint1.setDescription("this is sprint 1");sprint1.setIdProjet(1);
        List<Sprint> sprints = new ArrayList<Sprint>();
        sprints.add(sprint1);
        when(sprintService.findSprintByIdProjet(1L)).thenReturn(sprints);
        moc.perform(get("/sprint/findProjectSprints/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom_sprint", is("sprint1")));
    }

    @Test
    void addSprint() throws Exception {
        sprint1.setId_sprint(1); sprint1.setNom_sprint("sprint1");sprint1.setDescription("this is sprint 1");sprint1.setIdProjet(1);
        when(sprintService.addSprint(sprint1)).thenReturn(sprint1);
        String sprint_as_string = OW.writeValueAsString(sprint1);
        moc.perform(post("/sprint/createSprint")
                        .contentType(MediaType.APPLICATION_JSON).content(sprint_as_string))
                .andExpect(status().isCreated());
    }

    @Test
    void updateSprint() throws Exception {
        sprint1.setId_sprint(1); sprint1.setNom_sprint("sprint1");sprint1.setDescription("this is sprint 1");sprint1.setIdProjet(1);
        sprint2.setId_sprint(1); sprint2.setNom_sprint("sprint1");sprint1.setDescription("this is sprint 1 updated");sprint1.setIdProjet(1);

        when(sprintService.findSprintById(1L)).thenReturn(sprint1);
        when(sprintService.updateSprint(sprint1)).thenReturn(sprint2);
        String sprint_as_string = OW.writeValueAsString(sprint2);
        moc.perform(put("/sprint/update/1")
                        .contentType(MediaType.APPLICATION_JSON).content(sprint_as_string))
                .andExpect(status().isOk());
    }

    @Test
    void deleteSprint() throws Exception {
        doNothing().when(sprintService).deleteSprintById(1L);

        moc.perform(delete("/sprint/delete/1"))
                .andExpect(status().isNoContent());
    }




}