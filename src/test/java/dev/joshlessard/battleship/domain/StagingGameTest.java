package dev.joshlessard.battleship.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class StagingGameTest {

    @Test
    public void cannotAddThirdPlayerToStagingGame() {
        PlayerId player1Id = PlayerId.of( 8794237893L );
        PlayerId player2Id = PlayerId.of( 19823978234L );
        PlayerId player3Id = PlayerId.of( 988987342L );
        StagingGame stagingGame = new StagingGame( player1Id );

        stagingGame.addPlayer( player2Id );

        assertThatThrownBy( () -> stagingGame.addPlayer( player3Id ) )
            .isInstanceOf( TooManyPlayersException.class );
    }

    @Test
    public void cannotRegisterPlayer1Twice() {
        PlayerId player1Id = PlayerId.of( 389342798L );
        StagingGame stagingGame = new StagingGame( player1Id );

        assertThatThrownBy( () -> stagingGame.addPlayer( player1Id ) )
            .isInstanceOf( DuplicatePlayerException.class );
    }

    @Test
    public void cannotRegisterPlayer2Twice() {
        PlayerId player1Id = PlayerId.of( 743897423L );
        PlayerId player2Id = PlayerId.of( 69843789234L );
        StagingGame stagingGame = new StagingGame( player1Id );
        stagingGame.addPlayer( player2Id );

        assertThatThrownBy( () -> stagingGame.addPlayer( player2Id ) )
            .isInstanceOf( DuplicatePlayerException.class );
    }

    @Test
    public void player2MustBeRegisteredWithStagingGameBeforeCheckingStagingGrid() {
        PlayerId player1Id = PlayerId.of( 439278432L );
        StagingGame stagingGame = new StagingGame( player1Id );

        PlayerId player2Id = PlayerId.of( 19238923432342L );
        assertThatThrownBy( () -> stagingGame.stagingGrid( player2Id ) )
            .isInstanceOf( UnknownPlayerException.class );
    }

    @Test
    public void player2MustBeRegisteredWithStagingGameBeforeAddingShips() {
        PlayerId player1Id = PlayerId.of( 12345L );
        StagingGame stagingGame = new StagingGame( player1Id );

        PlayerId unregisteredPlayerId = PlayerId.of( 23456L );
        assertThatThrownBy( () -> stagingGame.addShip( unregisteredPlayerId, Ship.DESTROYER, Coordinate.of( Row.of( 'H' ), Column.of( 8 ) ), Direction.RIGHT ) )
            .isInstanceOf( UnknownPlayerException.class );
    }

    @Test
    public void player1StagingGridStartsOffEmpty() {
        PlayerId player1Id = PlayerId.of( 182938L );
        StagingGame stagingGame = new StagingGame( player1Id );

        assertThat( stagingGame.stagingGrid( player1Id ) )
            .isEqualTo( new StagingGrid() );
    }

    @Test
    public void player2StagingGridStartsOffEmpty() {
        PlayerId player1Id = PlayerId.of( 8432978324L );
        StagingGame stagingGame = new StagingGame( player1Id );

        PlayerId player2Id = PlayerId.of( 38324978423L );
        stagingGame.addPlayer( player2Id );

        assertThat( stagingGame.stagingGrid( player2Id ) )
            .isEqualTo( new StagingGrid() );
    }

    @Test
    public void player1CanAddShipsBeforePlayer2Registers() {
        PlayerId player1Id = PlayerId.of( 8293123L );
        StagingGame stagingGame = new StagingGame( player1Id );
        StagingGrid expectedPlayer1StagingGrid = new StagingGrid();

        stagingGame.addShip( player1Id, Ship.BATTLESHIP, Coordinate.of( Row.of( 'C' ), Column.of( 4 ) ), Direction.DOWN );
        expectedPlayer1StagingGrid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'C' ), Column.of( 4 ) ), Direction.DOWN );

        assertThat( stagingGame.stagingGrid( player1Id ) )
            .isEqualTo( expectedPlayer1StagingGrid );
    }
}
