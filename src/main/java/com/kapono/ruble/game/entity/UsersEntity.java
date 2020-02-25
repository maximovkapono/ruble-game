package com.kapono.ruble.game.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UsersEntity extends BaseEntity {

    public static final String USERS_ID_SEQ = "game_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = USERS_ID_SEQ)
    @SequenceGenerator(name = USERS_ID_SEQ, sequenceName = USERS_ID_SEQ, allocationSize = 1)
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

}
