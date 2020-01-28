package com.kapono.ruble.game.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "game")
public class GameEntity extends BaseEntity {

    public static final String GAME_ID_SEQ = "game_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = GAME_ID_SEQ)
    @SequenceGenerator(name = GAME_ID_SEQ, sequenceName = GAME_ID_SEQ, allocationSize = 1)
    private Long id;

    @Column
    private Integer cost;

    @Column
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "winner")
    private UsersEntity winner;

}
