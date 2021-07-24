package dev.joshlessard.battleship.domain;

public class StagingGameService {

    private final StagingGameRepository stagingGameRepository;

    public StagingGameService( StagingGameRepository stagingGameRepository ) {
        this.stagingGameRepository = stagingGameRepository;
    }

    public StagingGame newStagingGame( PlayerId playerId ) {
        StagingGame stagingGame = new StagingGame();
        stagingGame.addPlayer( playerId );
        stagingGameRepository.save( stagingGame );
        return stagingGame;
    }
}
