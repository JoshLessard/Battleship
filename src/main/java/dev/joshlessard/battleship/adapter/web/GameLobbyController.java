package dev.joshlessard.battleship.adapter.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "battleship/api/gameLobby" )
public class GameLobbyController {

    @PostMapping
    public void newGameLobby( @RequestBody NewGameLobbyRequest request ) {
    }
}
