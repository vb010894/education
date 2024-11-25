package com.github.vb010894.sv.task.repositories;

import com.github.vb010894.sv.task.domain.ProductPrice;
import com.github.vb010894.sv.task.domain.ProductionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<ProductPrice, Long> {

    @Query("""
            FROM ProductPrice pr
            where pr.productionEntity.id = :production
            """)
    List<ProductPrice> getPrices(long production);
}
