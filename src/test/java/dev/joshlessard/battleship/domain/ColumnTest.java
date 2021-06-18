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

    @Test
    public void columnsOtherThanMaxColumnHaveNextColumn() {
        Column column1 = Column.of( 1 );
        assertThat( column1.nextColumn() )
            .contains( Column.of( 2 ) );

        Column column5 = Column.of( 5 );
        assertThat( column5.nextColumn() )
            .contains( Column.of( 6 ) );

        Column column9 = Column.of( 9 );
        assertThat( column9.nextColumn() )
            .contains( Column.of( 10 ) );
    }

    @Test
    public void maxColumnHasNoNextColumn() {
        Column maxColumn = Column.of( 10 );
        assertThat( maxColumn.nextColumn() )
            .isEmpty();
    }
}
