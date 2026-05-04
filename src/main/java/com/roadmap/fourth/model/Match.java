package com.roadmap.fourth.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Matches")

public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "Player1", referencedColumnName = "id", nullable = false)
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "Player2", referencedColumnName = "id", nullable = false)
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "Winner", referencedColumnName = "id", nullable = false)
    private Player winner;
}