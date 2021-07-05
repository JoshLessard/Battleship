package dev.joshlessard.battleship.domain;

import java.util.Objects;

class ShotResult {

    private static final ShotResult MISS_RESULT = new ShotResult( Result.MISS, null );

    enum Result {
        HIT,
        SUNK,
        MISS;
    }

    private final Result result;
    private final Ship ship;

    static ShotResult hit( Ship ship ) {
        return new ShotResult( Result.HIT, ship );
    }

    static ShotResult sunk( Ship ship ) {
        return new ShotResult( Result.SUNK, ship );
    }

    static ShotResult miss() {
        return MISS_RESULT;
    }

    private ShotResult( Result result, Ship ship ) {
        this.result = result;
        this.ship = ship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShotResult)) return false;
        ShotResult that = (ShotResult) o;
        return result == that.result && ship == that.ship;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, ship);
    }

    @Override
    public String toString() {
        return "ShotResult{" +
            "result=" + result +
            ", ship=" + ship +
            '}';
    }
}
