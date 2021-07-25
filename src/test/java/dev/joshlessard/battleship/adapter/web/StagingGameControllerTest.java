package dev.joshlessard.battleship.adapter.web;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import dev.joshlessard.battleship.domain.TestStagingGameService;

public class StagingGameControllerTest {

    private static final long DUMMY_PLAYER_ID = 78432789234L;

    @Test
    public void newStagingGameReturnsStagingGameViewWithGameIdAndNoShips() {
        long gameId = 8943278324L;
        TestStagingGameService stagingGameService = new TestStagingGameService( gameId );
        StagingGameController controller = new StagingGameController( stagingGameService );

        StagingGameView gameView = controller.newStagingGame( new NewStagingGameRequest( DUMMY_PLAYER_ID ) );

        assertThat( gameView )
            .isEqualTo( new StagingGameView( gameId, emptyMap() ) );
    }
}
