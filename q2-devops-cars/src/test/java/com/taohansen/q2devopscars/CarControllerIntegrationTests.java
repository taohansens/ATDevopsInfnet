package com.taohansen.q2devopscars;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taohansen.q2devopscars.entities.Car;
import com.taohansen.q2devopscars.repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CarControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    void setUp() throws Exception {
        repository.deleteAll();

        Car car1 = new Car();
        car1.setModelo("Creta");
        car1.setFabricante("Hyunday");
        car1.setAno(2020);
        repository.save(car1);

        Car car2 = new Car();
        car2.setModelo("Etios");
        car2.setFabricante("Toyota");
        car2.setAno(2021);
        repository.save(car2);

        existingId = car1.getId();
        nonExistingId = 1000L;
    }

    @Test
    public void getAllShouldReturnAllCars() throws Exception {
        mockMvc.perform(get("/carros")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(existingId))
                .andExpect(jsonPath("$[0].modelo").value("Creta"))
                .andExpect(jsonPath("$[0].fabricante").value("Hyunday"))
                .andExpect(jsonPath("$[0].ano").value("2020"))
                .andExpect(jsonPath("$[1].fabricante").value("Toyota"));
    }

    @Test
    public void findByIdShouldReturnCarWhenIdExists() throws Exception {
        mockMvc.perform(get("/carros/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingId))
                .andExpect(jsonPath("$.modelo").value("Creta"))
                .andExpect(jsonPath("$.fabricante").value("Hyunday"))
                .andExpect(jsonPath("$.ano").value("2020"));
    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        mockMvc.perform(get("/carros/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void insertShouldCreateCar() throws Exception {
        Car entity = new Car();
        entity.setModelo("Strada");
        entity.setFabricante("Fiat");

        String json = objectMapper.writeValueAsString(entity);

        mockMvc.perform(post("/carros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.modelo").value("Strada"))
                .andExpect(jsonPath("$.fabricante").value("Fiat"));
    }

    @Test
    public void updateShouldUpdateCarWhenIdExists() throws Exception {
        Car entity = new Car();
        entity.setModelo("Polo");
        entity.setFabricante("Volkswagen");

        String json = objectMapper.writeValueAsString(entity);

        mockMvc.perform(put("/carros/{id}", existingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingId))
                .andExpect(jsonPath("$.modelo").value("Polo"));
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        Car entity = new Car();
        entity.setModelo("Fusca");
        entity.setFabricante("Fiat");

        String json = objectMapper.writeValueAsString(entity);

        mockMvc.perform(put("/empregados/{id}", nonExistingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteShouldRemoveCarWhenIdExists() throws Exception {
        mockMvc.perform(delete("/carros/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        boolean exists = repository.existsById(existingId);
        assertThat(exists).isFalse();
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        mockMvc.perform(delete("/carros/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
