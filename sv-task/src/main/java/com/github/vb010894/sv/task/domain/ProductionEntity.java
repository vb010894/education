package com.github.vb010894.sv.task.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import java.util.HashSet;
import java.util.Set;

/// # Продукция
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductionEntity {

    /// ИД проукции
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /// Наименование
    String name;

    /// Завод - производитель
    @ManyToOne
    @JoinColumn(name = "plant_id")
    Plant plant;

    /// Список цен с периодом
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<ProductPrice> prices = new HashSet<>();
}
