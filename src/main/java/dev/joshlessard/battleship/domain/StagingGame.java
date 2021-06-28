package dev.joshlessard.battleship.domain;

import java.util.HashMap;
import java.util.Map;

class StagingGame {

    private final Map<PlayerId, StagingGrid> stagingGridsByPlayerId = new HashMap<>();

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

    void addShip( PlayerId playerId, Ship ship, Coordinate topLeft, Direction direction ) {
        ensureKnownPlayer( playerId );
        stagingGridsByPlayerId.get( playerId )
            .addShip( ship, topLeft, direction );
    }

    private void ensureKnownPlayer( PlayerId playerId ) {
        if ( ! stagingGridsByPlayerId.containsKey(playerId) ) {
            throw new UnknownPlayerException();
        }
    }

    // todo Should we return a copy?
    StagingGrid stagingGrid( PlayerId playerId ) {
        ensureKnownPlayer( playerId );
        return stagingGridsByPlayerId.get( playerId );
    }

    void removePlayer( PlayerId playerId ) {
        stagingGridsByPlayerId.remove( playerId );
    }

    void buildGame() {
        if ( stagingGridsByPlayerId.size() < 2 ) {
            throw new TooFewPlayersException();
        }
        throw new GameNotReadyException();
    }
}
