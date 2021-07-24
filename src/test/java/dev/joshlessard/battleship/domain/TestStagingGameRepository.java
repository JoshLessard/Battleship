package dev.joshlessard.battleship.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class TestStagingGameRepository implements StagingGameRepository {

    private AtomicLong idGenerator = new AtomicLong( 0L );
    private Map<StagingGameId, StagingGame> stagingGamesById = new HashMap<>();

    @Override
    public void save( StagingGame stagingGame ) {
        if ( stagingGame.id() == null ) {
            stagingGame.setId( StagingGameId.of( idGenerator.incrementAndGet() ) );
        }
        stagingGamesById.put( stagingGame.id(), stagingGame );
    }

    public Collection<StagingGame> all() {
        return stagingGamesById.values();
    }
}
