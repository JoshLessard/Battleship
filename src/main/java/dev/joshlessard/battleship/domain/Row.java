package dev.joshlessard.battleship.domain;

import java.util.Objects;

class Row {

    private final char name;

    static Row of( char name ) {
        return new Row( name );
    }

    private Row( char name ) {
        this.name = validate( name );
    }

    private char validate( char name ) {
        name = Character.toUpperCase( name );
        if ( name < 'A' || name > 'J' ) {
            throw new IllegalArgumentException();
        }
        return name;
    }

    char name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Row)) return false;
        Row row = (Row) o;
        return name == row.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
