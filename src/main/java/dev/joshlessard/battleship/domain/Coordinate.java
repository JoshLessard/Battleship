package dev.joshlessard.battleship.domain;

import java.util.Objects;
import java.util.Optional;

class Coordinate {

    private final Row row;
    private final Column column;

    private Coordinate( Row row, Column column ) {
        this.row = row;
        this.column = column;
    }

    static Coordinate of( Row row, Column column ) {
        return new Coordinate( row, column );
    }

    Optional<Coordinate> down() {
        return row.nextRow()
            .map( r -> Coordinate.of( r, column ) );
    }

    Optional<Coordinate> right() {
        return column.nextColumn()
            .map( c -> Coordinate.of( row, c ) );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(row, that.row) &&
                Objects.equals(column, that.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
