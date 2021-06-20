package dev.joshlessard.battleship.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StagingGridTest {

    @Test
    public void shipCannotExtendBeyondMaxColumnOfGrid() {
        StagingGrid grid = new StagingGrid();

        assertThatThrownBy( () -> grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'H' ), Column.of( 8 ) ), Direction.RIGHT ) ) // H8/H9/H10/H11
            .isInstanceOf( ShipOffGridException.class );
    }

    @Test
    public void shipCannotExtendBeyondBottomSideOfGrid() {
        StagingGrid grid = new StagingGrid();

        assertThatThrownBy( () -> grid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'J' ), Column.of( 1 ) ), Direction.DOWN ) ) // J1/K1
            .isInstanceOf( ShipOffGridException.class );
    }

    @Test
    public void shipCanEndOnMaxColumnOfGrid() {
        StagingGrid grid = new StagingGrid();

        grid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'D' ), Column.of( 9 ) ), Direction.RIGHT ); // D9/D10
    }

    @Test
    public void shipCanEndOnMaxRowOfGrid() {
        StagingGrid grid = new StagingGrid();

        grid.addShip( Ship.SUBMARINE, Coordinate.of( Row.of( 'H' ), Column.of( 7 ) ), Direction.DOWN ); // H7/I7/J7
    }

    @Test
    public void shipCannotOverlapAnotherShipHorizontally() {
        StagingGrid grid = new StagingGrid();
        grid.addShip( Ship.CARRIER, Coordinate.of( Row.of( 'D' ), Column.of( 4 ) ), Direction.RIGHT ); // D4-D8

        assertThatThrownBy( () -> grid.addShip( Ship.CRUISER, Coordinate.of( Row.of( 'D' ), Column.of( 8 ) ), Direction.RIGHT ) ) // D8/D9/D10
            .isInstanceOf( ShipCollisionException.class );
    }

    @Test
    public void shipCannotOverlapAnotherShipVertically() {
        StagingGrid grid = new StagingGrid();
        grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'E' ), Column.of( 3 ) ), Direction.DOWN ); // E3-H3

        assertThatThrownBy( () -> grid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'G' ), Column.of( 3 ) ), Direction.DOWN ) ) // G3/H3
            .isInstanceOf( ShipCollisionException.class );
    }

    @Test
    public void shipCannotOverlapAnotherShipCrosswise() {
        StagingGrid grid = new StagingGrid();
        grid.addShip( Ship.CARRIER, Coordinate.of( Row.of( 'B' ), Column.of( 4 ) ), Direction.RIGHT ); // B4/B5/B6/B7/B8

        assertThatThrownBy( () -> grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'A' ), Column.of( 6 ) ), Direction.DOWN ) ) // A6/B6/C6/D6
            .isInstanceOf( ShipCollisionException.class );
    }

    @Test
    public void shipsMayBeAdjacentHorizontally() {
        StagingGrid grid = new StagingGrid();
        grid.addShip( Ship.CRUISER, Coordinate.of( Row.of( 'C' ), Column.of( 4 ) ), Direction.RIGHT ); // C4/C5/C6
        grid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'C' ), Column.of( 7 ) ), Direction.RIGHT ); // C7/C8
    }

    @Test
    public void shipsMayBeAdjacentVertically() {
        StagingGrid grid = new StagingGrid();
        grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'D' ), Column.of( 3 ) ), Direction.DOWN ); // D3/E3/F3/G3
        grid.addShip( Ship.SUBMARINE, Coordinate.of( Row.of( 'H' ), Column.of( 3 ) ), Direction.DOWN ); // H3/I3/J3
    }

    @Test
    public void shipsMayBeAdjacentCrosswise() {
        StagingGrid grid = new StagingGrid();
        grid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'E' ), Column.of( 4 ) ), Direction.DOWN ); // E4/F4
        grid.addShip( Ship.CRUISER, Coordinate.of( Row.of( 'G' ), Column.of( 3 ) ), Direction.RIGHT ); // G3/G4/G5
    }

    @Test
    public void cannotAddSameShipTwice() {
        StagingGrid grid = new StagingGrid();
        grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'D' ), Column.of( 1 ) ), Direction.DOWN ); // D1/E1/F1/G1

        assertThatThrownBy( () -> grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'I' ), Column.of( 5 ) ), Direction.RIGHT ) ) // I5/I6/I7/I8
            .isInstanceOf( DuplicateShipException.class );
    }

    @Test
    public void cannotRemoveShipNotOnGrid() {
        StagingGrid grid = new StagingGrid();
        grid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'H' ), Column.of( 3 ) ), Direction.RIGHT ); // H3/H4

        assertThatThrownBy( () -> grid.removeShip( Ship.CRUISER ) )
            .isInstanceOf( ShipNotOnGridException.class );
    }

    @Test
    public void removingShipAllowAnotherShipToOccupyItsCoordinates() {
        StagingGrid grid = new StagingGrid();
        grid.addShip( Ship.CARRIER, Coordinate.of( Row.of( 'C' ), Column.of( 5 ) ), Direction.DOWN ); // C5/D5/E5/F5/G5
        grid.removeShip( Ship.CARRIER );

        // All these ships occupy at least one coordinate formerly occupied by the Carrier
        grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'D' ), Column.of( 4 ) ), Direction.RIGHT ); // D4/D5/D6/D7
        grid.addShip( Ship.CRUISER, Coordinate.of( Row.of( 'C' ), Column.of( 5 ) ), Direction.RIGHT ); // C5/C6/C7
        grid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'F' ), Column.of( 5 ) ), Direction.DOWN ); // F5/G5
    }

    @Test
    public void cannotBuildGameGridWithoutAllShipsOnStagingGrid() {
        StagingGrid grid = new StagingGrid();
        grid.addShip( Ship.CARRIER, Coordinate.of( Row.of( 'A' ), Column.of( 1 ) ), Direction.RIGHT ); // A1/A2/A3/A4/A5
        grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'B' ), Column.of( 1 ) ), Direction.RIGHT ); // B1/B2/B3/B4
        grid.addShip( Ship.CRUISER, Coordinate.of( Row.of( 'C' ), Column.of( 1 ) ), Direction.RIGHT ); // C1/C2/C3
        grid.addShip( Ship.SUBMARINE, Coordinate.of( Row.of( 'D' ), Column.of( 1 ) ), Direction.RIGHT ); // D1/D2/D3

        assertThatThrownBy( grid::buildGameGrid )
            .isInstanceOf( MissingShipException.class );
    }

    @Test
    public void canBuildGameGridWithAllShipsOnStagingGrid() {
        START HERE
    }
}
