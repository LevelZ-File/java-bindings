package me.gamercoder215.calcgames.levelz.coord;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestCoordinate3D {

    @Test
    @DisplayName("Test Coordinate3D#fromString")
    public void testFromString() {
        Assertions.assertEquals(new Coordinate3D(0, 0, 0), Coordinate3D.fromString("[0,0,0]"));
        Assertions.assertEquals(new Coordinate3D(0, 0, 0), Coordinate3D.fromString("[0, 0, 0]"));
        Assertions.assertEquals(new Coordinate3D(0, 0, 0), Coordinate3D.fromString("[0,  0,  0]"));

        Assertions.assertEquals(Coordinate3D.fromString("[0,0,0]"), Coordinate3D.fromString("0,0,0"));
        Assertions.assertEquals(Coordinate3D.fromString("[0,0,0]"), Coordinate3D.fromString("0, 0, 0"));

        Assertions.assertEquals(new Coordinate3D(3, 4, 2), Coordinate3D.fromString("[3,4,2]"));
        Assertions.assertEquals(new Coordinate3D(-3, 4, -1), Coordinate3D.fromString("[-3, 4, -1]"));
        Assertions.assertEquals(new Coordinate3D(7, -14, 17), Coordinate3D.fromString("[7, -14, 17]"));

        Assertions.assertEquals(new Coordinate3D(2.5, -1.25, 12.5), Coordinate3D.fromString("[2.5, -1.25, 12.5]"));
        Assertions.assertEquals(new Coordinate3D(-4.5, 7.75, -11), Coordinate3D.fromString("[-4.5, 7.75, -11]"));
        Assertions.assertEquals(new Coordinate3D(0.5, 1, -0.5), Coordinate3D.fromString("[0.5, 1, -0.5]"));
        Assertions.assertEquals(new Coordinate3D(0.5, -1, -0.5), Coordinate3D.fromString("[0.5, -1.0, -0.5]"));

        Coordinate3D a = new Coordinate3D(0, 0, 0);
        Assertions.assertEquals(a.toString(), "[0, 0, 0]");
        Assertions.assertEquals(a, Coordinate3D.fromString(a.toString()));

        Coordinate3D b = new Coordinate3D(3, 4, -2.5);
        Assertions.assertEquals(b.toString(), "[3, 4, -2.5]");
        Assertions.assertEquals(b, Coordinate3D.fromString(b.toString()));

        Coordinate3D c = new Coordinate3D(2.5, -1.25, 12.5);
        Assertions.assertEquals(c.toString(), "[2.5, -1.25, 12.5]");
        Assertions.assertEquals(c, Coordinate3D.fromString(c.toString()));

        Coordinate3D d = new Coordinate3D(-11, -45.75, 88);
        Assertions.assertEquals(d.toString(), "[-11, -45.75, 88]");
        Assertions.assertEquals(d, Coordinate3D.fromString(d.toString()));
    }

}
