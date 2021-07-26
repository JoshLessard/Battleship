package dev.joshlessard.battleship.domain;

import java.util.concurrent.atomic.AtomicLong;

public class FakeStagingGameService implements StagingGameService {

    private final AtomicLong gameIdGenerator;

    public FakeStagingGameService( long nextGameId ) {
        this.gameIdGenerator = new AtomicLong( nextGameId );
    }

    @Override
    public StagingGame newStagingGame( PlayerId playerId ) {
        StagingGame stagingGame = new StagingGame();
        stagingGame.setId( StagingGameId.of( gameIdGenerator.getAndIncrement() ) );
        stagingGame.addPlayer( playerId );
        return stagingGame;
    }
}
