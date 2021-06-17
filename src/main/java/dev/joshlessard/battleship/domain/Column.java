package dev.joshlessard.battleship.domain;

class Column {

    private final int index;

    static Column of( int index ) {
        return new Column( index );
    }

    private Column( int index ) {
        this.index = validate( index );
    }

    private int validate( int index ) {
        if ( index < 1 || index > 10 ) {
            throw new IllegalArgumentException();
        }
        return index;
    }

    int index() {
        return index;
    }
}
