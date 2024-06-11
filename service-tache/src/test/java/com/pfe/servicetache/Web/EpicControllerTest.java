package com.pfe.servicetache.Web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pfe.servicetache.Entities.Epic;
import com.pfe.servicetache.Service.EmployeeServiceClient;
import com.pfe.servicetache.Service.EpicService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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

@WebMvcTest(EpicController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class EpicControllerTest {


    @Autowired
    private MockMvc moc;

    @MockBean
    private EpicService epicService;

    private ObjectMapper OM = new ObjectMapper();
    ObjectWriter OW = OM.writer();
    Epic epic1 = new Epic();

    Epic epic2 = new Epic();
    @Test
    void getAllEpics() throws Exception {
        epic1.setNom_epic("epic1");epic1.setDescription("thiss is epic 1");epic1.setCouleur("red");
        epic2.setNom_epic("epic2");epic2.setDescription("thiss is epic 2");epic2.setCouleur("green");
        List<Epic> epics = new ArrayList<Epic>();
        epics.add(epic1);
        epics.add(epic2);
        when(epicService.findAllEpics()).thenReturn(epics);

        moc.perform(get("/epic/AllEpics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].nom_epic", is("epic2")));
    }

    @Test
    void getEpic() throws Exception {
       epic1.setId_epic(1); epic1.setNom_epic("epic1");epic1.setDescription("this is epic 1");epic1.setCouleur("red");
        when(epicService.findEpicById(1L)).thenReturn(epic1);
        moc.perform(get("/epic/epic/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("this is epic 1")));


    }

    @Test
    void getProjetEpics() throws Exception {
        epic1.setId_epic(1); epic1.setNom_epic("epic1");epic1.setDescription("this is epic 1");epic1.setCouleur("red");epic1.setIdProjet(1);
        List<Epic> epics = new ArrayList<Epic>();
        epics.add(epic1);
        when(epicService.findEpicByIdProjet(1L)).thenReturn(epics);
        moc.perform(get("/epic/findProjectEpics/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom_epic", is("epic1")));

    }

    @Test
    void addEpic() throws Exception {

        epic1.setId_epic(1); epic1.setNom_epic("epic1");epic1.setDescription("this is epic 1");epic1.setCouleur("red");epic1.setIdProjet(1);
        when(epicService.addEpic(epic1)).thenReturn(epic1);
        String epic_as_string = OW.writeValueAsString(epic1);
        moc.perform(post("/epic/createEpic")
                        .contentType(MediaType.APPLICATION_JSON).content(epic_as_string))
                .andExpect(status().isCreated());

    }

    @Test
    void updateEpic() throws Exception {
        epic1.setId_epic(1); epic1.setNom_epic("epic1");epic1.setDescription("this is epic 1");epic1.setCouleur("red");epic1.setIdProjet(1);
        epic2.setId_epic(1); epic2.setNom_epic("epic1");epic1.setDescription("this is epic 1 updated");epic1.setCouleur("blue");epic1.setIdProjet(1);

        when(epicService.findEpicById(1L)).thenReturn(epic1);
        when(epicService.updateEpic(epic1)).thenReturn(epic2);
        String epic_as_string = OW.writeValueAsString(epic2);
        moc.perform(put("/epic/update/1")
                        .contentType(MediaType.APPLICATION_JSON).content(epic_as_string))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEpic() throws Exception {
        doNothing().when(epicService).deleteEpicById(1L);

        moc.perform(delete("/epic/delete/1"))
                .andExpect(status().isNoContent());
    }
}