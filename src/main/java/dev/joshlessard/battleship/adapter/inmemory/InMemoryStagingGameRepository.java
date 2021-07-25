package dev.joshlessard.battleship.adapter.inmemory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import dev.joshlessard.battleship.domain.StagingGame;
import dev.joshlessard.battleship.domain.StagingGameId;
import dev.joshlessard.battleship.domain.StagingGameRepository;

@Repository
public class InMemoryStagingGameRepository implements StagingGameRepository {

    private final AtomicLong idGenerator = new AtomicLong( 0L );
    private final Map<StagingGameId, StagingGame> stagingGamesById = new HashMap<>();

    @Override
    public void save( StagingGame stagingGame ) {
        if ( stagingGame.id() == null ) {
            stagingGame.setId( StagingGameId.of( idGenerator.incrementAndGet() ) );
        }
        stagingGamesById.put( stagingGame.id(), stagingGame );
    }
}
