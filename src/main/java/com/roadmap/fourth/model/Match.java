package com.roadmap.fourth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.DialectOverride;

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