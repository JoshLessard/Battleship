package dev.joshlessard.battleship.adapter.web;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag( "integration" )
@SpringBootTest
@AutoConfigureMockMvc
public class GameLobbyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void newGameLobbyWithUserIdReturnsHttp200() throws Exception {
        mockMvc.perform(
            post( "/battleship/api/gameLobby" )
                .content( "{ \"userId\": 432 }" )
                .contentType( MediaType.APPLICATION_JSON )
        )
        .andExpect( status().isOk() );
    }
}
