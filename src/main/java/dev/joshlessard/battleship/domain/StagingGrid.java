package dev.joshlessard.battleship.domain;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

class StagingGrid {

    private final Map<Ship, Set<Coordinate>> shipCoordinates = new EnumMap<>( Ship.class );
    private final Set<Coordinate> occupiedCoordinates = new HashSet<>();

    void addShip( Ship ship, Coordinate topLeft, Direction direction ) {
        ensureShipNotAlreadyOnGrid( ship );
        Set<Coordinate> coordinates = coordinatesToBeOccupied( topLeft, direction, ship.length() );
        ensureNoneAlreadyOccupied( coordinates );
        occupyCoordinates( ship, coordinates );
    }

    private void ensureShipNotAlreadyOnGrid( Ship ship ) {
        if ( shipCoordinates.containsKey( ship ) ) {
            throw new DuplicateShipException();
        }
    }

    private Set<Coordinate> coordinatesToBeOccupied( Coordinate topLeft, Direction direction, int length ) {
        Coordinate currentCoordinate = topLeft;
        Set<Coordinate> coordinates = new HashSet<>( length );
        coordinates.add( currentCoordinate );
        while( coordinates.size() < length ) {
            currentCoordinate = direction.next( currentCoordinate )
                .orElseThrow( ShipOffGridException::new );
            coordinates.add( currentCoordinate );
        }
        return coordinates;
    }

    private void ensureNoneAlreadyOccupied( Set<Coordinate> coordinates ) {
        if ( coordinates.stream().anyMatch( occupiedCoordinates::contains ) ) {
            throw new ShipCollisionException();
        }
    }

    private void occupyCoordinates( Ship ship, Set<Coordinate> coordinates ) {
        shipCoordinates.put( ship, coordinates );
        occupiedCoordinates.addAll( coordinates );
    }

    void removeShip( Ship ship ) {
        ensureShipOnGrid( ship );
        emptyCoordinatesOccupiedBy( ship );
    }

    private void ensureShipOnGrid( Ship ship ) {
        if ( ! shipCoordinates.containsKey( ship ) ) {
            throw new ShipNotOnGridException();
        }
    }

    private void emptyCoordinatesOccupiedBy( Ship ship ) {
        Set<Coordinate> coordinates = shipCoordinates.remove( ship );
        occupiedCoordinates.removeAll( coordinates );
    }

    GameGrid buildGameGrid() {
        ensureAllShipsOnGrid();
        return new GameGrid();
    }

    private void ensureAllShipsOnGrid() {
        for ( Ship ship : Ship.values() ) {
            if ( ! shipCoordinates.containsKey( ship ) ) {
                throw new MissingShipException();
            }
        }
    }
}
