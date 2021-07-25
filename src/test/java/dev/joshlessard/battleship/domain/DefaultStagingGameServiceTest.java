package dev.joshlessard.battleship.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultStagingGameServiceTest {

    @Test
    public void newStagingGameAddsNewStagingGameWithGivenPlayerIdToRepository() {
        TestStagingGameRepository repository = new TestStagingGameRepository();
        DefaultStagingGameService service = new DefaultStagingGameService( repository );
        PlayerId playerId = PlayerId.of( 812341L );

        StagingGame stagingGame = service.newStagingGame( playerId );

        assertThat( stagingGame.containsPlayer( playerId ) )
            .isTrue();
        assertThat( repository.all() )
            .containsExactly( stagingGame );
    }
}
