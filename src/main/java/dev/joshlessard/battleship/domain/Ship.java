package dev.joshlessard.battleship.domain;

enum Ship {
    CARRIER( 5 ),
    BATTLESHIP( 4 ),
    CRUISER( 3 ),
    SUBMARINE( 3 ),
    DESTROYER( 2 );

    private final int length;

    Ship( int length ) {
        this.length = length;
    }

    int length() {
        return length;
    }
}
