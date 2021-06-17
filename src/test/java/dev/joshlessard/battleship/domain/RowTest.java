package dev.joshlessard.battleship.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class RowTest {

    @Test
    public void rowNameCannotBeLessThanA() {
        char beforeA = 'A' - 1;
        assertThatIllegalArgumentException()
            .isThrownBy( () -> Row.of( beforeA ) );
    }

    @Test
    public void rowNameCannotBeGreaterThanJ() {
        char afterJ = 'J' + 1;
        assertThatIllegalArgumentException()
            .isThrownBy( () -> Row.of( afterJ ) );
    }

    @Test
    public void rowCanBeAnythingBetweenAandJ() {
        for ( char rowName = 'A'; rowName <= 'J'; ++rowName ) {
            Row row = Row.of( rowName );
            assertThat( row.name() )
                .isEqualTo( rowName );
        }
    }

    @Test
    public void rowNameAssumedToBeUppercase() {
        for ( char rowName = 'a'; rowName <= 'j'; ++rowName ) {
            Row row = Row.of( rowName );
            assertThat( row.name() )
                .isEqualTo( Character.toUpperCase( rowName ) );
        }
    }
}
