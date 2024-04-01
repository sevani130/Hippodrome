import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;


public class HorseTest {

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 999.999, 0.0})
    public void move(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("qwerty", 31, 283);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            horse.move();
            assertEquals(283 + 31 * random, horse.getDistance());
        }
    }

    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Horse", 1, 1);
        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);
        assertEquals("Horse", nameValue);
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("Horse", 10, 20);
        assertEquals(10, horse.getSpeed());
    }


    @Test
    public void getDistance() {
        Horse horse = new Horse("Horse", 10, 20);
        assertEquals(20, horse.getDistance());
    }

    @Test
    public void getDistanceTwoParameters() {
        Horse horse = new Horse("Horse", 10);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void testMove() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Horse", 10, 20);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            assertEquals(25, horse.getDistance());
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}