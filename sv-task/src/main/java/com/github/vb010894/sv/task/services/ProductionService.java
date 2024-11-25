package com.github.vb010894.sv.task.services;

import com.github.vb010894.sv.task.domain.Plant;
import com.github.vb010894.sv.task.domain.ProductionEntity;
import com.github.vb010894.sv.task.repositories.ProductionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductionService {

    ProductionRepository repository;

    public List<ProductionEntity> getAll(Plant plant) {
        return repository.getProductions(plant);
    }

    public Optional<ProductionEntity> getById(Long id) {
        return repository.findById(id);
    }

    public ProductionEntity save(ProductionEntity entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
