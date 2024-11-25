package com.github.vb010894.sv.task.services;

import com.github.vb010894.sv.task.domain.Plant;
import com.github.vb010894.sv.task.repositories.PlantRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlantService {

    PlantRepository plantRepository;

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public Optional<Plant> getPlantById(long id) {
        return plantRepository.findById(id);
    }

    public Plant save(Plant plant) {
        return plantRepository.save(plant);
    }

    public void deletePlant(long id) {
        plantRepository.deleteById(id);
    }
}
