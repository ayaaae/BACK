package com.pfe.servicetache.Web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pfe.servicetache.Entities.BackLog;
import com.pfe.servicetache.Entities.BackLog;
import com.pfe.servicetache.Service.BacklogService;
import com.pfe.servicetache.Service.BacklogService;
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

@WebMvcTest(BacklogController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BacklogControllerTest {
    @Autowired
    private MockMvc moc;

    @MockBean
    private BacklogService backlogService;

    private ObjectMapper OM = new ObjectMapper();
    ObjectWriter OW = OM.writer();
    BackLog backlog1 = new BackLog();

    BackLog backlog2 = new BackLog();
    @Test
    void getBacklogs() throws Exception {
         backlog1.setNom_backlog("backlog1"); backlog1.setDescription("thiss is  backlog 1");
         backlog2.setNom_backlog("backlog2"); backlog2.setDescription("thiss is  backlog 2");
        List<BackLog>  backlogs = new ArrayList<BackLog>();
         backlogs.add( backlog1);
         backlogs.add( backlog2);
        when(backlogService.findAllBacklogs()).thenReturn(backlogs);

        moc.perform(get("/backlog/AllBacklogs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].nom_backlog", is("backlog2")));
    }

    @Test
    void getBackLog() throws Exception {

        backlog1.setId_backlog(1); backlog1.setNom_backlog("backlog1");backlog1.setDescription("this is backlog 1");
        when(backlogService.findBacklogById(1L)).thenReturn(backlog1);
        moc.perform(get("/backlog/findBacklog/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("this is backlog 1")));
    }

    @Test
    void getProjetBackLogs() throws Exception {
        backlog1.setId_backlog(1); backlog1.setNom_backlog("backlog1");backlog1.setDescription("this is backlog 1");backlog1.setIdProjet(1L);
        List<BackLog> backlogs = new ArrayList<BackLog>();
        backlogs.add(backlog1);
        when(backlogService.findBackLogByIdProjet(1L)).thenReturn(backlogs);
        moc.perform(get("/backlog/findProjectBacklogs/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom_backlog", is("backlog1")));
    }

    @Test
    void addBackLog() throws Exception {
        backlog1.setId_backlog(1); backlog1.setNom_backlog("backlog1");backlog1.setDescription("this is backlog 1");backlog1.setIdProjet(1L);
        when(backlogService.addBacklog(backlog1)).thenReturn(backlog1);
        String backlog_as_string = OW.writeValueAsString(backlog1);
        moc.perform(post("/backlog/createBacklog")
                        .contentType(MediaType.APPLICATION_JSON).content(backlog_as_string))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBackLog() throws Exception {
        backlog1.setId_backlog(1); backlog1.setNom_backlog("backlog1");backlog1.setDescription("this is backlog 1");backlog1.setIdProjet(1L);
        backlog2.setId_backlog(1); backlog2.setNom_backlog("backlog1");backlog1.setDescription("this is backlog 1 updated");backlog1.setIdProjet(1L);

        when(backlogService.findBacklogById(1L)).thenReturn(backlog1);
        when(backlogService.updateBacklog(backlog1)).thenReturn(backlog2);
        String backlog_as_string = OW.writeValueAsString(backlog2);
        moc.perform(put("/backlog/update/1")
                        .contentType(MediaType.APPLICATION_JSON).content(backlog_as_string))
                .andExpect(status().isOk());
        
        
    }

    @Test
    void deleteBackLog() throws Exception {

        doNothing().when(backlogService).deleteBacklogById(1L);

        moc.perform(delete("/backlog/delete/1"))
                .andExpect(status().isNoContent());

    }
}