package dev.joshlessard.battleship.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class StagingGameTest {

    @Test
    public void cannotAddThirdPlayer() {
        PlayerId player1Id = PlayerId.of( 8794237893L );
        PlayerId player2Id = PlayerId.of( 19823978234L );
        PlayerId player3Id = PlayerId.of( 988987342L );
        StagingGame stagingGame = new StagingGame();

        stagingGame.addPlayer( player1Id );
        stagingGame.addPlayer( player2Id );

        assertThatThrownBy( () -> stagingGame.addPlayer( player3Id ) )
            .isInstanceOf( TooManyPlayersException.class );
    }

    @Test
    public void cannotAddPlayer1Twice() {
        PlayerId player1Id = PlayerId.of( 389342798L );
        StagingGame stagingGame = new StagingGame();
        stagingGame.addPlayer( player1Id );

        assertThatThrownBy( () -> stagingGame.addPlayer( player1Id ) )
            .isInstanceOf( DuplicatePlayerException.class );
    }

    @Test
    public void cannotAddPlayer2Twice() {
        PlayerId player1Id = PlayerId.of( 743897423L );
        PlayerId player2Id = PlayerId.of( 69843789234L );
        StagingGame stagingGame = new StagingGame();
        stagingGame.addPlayer( player1Id );
        stagingGame.addPlayer( player2Id );

        assertThatThrownBy( () -> stagingGame.addPlayer( player2Id ) )
            .isInstanceOf( DuplicatePlayerException.class );
    }

    @Test
    public void player2MustBeAddedToStagingGameBeforeCheckingStagingGrid() {
        PlayerId player1Id = PlayerId.of( 439278432L );
        PlayerId player2Id = PlayerId.of( 19238923432342L );
        StagingGame stagingGame = new StagingGame();
        stagingGame.addPlayer( player1Id );

        assertThatThrownBy( () -> stagingGame.stagingGrid( player2Id ) )
            .isInstanceOf( UnknownPlayerException.class );
    }

    @Test
    public void player2MustBeAddedToStagingGameBeforeAddingShips() {
        PlayerId player1Id = PlayerId.of( 12345L );
        PlayerId player2Id = PlayerId.of( 23456L );
        StagingGame stagingGame = new StagingGame();
        stagingGame.addPlayer( player1Id );

        assertThatThrownBy( () -> stagingGame.addShip( player2Id, Ship.DESTROYER, Coordinate.of( Row.of( 'H' ), Column.of( 8 ) ), Direction.RIGHT ) )
            .isInstanceOf( UnknownPlayerException.class );
    }

    @Test
    public void player1StagingGridStartsOffEmpty() {
        PlayerId player1Id = PlayerId.of( 182938L );
        StagingGame stagingGame = new StagingGame();
        stagingGame.addPlayer( player1Id );

        assertThat( stagingGame.stagingGrid( player1Id ) )
            .isEqualTo( new StagingGrid() );
    }

    @Test
    public void player2StagingGridStartsOffEmpty() {
        PlayerId player1Id = PlayerId.of( 8432978324L );
        PlayerId player2Id = PlayerId.of( 38324978423L );
        StagingGame stagingGame = new StagingGame();
        stagingGame.addPlayer( player1Id );

        stagingGame.addPlayer( player2Id );

        assertThat( stagingGame.stagingGrid( player2Id ) )
            .isEqualTo( new StagingGrid() );
    }

    @Test
    public void player1CanAddShipsBeforePlayer2Registers() {
        PlayerId player1Id = PlayerId.of( 8293123L );
        StagingGrid expectedPlayer1StagingGrid = new StagingGrid();
        StagingGame stagingGame = new StagingGame();
        stagingGame.addPlayer( player1Id );

        stagingGame.addShip( player1Id, Ship.BATTLESHIP, Coordinate.of( Row.of( 'C' ), Column.of( 4 ) ), Direction.DOWN );
        expectedPlayer1StagingGrid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'C' ), Column.of( 4 ) ), Direction.DOWN );

        assertThat( stagingGame.stagingGrid( player1Id ) )
            .isEqualTo( expectedPlayer1StagingGrid );
    }

    @Test
    public void cannotRemoveUnknownPlayer() {
        PlayerId player1Id = PlayerId.of( 978432423L );
        StagingGame stagingGame = new StagingGame();

        assertThatThrownBy( () -> stagingGame.removePlayer( player1Id ) )
            .isInstanceOf( UnknownPlayerException.class );
    }

    @Test
    public void player3CanJoinIfAnotherPlayerLeaves() {
        PlayerId player1Id = PlayerId.of( 378943298L );
        PlayerId player2Id = PlayerId.of( 89342723432324L );
        PlayerId player3Id = PlayerId.of( 2342323L );
        StagingGame stagingGame = new StagingGame();

        stagingGame.addPlayer( player1Id );
        stagingGame.addPlayer( player2Id );
        stagingGame.removePlayer( player1Id );
        stagingGame.addPlayer( player3Id );

        assertThat( stagingGame.stagingGrid( player3Id ) )
            .isEqualTo( new StagingGrid() );
    }

    @Test
    public void shipsAddedToRespectivePlayersStagingGrids() {
        PlayerId player1Id = PlayerId.of( 48974328234L );
        PlayerId player2Id = PlayerId.of( 1234879423L );
        StagingGrid expectedPlayer1StagingGrid = new StagingGrid();
        StagingGrid expectedPlayer2StagingGrid = new StagingGrid();
        StagingGame stagingGame = new StagingGame();
        stagingGame.addPlayer( player1Id );
        stagingGame.addPlayer( player2Id );

        stagingGame.addShip( player1Id, Ship.SUBMARINE, Coordinate.of( Row.of( 'B' ), Column.of( 6 ) ), Direction.DOWN );
        expectedPlayer1StagingGrid.addShip( Ship.SUBMARINE, Coordinate.of( Row.of( 'B' ), Column.of( 6 ) ), Direction.DOWN );
        stagingGame.addShip( player2Id, Ship.CRUISER, Coordinate.of( Row.of( 'H' ), Column.of( 4 ) ), Direction.RIGHT );
        expectedPlayer2StagingGrid.addShip( Ship.CRUISER, Coordinate.of( Row.of( 'H' ), Column.of( 4 ) ), Direction.RIGHT );

        assertThat( stagingGame.stagingGrid( player1Id ) )
            .isEqualTo( expectedPlayer1StagingGrid );
        assertThat( stagingGame.stagingGrid( player2Id ) )
            .isEqualTo( expectedPlayer2StagingGrid );
    }

    @Test
    public void cannotBuildGameUnlessTwoPlayersRegistered() {
        PlayerId player1Id = PlayerId.of( 987342789234L );
        StagingGame stagingGame = new StagingGame();
        stagingGame.addPlayer( player1Id );

        assertThatThrownBy( stagingGame::buildGame )
            .isInstanceOf( TooFewPlayersException.class );
    }

    @Test
    public void cannotBuildGameUnlessBothPlayersHavePlacedAllShips() {
        PlayerId player1Id = PlayerId.of( 198234789423L );
        PlayerId player2Id = PlayerId.of( 6874329423L );
        StagingGame stagingGame = new StagingGame();
        stagingGame.addPlayer( player1Id );
        stagingGame.addPlayer( player2Id );

        // Player 1 places all ships
        stagingGame.addShip( player1Id, Ship.CARRIER, Coordinate.of( Row.of( 'A' ), Column.of( 1 ) ), Direction.RIGHT );
        stagingGame.addShip( player1Id, Ship.BATTLESHIP, Coordinate.of( Row.of( 'B' ), Column.of( 1 ) ), Direction.RIGHT );
        stagingGame.addShip( player1Id, Ship.CRUISER, Coordinate.of( Row.of( 'C' ), Column.of( 1 ) ), Direction.RIGHT );
        stagingGame.addShip( player1Id, Ship.SUBMARINE, Coordinate.of( Row.of( 'D' ), Column.of( 1 ) ), Direction.RIGHT );
        stagingGame.addShip( player1Id, Ship.DESTROYER, Coordinate.of( Row.of( 'E' ), Column.of( 1 ) ), Direction.RIGHT );

        // Player 2 places all but the Destroyer
        stagingGame.addShip( player2Id, Ship.CARRIER, Coordinate.of( Row.of( 'E' ), Column.of( 5 ) ), Direction.DOWN );
        stagingGame.addShip( player2Id, Ship.BATTLESHIP, Coordinate.of( Row.of( 'E' ), Column.of( 6 ) ), Direction.DOWN );
        stagingGame.addShip( player2Id, Ship.CRUISER, Coordinate.of( Row.of( 'E' ), Column.of( 7 ) ), Direction.DOWN );
        stagingGame.addShip( player2Id, Ship.SUBMARINE, Coordinate.of( Row.of( 'E' ), Column.of( 8 ) ), Direction.DOWN );

        assertThatThrownBy( stagingGame::buildGame )
            .isInstanceOf( GameNotReadyException.class );
    }
}
