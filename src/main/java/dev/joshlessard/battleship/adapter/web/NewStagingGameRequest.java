package dev.joshlessard.battleship.adapter.web;

class NewStagingGameRequest {

    private long playerId;

    NewStagingGameRequest( long playerId ) {
        this.playerId = playerId;
    }

    public long getPlayerId() {
        return playerId;
    }
}
