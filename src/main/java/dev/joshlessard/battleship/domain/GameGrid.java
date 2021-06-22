package dev.joshlessard.battleship.domain;

import java.util.*;

import static java.util.Collections.emptySet;

class GameGrid {

    private final Map<Ship, Set<Coordinate>> coordinatesByShip;
    private final Map<Ship, Set<Coordinate>> hitsByShip;
    private final Map<Coordinate, Ship> shipsByCoordinate;

    GameGrid( Map<Ship, Set<Coordinate>> coordinatesByShip ) {
        this.coordinatesByShip = new EnumMap<>( coordinatesByShip );
        this.hitsByShip = new EnumMap<>( Ship.class );
        this.shipsByCoordinate = mapShipsByCoordinate( coordinatesByShip );
    }

    private Map<Coordinate, Ship> mapShipsByCoordinate( Map<Ship, Set<Coordinate>> coordinatesByShip ) {
        Map<Coordinate, Ship> shipsByCoordinate = new HashMap<>();
        for ( Ship ship : coordinatesByShip.keySet() ) {
            for ( Coordinate coordinate : coordinatesByShip.get( ship ) ) {
                shipsByCoordinate.put( coordinate, ship );
            }
        }
        return shipsByCoordinate;
    }

    ShotResult shoot( Coordinate coordinate ) {
        return shipAt( coordinate )
            .map( ship -> hit( ship, coordinate ) )
            .orElse( ShotResult.miss() );
    }

    private Optional<Ship> shipAt( Coordinate coordinate ) {
        return Optional.ofNullable( shipsByCoordinate.get( coordinate ) );
    }

    private ShotResult hit( Ship ship, Coordinate coordinate ) {
        recordHit( ship, coordinate );
        return areAllCoordinatesHit( ship )
            ? ShotResult.sunk( ship )
            : ShotResult.hit( ship );
    }

    private void recordHit( Ship ship, Coordinate coordinate ) {
        hitsByShip.computeIfAbsent( ship, s -> new HashSet<>() )
            .add( coordinate );
    }

    private boolean areAllCoordinatesHit( Ship ship ) {
        Set<Coordinate> hits = hitsByShip.getOrDefault( ship, emptySet() );
        Set<Coordinate> shipCoordinates = coordinatesByShip.getOrDefault( ship, emptySet() );
        return hits.containsAll( shipCoordinates );
    }
}
