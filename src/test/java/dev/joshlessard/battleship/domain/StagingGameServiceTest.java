package dev.joshlessard.battleship.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StagingGameServiceTest {

    @Test
    public void newStagingGameAddsNewStagingGameWithGivenPlayerIdToRepository() {
        TestStagingGameRepository repository = new TestStagingGameRepository();
        StagingGameService service = new StagingGameService( repository );
        PlayerId playerId = PlayerId.of( 812341L );

        StagingGame stagingGame = service.newStagingGame( playerId );

        assertThat( stagingGame.containsPlayer( playerId ) )
            .isTrue();
        assertThat( repository.all() )
            .containsExactly( stagingGame );
    }
}
