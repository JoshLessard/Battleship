package dev.joshlessard.battleship.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class StagingGame {

    private final Map<PlayerId, StagingGrid> stagingGridsByPlayerId = new HashMap<>();

    StagingGame( PlayerId playerId ) {
        this.stagingGridsByPlayerId.put( playerId, new StagingGrid() );
    }

    void addShip( PlayerId playerId, Ship ship, Coordinate topLeft, Direction direction ) {
        if ( ! stagingGridsByPlayerId.containsKey( playerId ) ) {
            throw new UnknownPlayerException();
        }
        stagingGridsByPlayerId.get( playerId )
            .addShip( ship, topLeft, direction );
    }

    // todo Should we return a copy?
    StagingGrid stagingGrid( PlayerId player1Id ) {
        return Optional.ofNullable( stagingGridsByPlayerId.get( player1Id ) )
            .orElseThrow( UnknownPlayerException::new );
    }

    void addPlayer( PlayerId playerId ) {
        ensurePlayerIsNew( playerId );
        ensureRoomForAnotherPlayer();
        stagingGridsByPlayerId.put( playerId, new StagingGrid() );
    }

    private void ensurePlayerIsNew( PlayerId playerId ) {
        if ( stagingGridsByPlayerId.containsKey( playerId ) ) {
            throw new DuplicatePlayerException();
        }
    }

    private void ensureRoomForAnotherPlayer() {
        if ( stagingGridsByPlayerId.size() == 2 ) {
            throw new TooManyPlayersException();
        }
    }
}
