package com.github.vb010894.sv.task.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/// # Завод-производитель
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plant {

    /// ИД завода
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /// Имя завода
    String name;

    /// Список продукции
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<ProductionEntity> productionEntities = new ArrayList<>();

    @Override
    public String toString() {
        return this.name;
    }
}
