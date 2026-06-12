package com.roadmap.fourth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "Players",
        indexes = @Index(columnList = "name")
)

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Getter
    @Column(name = "Name", unique = true, nullable = false, length = 50)
    private String name;

    public Player(String name) {
        if (name == null && name.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be empty");
        }
        this.name = name;
    }
}

