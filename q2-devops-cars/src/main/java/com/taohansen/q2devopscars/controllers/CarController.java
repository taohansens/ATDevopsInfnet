package com.taohansen.q2devopscars.controllers;

import com.taohansen.q2devopscars.entities.Car;
import com.taohansen.q2devopscars.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carros")
public class CarController {
    private final CarService service;

    @GetMapping
    public ResponseEntity<List<Car>> findAll() {
        List<Car> cars = service.findAll();
        return ResponseEntity.ok().body(cars);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Car> findById(@PathVariable Long id) {
        Car car = service.findById(id);
        return ResponseEntity.ok().body(car);
    }

    @PostMapping
    public ResponseEntity<Car> insert(@RequestBody Car dto) {
        dto = service.insert(dto);
        return ResponseEntity.status(201).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Car> update(@PathVariable Long id,@RequestBody Car dto) {
        Car entity = service.update(id, dto);
        return ResponseEntity.ok().body(entity);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
