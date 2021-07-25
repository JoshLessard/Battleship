package dev.joshlessard.battleship.adapter.web;

import static java.util.Collections.emptyMap;

import java.util.Map;

import dev.joshlessard.battleship.domain.StagingGame;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
class StagingGameView {

    private final long gameId;
    private final Map<String, Object> myShips;

    StagingGameView( long gameId, Map<String, Object> myShips ) {
        this.gameId = gameId;
        this.myShips = Map.copyOf( myShips );
    }

    public static StagingGameView of( StagingGame stagingGame ) {
        return new StagingGameView( stagingGame.id().asLong(), emptyMap() );
    }
}
