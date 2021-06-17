package dev.joshlessard.battleship.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ColumnTest {

    @Test
    public void columnIndexCannotBLessThan1() {
        assertThatIllegalArgumentException()
            .isThrownBy( () -> Column.of( 0 ) );
    }

    @Test
    public void columnIndexCannotBeGreaterThan10() {
        assertThatIllegalArgumentException()
            .isThrownBy( () -> Column.of( 11 ) );
    }

    @Test
    public void columnIndexCanBeAnythingBetween1And10() {
        for ( int index = 1; index <= 10; ++index ) {
            Column column = Column.of( index );
            assertThat( column.index() )
                .isEqualTo( index );
        }
    }
}
