package dev.joshlessard.battleship.domain;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Grid {

    private final Map<Ship, Set<Coordinate>> shipCoordinates = new EnumMap<>( Ship.class );
    private final Set<Coordinate> occupiedCoordinates = new HashSet<>();

    void addShip( Ship ship, Coordinate topLeft, Direction direction ) {
        Set<Coordinate> coordinates = coordinatesToBeOccupied( topLeft, direction, ship.length() );
        ensureNoneAlreadyOccupied( coordinates );
        shipCoordinates.put( ship, coordinates );
        occupiedCoordinates.addAll( coordinates );
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
}
