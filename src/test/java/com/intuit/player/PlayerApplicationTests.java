package com.intuit.player;

import com.intuit.player.controller.PlayerController;
import com.intuit.player.dto.ApiResponseWrapper;
import com.intuit.player.dto.Player;
import com.intuit.player.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PlayerApplicationTests {
	@InjectMocks
	private PlayerController playerController;

	@Mock
	private PlayerService playerService;

    @Test
    public void testGetPlayerValidId() {
        // Set up a valid player ID and mock the PlayerDto object that should be returned
        String playerId = "validPlayerId";
        Player player = new Player();
        player.setPlayerId(playerId);
        ApiResponseWrapper apiResponseWrapper = new ApiResponseWrapper(player);
        when(playerService.getPlayer(playerId)).thenReturn(apiResponseWrapper);

        // Call the getPlayer method with the valid player ID
        ResponseEntity<ApiResponseWrapper> response = playerController.getPlayer(playerId);

        // Verify the response status is OK
        // Check that the response body is not null and contains the expected PlayerDto object
        // Verify that the getPlayer method in playerOrchestrator was called exactly once with the given player ID
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(player, response.getBody().getResponse());
        verify(playerService, times(1)).getPlayer(playerId);
    }

    @Test
    public void testGetPlayerEmptyId() {
        // Set up an empty player ID string
        String playerId = "";

        // Call the getPlayer method with the empty player ID
        ResponseEntity<ApiResponseWrapper> response = playerController.getPlayer(playerId);

        // Verify that the response status is BAD_REQUEST
        // Check that the response body is not null and contains the correct error message
        // Verify that the getPlayer method in playerOrchestrator was not called at all
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Player ID is missing from the request.", response.getBody().getMessage());
        verify(playerService, times(0)).getPlayer(anyString());
    }


    @Test
    public void testGetPlayerNotExistId() {
        // Set up a non-existent player ID and response error message
        String playerId = "nonExistentPlayerId";
        String errorMessage = "Player not found with ID: " + playerId;
        ApiResponseWrapper apiResponseWrapper = new ApiResponseWrapper(errorMessage);

        // Mock the service call to return null when the ID does not exist
        when(playerService.getPlayer(playerId)).thenReturn(apiResponseWrapper);

        // Call the getPlayer method with the non-existent player ID
        ResponseEntity<ApiResponseWrapper> response = playerController.getPlayer(playerId);

        // Verify that the response status is OK
        // Check that the response body is not null and contains the correct error message
        // Verify that the getPlayer method in playerOrchestrator was called once with the non-existent ID
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getMessage());
        assertEquals(errorMessage, response.getBody().getMessage());
        verify(playerService, times(1)).getPlayer(playerId);
    }
}

