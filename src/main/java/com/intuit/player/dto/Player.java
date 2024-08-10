package com.intuit.player.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Player {
    private String playerId;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private Integer deathYear;
    private Integer deathMonth;
    private Integer deathDay;
    private String deathCounty;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private Integer weight;
    private Integer height;
    private char bats;
    private char playerThrows;
    private LocalDate debut;
    private LocalDate finalGame;
    private String retroID;
    private String bbrefID;

}
