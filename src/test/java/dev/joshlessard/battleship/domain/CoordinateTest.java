package dev.joshlessard.battleship.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CoordinateTest {

    @Test
    public void coordinatesNotInMaxRowHasDownCoordinateInNextRow() {
        Coordinate coordinateA5 = Coordinate.of( Row.of( 'A' ), Column.of( 5 ) );
        assertThat( coordinateA5.down() )
            .contains( Coordinate.of( Row.of( 'B' ), Column.of( 5 ) ) );

        Coordinate coordinateG7 = Coordinate.of( Row.of( 'G' ), Column.of( 7 ) );
        assertThat( coordinateG7.down() )
            .contains( Coordinate.of( Row.of( 'H' ), Column.of( 7 ) ) );

        Coordinate coordinateI10 = Coordinate.of( Row.of( 'I' ), Column.of( 10 ) );
        assertThat( coordinateI10.down() )
            .contains( Coordinate.of( Row.of( 'J' ), Column.of( 10 ) ) );
    }

    @Test
    public void coordinateInMaxRowHasNoDownCoordinate() {
        Coordinate coordinateJ8 = Coordinate.of( Row.of( 'J' ), Column.of( 8 ) );
        assertThat( coordinateJ8.down() )
            .isEmpty();
    }

    @Test
    public void coordinateNotInMaxColumnHasRightCoordinateInNextColumn() {
        Coordinate coordinateB2 = Coordinate.of( Row.of( 'B' ), Column.of( 2 ) );
        assertThat( coordinateB2.right() )
            .contains( Coordinate.of( Row.of( 'B' ), Column.of( 3 ) ) );

        Coordinate coordinateF7 = Coordinate.of( Row.of( 'F' ), Column.of( 7 ) );
        assertThat( coordinateF7.right() )
            .contains( Coordinate.of( Row.of( 'F' ), Column.of( 8 ) ) );

        Coordinate coordinateJ9 = Coordinate.of( Row.of( 'J' ), Column.of( 9 ) );
        assertThat( coordinateJ9.right() )
            .contains( Coordinate.of( Row.of( 'J' ), Column.of( 10 ) ) );
    }

    @Test
    public void coordinateInMaxColumnHasNoRightCoordinate() {
        Coordinate coordinateH10 = Coordinate.of( Row.of( 'H' ), Column.of( 10 ) );
        assertThat( coordinateH10.right() )
            .isEmpty();
    }
}
