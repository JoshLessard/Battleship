package dev.joshlessard.battleship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.joshlessard.battleship.domain.DefaultStagingGameService;
import dev.joshlessard.battleship.domain.StagingGameRepository;
import dev.joshlessard.battleship.domain.StagingGameService;

@SpringBootApplication
public class BattleshipApplication {

    public static void main( String[] args ) {
        SpringApplication.run( BattleshipApplication.class, args );
    }

    @Bean
    public StagingGameService stagingGameService( StagingGameRepository stagingGameRepository ) {
        return new DefaultStagingGameService( stagingGameRepository );
    }
}
