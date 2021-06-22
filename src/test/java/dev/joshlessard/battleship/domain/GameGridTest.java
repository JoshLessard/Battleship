package dev.joshlessard.battleship.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class GameGridTest {

    @Test
    public void coordinatesOccupiedByShipsAreHitsAndOthersAreMissesWhenShotAt() {
        StagingGrid stagingGrid = new StagingGrid();
        stagingGrid.addShip( Ship.CARRIER, Coordinate.of( Row.of( 'C' ), Column.of( 4 ) ), Direction.DOWN ); // C4/D4/E4/F4/G4
        stagingGrid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'A' ), Column.of( 3 ) ), Direction.DOWN ); // A3/B3/C3/D3
        stagingGrid.addShip( Ship.CRUISER, Coordinate.of( Row.of( 'H' ), Column.of( 8 ) ), Direction.RIGHT ); // H8/H9/H10
        stagingGrid.addShip( Ship.SUBMARINE, Coordinate.of( Row.of( 'I' ), Column.of( 1 ) ), Direction.RIGHT ); // I1/I2/I3
        stagingGrid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'H' ), Column.of( 5 ) ), Direction.DOWN ); // H5/I5

        GameGrid gameGrid = stagingGrid.buildGameGrid();

        // Hits
        assertThat( gameGrid.shoot( Coordinate.of( Row.of( 'C' ), Column.of( 4 ) ) ) )
            .isEqualTo( ShotResult.hit( Ship.CARRIER ) );
        assertThat( gameGrid.shoot( Coordinate.of( Row.of( 'H' ), Column.of( 9 ) ) ) )
            .isEqualTo( ShotResult.hit( Ship.CRUISER ) );
        assertThat( gameGrid.shoot( Coordinate.of( Row.of( 'I' ), Column.of( 3 ) ) ) )
            .isEqualTo( ShotResult.hit( Ship.SUBMARINE ) );

        // Misses
        assertThat( gameGrid.shoot( Coordinate.of( Row.of( 'E' ), Column.of( 2 ) ) ) )
            .isEqualTo( ShotResult.miss() );
        assertThat( gameGrid.shoot( Coordinate.of( Row.of( 'J' ), Column.of( 8 ) ) ) )
            .isEqualTo( ShotResult.miss() );
    }

    @Test
    public void hittingAllCoordinatesOccupiedByAShipSinksThatShip() {
        StagingGrid stagingGrid = new StagingGrid();
        stagingGrid.addShip( Ship.CARRIER, Coordinate.of( Row.of( 'F' ), Column.of( 6 ) ), Direction.DOWN ); // F6/G6/H6/I6/J6
        stagingGrid.addShip( Ship.BATTLESHIP, Coordinate.of( Row.of( 'B' ), Column.of( 4 ) ), Direction.DOWN ); // B4/C4/D4/E4
        stagingGrid.addShip( Ship.CRUISER, Coordinate.of( Row.of( 'A' ), Column.of( 3 ) ), Direction.RIGHT ); // A3/A4/A5
        stagingGrid.addShip( Ship.SUBMARINE, Coordinate.of( Row.of( 'D' ), Column.of( 10 ) ), Direction.DOWN ); // D10/E10/F10
        stagingGrid.addShip( Ship.DESTROYER, Coordinate.of( Row.of( 'H' ), Column.of( 4 ) ), Direction.RIGHT ); // H4/H5

        GameGrid gameGrid = stagingGrid.buildGameGrid();

        assertThat( gameGrid.shoot( Coordinate.of( Row.of( 'I' ), Column.of( 6 ) ) ) )
            .isEqualTo( ShotResult.hit( Ship.CARRIER ) );
        assertThat( gameGrid.shoot( Coordinate.of( Row.of( 'G' ), Column.of( 6 ) ) ) )
            .isEqualTo( ShotResult.hit( Ship.CARRIER ) );
        assertThat( gameGrid.shoot( Coordinate.of( Row.of( 'J' ), Column.of( 6 ) ) ) )
            .isEqualTo( ShotResult.hit( Ship.CARRIER ) );
        assertThat( gameGrid.shoot( Coordinate.of( Row.of( 'F' ), Column.of( 6 ) ) ) )
            .isEqualTo( ShotResult.hit( Ship.CARRIER ) );
        assertThat( gameGrid.shoot( Coordinate.of( Row.of( 'H' ), Column.of( 6 ) ) ) )
            .isEqualTo( ShotResult.sunk( Ship.CARRIER ) );
    }

    // todo: exceptions could provide more detail (e.g., colliding ships and coordinates they occupy)
}
