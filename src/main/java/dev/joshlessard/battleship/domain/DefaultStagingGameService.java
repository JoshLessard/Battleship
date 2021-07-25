package dev.joshlessard.battleship.domain;

public class DefaultStagingGameService implements StagingGameService {

    private final StagingGameRepository stagingGameRepository;

    public DefaultStagingGameService( StagingGameRepository stagingGameRepository ) {
        this.stagingGameRepository = stagingGameRepository;
    }

    @Override
    public StagingGame newStagingGame( PlayerId playerId ) {
        StagingGame stagingGame = new StagingGame();
        stagingGame.addPlayer( playerId );
        stagingGameRepository.save( stagingGame );
        return stagingGame;
    }
}
