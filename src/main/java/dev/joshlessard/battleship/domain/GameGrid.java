package dev.joshlessard.battleship.domain;

import java.util.*;

class GameGrid {

    private final Map<Ship, Set<Coordinate>> coordinatesByShip;
    private final Map<Coordinate, Ship> shipsByCoordinate;

    GameGrid( Map<Ship, Set<Coordinate>> coordinatesByShip ) {
        this.coordinatesByShip = new EnumMap<>( coordinatesByShip );
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
        return Optional.ofNullable( shipsByCoordinate.get( coordinate ) )
            .map( ShotResult::hit )
            .orElse( ShotResult.miss() );
    }
}
