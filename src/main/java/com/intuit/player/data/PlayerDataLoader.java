package com.intuit.player.data;

import com.intuit.player.dto.Player;
import com.intuit.player.transformer.PlayerDataTransformer;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PlayerDataLoader {

    @Value("${app.csv.data.file.path}")
    private String csvDataFilePath;
    private PlayerDataTransformer playerDataTransformer;

    public PlayerDataLoader(PlayerDataTransformer playerDataTransformer) {
        this.playerDataTransformer = new PlayerDataTransformer();
    }

    public List<Player> loadPlayerDataFromCsv() {

        log.info("Loading data...");
        List<Player> players = new ArrayList<>();

        try (FileReader fileReader = new FileReader(csvDataFilePath);
             CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build()) {

            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                Player player = playerDataTransformer.toPlayer(nextRecord);
                if(player != null) {
                    players.add(player);
                }
            }
        } catch (IOException e) {
            log.error("Failed to open file " + csvDataFilePath, e);
            throw new RuntimeException("Failed to open file: " + csvDataFilePath, e);
        } catch (CsvValidationException e) {
            log.error("Failed to read file " + csvDataFilePath, e);
            throw new RuntimeException("Failed to read file: " + csvDataFilePath, e);
        }

        if (players.isEmpty()) {
            log.warn("No data found in the CSV file.");
        }else{
            log.info("Data loaded successfully.");
        }

        return players;
    }

}
