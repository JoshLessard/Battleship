package dev.joshlessard.battleship.adapter.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.joshlessard.battleship.domain.PlayerId;
import dev.joshlessard.battleship.domain.StagingGame;
import dev.joshlessard.battleship.domain.StagingGameService;

@RestController
@RequestMapping( "battleship/api/stagingGame" )
public class StagingGameController {

    private final StagingGameService stagingGameService;

    public StagingGameController( StagingGameService stagingGameService ) {
        this.stagingGameService = stagingGameService;
    }

    @PostMapping
    public StagingGameView newStagingGame( @RequestBody NewStagingGameRequest request ) {
        StagingGame stagingGame = stagingGameService.newStagingGame( PlayerId.of( request.getPlayerId() ) );
        return StagingGameView.of( stagingGame );
    }
}
