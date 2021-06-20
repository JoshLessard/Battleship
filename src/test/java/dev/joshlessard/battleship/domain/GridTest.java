package dev.joshlessard.battleship.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GridTest {

    @Test
    public void shipCannotExtendBeyondMaxColumnOfGrid() {
        Grid grid = new Grid();

        assertThatThrownBy( () -> grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'H' ), Column.of( 8 ) ), Direction.RIGHT ) )
            .isInstanceOf( ShipOffGridException.class );
    }

    @Test
    public void shipCannotExtendBeyondBottomSideOfGrid() {
        Grid grid = new Grid();

        assertThatThrownBy( () -> grid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'J' ), Column.of( 1 ) ), Direction.DOWN ) )
            .isInstanceOf( ShipOffGridException.class );
    }

    @Test
    public void shipCanEndOnMaxColumnOfGrid() {
        Grid grid = new Grid();

        // D9-D10
        grid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'D' ), Column.of( 9 ) ), Direction.RIGHT );
    }

    @Test
    public void shipCanEndOnMaxRowOfGrid() {
        Grid grid = new Grid();

        // H7-J7
        grid.addShip( Ship.SUBMARINE, Coordinate.of( Row.of( 'H' ), Column.of( 7 ) ), Direction.DOWN );
    }

    @Test
    public void shipCannotOverlapAnotherShipHorizontally() {
        Grid grid = new Grid();
        grid.addShip( Ship.CARRIER, Coordinate.of( Row.of( 'D' ), Column.of( 4 ) ), Direction.RIGHT ); // D4-D8

        assertThatThrownBy( () -> grid.addShip( Ship.CRUISER, Coordinate.of( Row.of( 'D' ), Column.of( 8 ) ), Direction.RIGHT ) ) // D8-D10
            .isInstanceOf( ShipCollisionException.class );
    }

    @Test
    public void shipCannotOverlapAnotherShipVertically() {
        Grid grid = new Grid();
        grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'E' ), Column.of( 3 ) ), Direction.DOWN ); // E3-H3

        assertThatThrownBy( () -> grid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'G' ), Column.of( 3 ) ), Direction.DOWN ) ) // G3-H3
            .isInstanceOf( ShipCollisionException.class );
    }

    @Test
    public void shipCannotOverlapAnotherShipCrosswise() {
        Grid grid = new Grid();
        grid.addShip( Ship.CARRIER, Coordinate.of( Row.of( 'B' ), Column.of( 4 ) ), Direction.RIGHT ); // B4-B8

        assertThatThrownBy( () -> grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'A' ), Column.of( 6 ) ), Direction.DOWN ) ) // A6-D6
            .isInstanceOf( ShipCollisionException.class );
    }

    @Test
    public void shipsMayBeAdjacentHorizontally() {
        Grid grid = new Grid();
        grid.addShip( Ship.CRUISER, Coordinate.of( Row.of( 'C' ), Column.of( 4 ) ), Direction.RIGHT ); // C4-C6
        grid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'C' ), Column.of( 7 ) ), Direction.RIGHT ); // C7-C8
    }

    @Test
    public void shipsMayBeAdjacentVertically() {
        Grid grid = new Grid();
        grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'D' ), Column.of( 3 ) ), Direction.DOWN ); // D3-G3
        grid.addShip( Ship.SUBMARINE, Coordinate.of( Row.of( 'H' ), Column.of( 3 ) ), Direction.DOWN ); // H3-J3
    }

    @Test
    public void shipsMayBeAdjacentCrosswise() {
        Grid grid = new Grid();
        grid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'E' ), Column.of( 4 ) ), Direction.DOWN ); // E4-F4
        grid.addShip( Ship.CRUISER, Coordinate.of( Row.of( 'G' ), Column.of( 3 ) ), Direction.RIGHT ); // G3-G5
    }

    @Test
    public void cannotAddSameShipTwice() {
        Grid grid = new Grid();
        grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'D' ), Column.of( 1 ) ), Direction.DOWN ); // D1-G1

        assertThatThrownBy( () -> grid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'I' ), Column.of( 5 ) ), Direction.RIGHT ) ) // I5-I8
            .isInstanceOf( DuplicateShipException.class );
    }

    // todo: must have all ships exactly once
}
