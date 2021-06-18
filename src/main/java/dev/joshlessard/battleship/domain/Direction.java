package dev.joshlessard.battleship.domain;

import java.util.Optional;
import java.util.function.Function;

enum Direction {
    RIGHT( Coordinate::right ),
    DOWN( Coordinate::down );

    private final Function<Coordinate, Optional<Coordinate>> nextCoordinateFunction;

    Direction( Function<Coordinate, Optional<Coordinate>> nextCoordinateFunction ) {
        this.nextCoordinateFunction = nextCoordinateFunction;
    }

    Optional<Coordinate> next( Coordinate coordinate ) {
        return nextCoordinateFunction.apply( coordinate );
    }
}
