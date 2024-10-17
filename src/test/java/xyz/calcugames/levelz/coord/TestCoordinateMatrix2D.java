package xyz.calcugames.levelz.coord;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestCoordinateMatrix2D {

    @Test
    @DisplayName("Test CoordinateMatrix2D#getCoordinates")
    public void testGetCoordinates() {
        CoordinateMatrix2D m1 = new CoordinateMatrix2D(0, 4, 0, 4, new Coordinate2D(0, 0));
        Assertions.assertEquals(25, m1.getCoordinates().size());
        Assertions.assertTrue(m1.getCoordinates().contains(new Coordinate2D(0, 0)));

        CoordinateMatrix2D m2 = new CoordinateMatrix2D(-4, 4, -4, 4, new Coordinate2D(1, 1));
        Assertions.assertEquals(81, m2.getCoordinates().size());
        Assertions.assertTrue(m2.getCoordinates().contains(new Coordinate2D(1, 1)));

        CoordinateMatrix2D m3 = new CoordinateMatrix2D(4, 4, new Coordinate2D(0, 0));
        Assertions.assertEquals(m1, m3);
    }

}
