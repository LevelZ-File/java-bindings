package xyz.calcugames.levelz.coord;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestCoordinateMatrix3D {

    @Test
    @DisplayName("Test CoordinateMatrix3D#getCoordinates")
    public void testGetCoordinates() {
        CoordinateMatrix3D m1 = new CoordinateMatrix3D(0, 4, 0, 4, 0, 4, new Coordinate3D(0, 0, 0));
        Assertions.assertEquals(125, m1.getCoordinates().size());
        Assertions.assertTrue(m1.getCoordinates().contains(new Coordinate3D(0, 0, 0)));

        CoordinateMatrix3D m2 = new CoordinateMatrix3D(-4, 4, -4, 4, -4, 4, new Coordinate3D(1, 1, 1));
        Assertions.assertEquals(729, m2.getCoordinates().size());
        Assertions.assertTrue(m2.getCoordinates().contains(new Coordinate3D(1, 1, 1)));

        CoordinateMatrix3D m3 = new CoordinateMatrix3D(4, 4, 4, new Coordinate3D(0, 0, 0));
        Assertions.assertEquals(m1, m3);
    }

}
