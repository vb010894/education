package com.github.vb010894.sv.task.repositories;

import com.github.vb010894.sv.task.domain.Plant;
import com.github.vb010894.sv.task.domain.ProductionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionRepository extends JpaRepository<ProductionEntity, Long> {

    @Query("""
            FROM ProductionEntity p
            WHERE p.plant = :plant
            """)
    List<ProductionEntity> getProductions(Plant plant);

}
