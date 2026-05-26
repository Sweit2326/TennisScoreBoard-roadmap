package com.roadmap.fourth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
// @EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "Matches")

public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "Player1", referencedColumnName = "id", nullable = false)
    private Player player1;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "Player2", referencedColumnName = "id", nullable = false)
    private Player player2;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "Winner", referencedColumnName = "id", nullable = false)
    private Player winner;
}