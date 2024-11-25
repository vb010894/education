package com.github.vb010894.sv.task.services;

import com.github.vb010894.sv.task.domain.ProductPrice;
import com.github.vb010894.sv.task.domain.ProductionEntity;
import com.github.vb010894.sv.task.repositories.PriceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PriceService {

    PriceRepository priceRepository;

    public List<ProductPrice> getPricesByProduct(ProductionEntity production) {
        return priceRepository.getPrices(production.getId());
    }

    public boolean checkPrice(ProductPrice productPrice) {
        return  productPrice.getValidTo().isAfter(LocalDate.now());
    }

    public ProductPrice savePrice(ProductPrice productPrice) {
        return priceRepository.save(productPrice);
    }

    public void deletePrice(long priceID) {
        priceRepository.deleteById(priceID);
    }
}
