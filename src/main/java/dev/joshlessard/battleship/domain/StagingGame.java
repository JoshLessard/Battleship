package dev.joshlessard.battleship.domain;

import java.util.HashMap;
import java.util.Map;

public class StagingGame {

    private StagingGameId id;
    private final Map<PlayerId, StagingGrid> stagingGridsByPlayerId = new HashMap<>();

    public StagingGameId id() {
        return id;
    }

    // todo Should I hide this and have tests set via reflection?
    public void setId( StagingGameId id ) {
        this.id = id;
    }

    void addPlayer( PlayerId playerId ) {
        ensurePlayerIsUnique( playerId );
        ensureRoomForAnotherPlayer();
        stagingGridsByPlayerId.put( playerId, new StagingGrid() );
    }

    private void ensurePlayerIsUnique( PlayerId playerId ) {
        if ( stagingGridsByPlayerId.containsKey( playerId ) ) {
            throw new DuplicatePlayerException();
        }
    }

    // todo: race condition if two "second players" try to join at the same time
    private void ensureRoomForAnotherPlayer() {
        if ( stagingGridsByPlayerId.size() >= 2 ) {
            throw new TooManyPlayersException();
        }
    }

    void removePlayer( PlayerId playerId ) {
        ensurePlayerIsKnown( playerId );
        stagingGridsByPlayerId.remove( playerId );
    }

    private void ensurePlayerIsKnown( PlayerId playerId ) {
        if ( ! stagingGridsByPlayerId.containsKey(playerId) ) {
            throw new UnknownPlayerException();
        }
    }

    public boolean containsPlayer( PlayerId playerId ) {
        return stagingGridsByPlayerId.containsKey( playerId );
    }

    void addShip( PlayerId playerId, Ship ship, Coordinate topLeft, Direction direction ) {
        ensurePlayerIsKnown( playerId );
        stagingGridsByPlayerId.get( playerId )
            .addShip( ship, topLeft, direction );
    }

    // todo Should we return a copy?
    StagingGrid stagingGrid( PlayerId playerId ) {
        ensurePlayerIsKnown( playerId );
        return stagingGridsByPlayerId.get( playerId );
    }

    void buildGame() {
        if ( stagingGridsByPlayerId.size() < 2 ) {
            throw new TooFewPlayersException();
        }
        throw new GameNotReadyException();
    }
}
