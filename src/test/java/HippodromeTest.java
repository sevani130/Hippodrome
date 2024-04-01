import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {

    @Test
    public void nullHorsesException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void emptyHorsesException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void getHorses() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

        @Test
        public void getWinner() {
            Horse horse1 = new Horse("Horse1", 1, 2.999999);
            Horse horse2 = new Horse("Horse2", 1, 2);
            Horse horse3 = new Horse("Horse3", 1, 3);
            Horse horse4 = new Horse("Horse4", 1, 1);
            Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4));

            Horse expectedWinner = horse3;
            Horse actualWinner = hippodrome.getWinner();

            assertSame(expectedWinner, actualWinner);
            }
    }