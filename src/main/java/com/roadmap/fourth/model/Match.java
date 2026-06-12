package com.roadmap.fourth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "Matches",
        check = @CheckConstraint(constraint = "(player1 <> player2) AND (winner IN (player1, player2))")
)

public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "Player1", referencedColumnName = "id", nullable = false)
    private Player player1;

    @Getter
    @ManyToOne
    @JoinColumn(name = "Player2", referencedColumnName = "id", nullable = false)
    private Player player2;

    @Getter
    @ManyToOne
    @JoinColumn(name = "Winner", referencedColumnName = "id", nullable = false)
    private Player winner;

    public Match(Player stPlayer, Player ndPlayer, Player winner) {
        if (stPlayer == null || ndPlayer == null || winner == null) {
            throw new NullPointerException("Match cannot be created with null player");
        }
        this.player1 = stPlayer;
        this.player2 = ndPlayer;
        this.winner = winner;
    }
}