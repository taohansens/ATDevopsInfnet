package com.taohansen.q2devopscars;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taohansen.q2devopscars.controllers.CarController;
import com.taohansen.q2devopscars.entities.Car;
import com.taohansen.q2devopscars.services.CarService;
import com.taohansen.q2devopscars.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
public class CarControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setId(1L);
        car.setModelo("Tracker");
        car.setFabricante("Chevrolet");
        car.setPreco(119000.0);
    }

    @Test
    public void getAllCarsShouldReturnListOfDTOs() throws Exception {
        when(service.findAll()).thenReturn(List.of(car));

        mockMvc.perform(get("/carros"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(car.getId()))
                .andExpect(jsonPath("$[0].modelo").value(car.getModelo()))
                .andExpect(jsonPath("$[0].fabricante").value(car.getFabricante()));

        verify(service, times(1)).findAll();
    }

    @Test
    public void getCarByIdShouldReturnCarWhenIdExists() throws Exception {
        when(service.findById(1L)).thenReturn(car);

        mockMvc.perform(get("/carros/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(car.getId()))
                .andExpect(jsonPath("$.modelo").value(car.getModelo()))
                .andExpect(jsonPath("$.fabricante").value(car.getFabricante()));

        verify(service, times(1)).findById(1L);
    }

    @Test
    public void getCarByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        when(service.findById(1L)).thenThrow(new ResourceNotFoundException("Carro não encontrado"));

        mockMvc.perform(get("/carros/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(service, times(1)).findById(1L);
    }

    @Test
    public void createCarroShouldReturnCreatedStatus() throws Exception {
        when(service.insert(any(Car.class))).thenReturn(car);

        mockMvc.perform(post("/carros")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(car.getId()))
                .andExpect(jsonPath("$.modelo").value(car.getModelo()))
                .andExpect(jsonPath("$.fabricante").value(car.getFabricante()));

        verify(service, times(1)).insert(any(Car.class));
    }

    @Test
    public void updateEmpregadoShouldReturnUpdatedDTO() throws Exception {
        when(service.update(eq(1L), any(Car.class))).thenReturn(car);

        mockMvc.perform(put("/carros/{id}", 1L)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(car.getId()))
                .andExpect(jsonPath("$.modelo").value(car.getModelo()))
                .andExpect(jsonPath("$.fabricante").value(car.getFabricante()));

        verify(service, times(1)).update(eq(1L), any(Car.class));
    }

    @Test
    public void deleteCarShouldReturnNoContentWhenSuccessful() throws Exception {
        doNothing().when(service).deleteById(1L);

        mockMvc.perform(delete("/carros/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteById(1L);
    }

    @Test
    public void deleteCarroShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        doThrow(new ResourceNotFoundException("Carro não encontrado")).when(service).deleteById(1L);

        mockMvc.perform(delete("/carros/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(service, times(1)).deleteById(1L);
    }

}