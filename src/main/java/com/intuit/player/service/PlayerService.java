package com.intuit.player.service;


import com.intuit.player.data.PlayerDataLoader;
import com.intuit.player.dto.ApiResponseWrapper;
import com.intuit.player.dto.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PlayerService {
    private Map<String, Player> playersById;

    public PlayerService(PlayerDataLoader playerDataLoader) {
        this.playersById = playerDataLoader.loadPlayerDataFromCsv().stream().collect(Collectors.toMap(Player::getPlayerId, playerDto -> playerDto));
    }

    public ApiResponseWrapper getAllPlayers() {

        if(playersById.isEmpty()){
            log.info("No players found.");
            return new ApiResponseWrapper("No players found.");
        }

        return new ApiResponseWrapper(playersById.values().stream().toList());
    }

    public ApiResponseWrapper getPlayer(String playerId) {

        Player player = playersById.get(playerId);

        if(player == null){
            log.info("Player not found with id: " + playerId);
            return new ApiResponseWrapper("Player not found with id: " + playerId);
        }

        return new ApiResponseWrapper(player);
    }
}
