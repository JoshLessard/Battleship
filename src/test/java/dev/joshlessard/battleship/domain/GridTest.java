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

    // todo: more overlap cases
    // todo: test ships can be beside each other
    // todo: can't add same ship more than once
    // todo: must have all ships exactly once
}
