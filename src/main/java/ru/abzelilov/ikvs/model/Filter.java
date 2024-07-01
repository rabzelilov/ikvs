package ru.abzelilov.ikvs.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Filter {
    /** Идентификатор штамма */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    /** Род */
    @Column(name = "genus")
    private String genus;

    /** Консорциум */
    @Column(name = "consortium")
    private Boolean consortium;

    /** Название коллекции */
    @Column(name = "name_collection")
    private String nameCollection;
}
