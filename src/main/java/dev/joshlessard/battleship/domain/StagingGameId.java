package dev.joshlessard.battleship.domain;

import java.util.Objects;

public class StagingGameId {

    private final long id;

    public static StagingGameId of( long id ) {
        return new StagingGameId( id );
    }

    private StagingGameId( long id ) {
        this.id = validateId( id );
    }

    private long validateId( long id ) {
        if ( id < 1L ) {
            throw new IllegalArgumentException();
        }
        return id;
    }

    public long asLong() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StagingGameId)) return false;
        StagingGameId that = (StagingGameId) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
