package com.intuit.player.transformer;

import com.intuit.player.dto.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Slf4j
@Component
public class PlayerDataTransformer {

    private static final char DEFAULT_CHAR = '0';
    private static final int PLAYER_ID_INDEX = 0;
    private static final int BIRTH_YEAR_INDEX = 1;
    private static final int BIRTH_MONTH_INDEX = 2;
    private static final int BIRTH_DAY_INDEX = 3;
    private static final int BIRTH_COUNTRY_INDEX = 4;
    private static final int BIRTH_STATE_INDEX = 5;
    private static final int BIRTH_CITY_INDEX = 6;
    private static final int DEATH_YEAR_INDEX = 7;
    private static final int DEATH_MONTH_INDEX = 8;
    private static final int DEATH_DAY_INDEX = 9;
    private static final int DEATH_COUNTY_INDEX = 10;
    private static final int DEATH_STATE_INDEX = 11;
    private static final int DEATH_CITY_INDEX = 12;
    private static final int NAME_FIRST_INDEX = 13;
    private static final int NAME_LAST_INDEX = 14;
    private static final int NAME_GIVEN_INDEX = 15;
    private static final int WEIGHT_INDEX = 16;
    private static final int HEIGHT_INDEX = 17;
    private static final int BATS_INDEX = 18;
    private static final int THplayerRawS_INDEX = 19;
    private static final int DEBUT_INDEX = 20;
    private static final int FINAL_GAME_INDEX = 21;
    private static final int RETRO_ID_INDEX = 22;
    private static final int BBREF_ID_INDEX = 23;

    public Player toPlayer(String[] playerRaw) {

        String playerId = parseString(playerRaw[PLAYER_ID_INDEX]);
        if(playerId == null){
            return null;
        }

        Player player = new Player();
        player.setPlayerId(playerId);
        player.setBirthYear(parseInteger(playerRaw[BIRTH_YEAR_INDEX]));
        player.setBirthMonth(parseInteger(playerRaw[BIRTH_MONTH_INDEX]));
        player.setBirthDay(parseInteger(playerRaw[BIRTH_DAY_INDEX]));
        player.setBirthCountry(parseString(playerRaw[BIRTH_COUNTRY_INDEX]));
        player.setBirthState(parseString(playerRaw[BIRTH_STATE_INDEX]));
        player.setBirthCity(parseString(playerRaw[BIRTH_CITY_INDEX]));
        player.setDeathYear(parseInteger(playerRaw[DEATH_YEAR_INDEX]));
        player.setDeathMonth(parseInteger(playerRaw[DEATH_MONTH_INDEX]));
        player.setDeathDay(parseInteger(playerRaw[DEATH_DAY_INDEX]));
        player.setDeathCounty(parseString(playerRaw[DEATH_COUNTY_INDEX]));
        player.setDeathState(parseString(playerRaw[DEATH_STATE_INDEX]));
        player.setDeathCity(parseString(playerRaw[DEATH_CITY_INDEX]));
        player.setNameFirst(parseString(playerRaw[NAME_FIRST_INDEX]));
        player.setNameLast(parseString(playerRaw[NAME_LAST_INDEX]));
        player.setNameGiven(parseString(playerRaw[NAME_GIVEN_INDEX]));
        player.setWeight(parseInteger(playerRaw[WEIGHT_INDEX]));
        player.setHeight(parseInteger(playerRaw[HEIGHT_INDEX]));
        player.setBats(parseChar(playerRaw[BATS_INDEX]));
        player.setPlayerThrows(parseChar(playerRaw[THplayerRawS_INDEX]));
        player.setDebut(parseDate(playerRaw[DEBUT_INDEX]));
        player.setFinalGame(parseDate(playerRaw[FINAL_GAME_INDEX]));
        player.setRetroID(parseString(playerRaw[RETRO_ID_INDEX]));
        player.setBbrefID(parseString(playerRaw[BBREF_ID_INDEX]));

        return player;
    }

    private Integer parseInteger(String value) {
        try {
            return value.isBlank() ? null : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            log.warn("Failed to parse integer from value: " + value);
            return null;
        }
    }

    private String parseString(String value) {
        return value.isBlank() ? null : value;
    }

    private char parseChar(String value) {
        return value.isBlank() ? DEFAULT_CHAR : value.charAt(0);
    }

    private LocalDate parseDate(String value) {
        try {
            return value.isBlank() ? null : LocalDate.parse(value);
        } catch (DateTimeParseException e) {
            log.warn("Failed to parse date from value: " + value);
            return null;
        }
    }
}
