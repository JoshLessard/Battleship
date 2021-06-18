package dev.joshlessard.battleship.domain;

import java.util.Objects;
import java.util.Optional;

class Column {

    private static final int MINIMUM_COLUMN_INDEX = 1;
    private static final int MAXIMUM_COLUMN_INDEX = 10;

    private final int index;

    static Column of( int index ) {
        return new Column( index );
    }

    private Column( int index ) {
        this.index = validate( index );
    }

    private int validate( int index ) {
        if ( index < MINIMUM_COLUMN_INDEX || index > MAXIMUM_COLUMN_INDEX ) {
            throw new IllegalArgumentException();
        }
        return index;
    }

    int index() {
        return index;
    }

    Optional<Column> nextColumn() {
        return index == MAXIMUM_COLUMN_INDEX
            ? Optional.empty()
            : Optional.of( Column.of( index + 1 ) );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Column)) return false;
        Column column = (Column) o;
        return index == column.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
