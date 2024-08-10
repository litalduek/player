package com.intuit.player.controller;


import com.intuit.player.dto.ApiResponseWrapper;
import com.intuit.player.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class PlayerController {

    public static final String PLAYERS_API_PATH = "/api/players";
    public static final String PLAYERS_API_ID_PARAM = "/{id}";
    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(path = PLAYERS_API_PATH)
    public ResponseEntity<ApiResponseWrapper> getAllPlayers() {
        // The api should be paginated
        ApiResponseWrapper apiResponseWrapper = playerService.getAllPlayers();

        return ResponseEntity.ok().body(apiResponseWrapper);
    }

    @GetMapping(path = PLAYERS_API_PATH + PLAYERS_API_ID_PARAM)
    public ResponseEntity<ApiResponseWrapper> getPlayer(@PathVariable(value = "id") String playerId){
        if(playerId.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponseWrapper("Player ID is missing from the request."));
        }

        ApiResponseWrapper apiResponseWrapper = playerService.getPlayer(playerId);

        return ResponseEntity.ok().body(apiResponseWrapper);
    }

}
