package com.example.bootstrap.web;


import com.example.bootstrap.service.DogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(DogController.class)
public class DogControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DogService dogService;

    @Test
    @WithMockUser(username = "admin", password="password", roles={"USER"})
    public void getAllDogs() throws Exception {
        mockMvc.perform(get("/dogs/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(dogService, times(1)).retrieveDogs();
    }


    @Test
    @WithMockUser(username = "admin", password="password", roles={"USER"})
    public void getDogBreeds() throws Exception {
        mockMvc.perform(get("/dogs/breed/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(dogService, times(1)).retrieveDogBreed();
    }

    @Test
    @WithMockUser(username = "admin", password="password", roles={"USER"})
    public void getBreedByID() throws Exception {
        mockMvc.perform(get("/{id}/breed/", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(dogService, times(1)).retrieveDogBreedById(1L);
    }

    @Test
    @WithMockUser(username = "admin", password="password", roles={"USER"})
    public void getDogNames() throws Exception {
        mockMvc.perform(get("/dogs/name/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(dogService, times(1)).retrieveDogNames();
    }

}
