package me.gamercoder215.calcgames.levelz.coord;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestCoordinate2D {

    @Test
    @DisplayName("Test Coordinate2D#fromString")
    public void testFromString() {
        Assertions.assertEquals(new Coordinate2D(0, 0), Coordinate2D.fromString("[0,0]"));
        Assertions.assertEquals(new Coordinate2D(0, 0), Coordinate2D.fromString("[0, 0]"));
        Assertions.assertEquals(new Coordinate2D(0, 0), Coordinate2D.fromString("[0,  0]"));

        Assertions.assertEquals(Coordinate2D.fromString("[0,0]"), Coordinate2D.fromString("0,0"));
        Assertions.assertEquals(Coordinate2D.fromString("[0,0]"), Coordinate2D.fromString("0, 0"));

        Assertions.assertEquals(new Coordinate2D(3, 4), Coordinate2D.fromString("[3,4]"));
        Assertions.assertEquals(new Coordinate2D(-3, 4), Coordinate2D.fromString("[-3, 4]"));
        Assertions.assertEquals(new Coordinate2D(7, -14), Coordinate2D.fromString("[7, -14]"));

        Assertions.assertEquals(new Coordinate2D(2.5, -1.25), Coordinate2D.fromString("[2.5, -1.25]"));
        Assertions.assertEquals(new Coordinate2D(-4.5, 7.75), Coordinate2D.fromString("[-4.5, 7.75]"));
        Assertions.assertEquals(new Coordinate2D(0.5, 1), Coordinate2D.fromString("[0.5, 1]"));
        Assertions.assertEquals(new Coordinate2D(0.5, 1), Coordinate2D.fromString("[0.5, 1.0]"));

        Coordinate2D a = new Coordinate2D(0, 0);
        Assertions.assertEquals(a.toString(), "[0.0, 0.0]");
        Assertions.assertEquals(a, Coordinate2D.fromString(a.toString()));

        Coordinate2D b = new Coordinate2D(3, 4);
        Assertions.assertEquals(b.toString(), "[3.0, 4.0]");
        Assertions.assertEquals(b, Coordinate2D.fromString(b.toString()));

        Coordinate2D c = new Coordinate2D(2.5, -1.25);
        Assertions.assertEquals(c.toString(), "[2.5, -1.25]");
        Assertions.assertEquals(c, Coordinate2D.fromString(c.toString()));
    }

}
