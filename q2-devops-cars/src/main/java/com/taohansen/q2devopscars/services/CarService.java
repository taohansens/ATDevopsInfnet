package com.taohansen.q2devopscars.services;

import com.taohansen.q2devopscars.entities.Car;
import com.taohansen.q2devopscars.repositories.CarRepository;
import com.taohansen.q2devopscars.services.exceptions.DatabaseException;
import com.taohansen.q2devopscars.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    final private CarRepository repository;

    @Transactional(readOnly = true)
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Car findById(Long id) {
        Optional<Car> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(String.format("Empregado %d não encontrado.", id)));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Empregado id %d not found.", id));
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseException("Erro no Banco de Dados ao excluir");
        }
    }

    public Car insert(Car dto) {
        Car obj = new Car();
        obj.setAno(dto.getAno());
        obj.setFabricante(dto.getFabricante());
        obj.setModelo(dto.getModelo());
        obj.setPreco(dto.getPreco());
        return repository.save(obj);
    }

    public Car update(Long id, Car dto) {
        try {
            Car obj = repository.getReferenceById(id);
            obj.setAno(dto.getAno());
            obj.setFabricante(dto.getFabricante());
            obj.setModelo(dto.getModelo());
            obj.setPreco(dto.getPreco());
            return repository.save(obj);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Empregado id %d not found.", id));
        }
    }
}
