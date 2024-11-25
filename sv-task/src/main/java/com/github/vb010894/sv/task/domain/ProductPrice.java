package com.github.vb010894.sv.task.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/// # Цены продукции
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(indexes = @Index(name = "product_price_index", columnList = "id, validFrom, validTO", unique = true))
public class ProductPrice {

    /// ИД цены
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /// Доступна от
    LocalDate validFrom;

    /// Доступна до
    LocalDate validTo;

    /// Стоимость
    Float price;

    /// Проукция
    @ManyToOne
    @JoinColumn(name = "production_id")
    ProductionEntity productionEntity;

    @Override
    public String toString() {
        return this.price.toString();
    }
}
