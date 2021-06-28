package dev.joshlessard.battleship.domain;

import java.util.Objects;

class PlayerId {

    private final long id;

    static PlayerId of( long id ) {
        return new PlayerId( id );
    }

    private PlayerId( long id ) {
        this.id = validateId( id );
    }

    private long validateId( long id ) {
        if ( id < 1L ) {
            throw new IllegalArgumentException();
        }
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerId)) return false;
        PlayerId playerId = (PlayerId) o;
        return id == playerId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
