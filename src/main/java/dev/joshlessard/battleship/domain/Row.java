package dev.joshlessard.battleship.domain;

import java.util.Objects;
import java.util.Optional;

class Row {

    private static final char MINIMUM_ROW_NAME = 'A';
    private static final char MAXIMUM_ROW_NAME = 'J';

    private final char name;

    static Row of( char name ) {
        return new Row( name );
    }

    private Row( char name ) {
        this.name = validate( name );
    }

    private char validate( char name ) {
        name = Character.toUpperCase( name );
        if ( name < MINIMUM_ROW_NAME || name > MAXIMUM_ROW_NAME ) {
            throw new IllegalArgumentException();
        }
        return name;
    }

    char name() {
        return name;
    }

    Optional<Row> nextRow() {
        if ( name == MAXIMUM_ROW_NAME ) {
            return Optional.empty();
        } else {
            char nextRowName = (char) (name + 1);
            return Optional.of( Row.of( nextRowName ) );
        }
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
