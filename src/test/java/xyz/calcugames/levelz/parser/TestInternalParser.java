package xyz.calcugames.levelz.parser;

import xyz.calcugames.levelz.*;
import xyz.calcugames.levelz.coord.Coordinate2D;
import xyz.calcugames.levelz.coord.Coordinate3D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Set;

import static xyz.calcugames.levelz.parser.InternalParser.*;

public class TestInternalParser {

    private static final SecureRandom seed = new SecureRandom();

    @Test
    @DisplayName("Test InternalParser#value")
    public void testValue() {
        Assertions.assertEquals(true, value("true"));
        Assertions.assertEquals(false, value("false"));

        Assertions.assertEquals(5, value("5"));
        Assertions.assertEquals(5.0, value("5.0"));

        Assertions.assertEquals("Hello, World!", value("Hello, World!"));
    }

    @Test
    @DisplayName("Test InternalParser#read2DPoints")
    public void testRead2DPoints() {
        Assertions.assertEquals(Set.of(new Coordinate2D(0, 0)), read2DPoints("[0,0]"));
        Assertions.assertEquals(Set.of(new Coordinate2D(5, 5)), read2DPoints("[5, 5]"));
        Assertions.assertEquals(Set.of(new Coordinate2D(2.5, 2.5)), read2DPoints("[2.5, 2.5]"));

        Assertions.assertEquals(Set.of(new Coordinate2D(0, 0), new Coordinate2D(5, 5)), read2DPoints("[0,0]*[5, 5]"));
        Assertions.assertEquals(Set.of(new Coordinate2D(0, 0), new Coordinate2D(5, 5), new Coordinate2D(2.5, 2.5)), read2DPoints("[0,0]*[5, 5]*[2.5, 2.5]"));

        Assertions.assertEquals(Set.of(
                new Coordinate2D(0, 0), new Coordinate2D(0, 1), new Coordinate2D(0, 2),
                new Coordinate2D(1, 0), new Coordinate2D(1, 1), new Coordinate2D(1, 2),
                new Coordinate2D(2, 0), new Coordinate2D(2, 1), new Coordinate2D(2, 2)
        ), read2DPoints("(0, 2, 0, 2)^[0, 0]"));
    }
    
    @Test
    @DisplayName("Test InternalParser#read3DPoints")
    public void testRead3DPoints() {
        Assertions.assertEquals(Set.of(new Coordinate3D(0, 0, 0)), read3DPoints("[0,0,0]"));
        Assertions.assertEquals(Set.of(new Coordinate3D(5, 5, 5)), read3DPoints("[5, 5, 5]"));
        Assertions.assertEquals(Set.of(new Coordinate3D(2.5, 2.5, 2.5)), read3DPoints("[2.5, 2.5, 2.5]"));

        Assertions.assertEquals(Set.of(new Coordinate3D(0, 0, 0), new Coordinate3D(5, 5, 5)), read3DPoints("[0,0,0]*[5, 5, 5]"));
        Assertions.assertEquals(Set.of(new Coordinate3D(0, 0, 0), new Coordinate3D(5, 5, 5), new Coordinate3D(2.5, 2.5, 2.5)), read3DPoints("[0,0,0]*[5, 5, 5]*[2.5, 2.5, 2.5]"));

        Assertions.assertEquals(Set.of(
                new Coordinate3D(0, 0, 0), new Coordinate3D(0, 1, 0), new Coordinate3D(0, 2, 0),
                new Coordinate3D(1, 0, 0), new Coordinate3D(1, 1, 0), new Coordinate3D(1, 2, 0),
                new Coordinate3D(2, 0, 0), new Coordinate3D(2, 1, 0), new Coordinate3D(2, 2, 0),
                new Coordinate3D(0, 0, 1), new Coordinate3D(0, 1, 1), new Coordinate3D(0, 2, 1),
                new Coordinate3D(1, 0, 1), new Coordinate3D(1, 1, 1), new Coordinate3D(1, 2, 1),
                new Coordinate3D(2, 0, 1), new Coordinate3D(2, 1, 1), new Coordinate3D(2, 2, 1)
        ), read3DPoints("(0, 2, 0, 2, 0, 1)^[0, 0, 0]"));
    }

    @Test
    @DisplayName("Test InternalParser#readBlock")
    public void testReadBlock() {
        Assertions.assertEquals(new Block("test", Map.of("test", true)), readBlock("test<test=true>", seed));
        Assertions.assertEquals(new Block("test", Map.of("test", 5)), readBlock("test<test=5>", seed));
        Assertions.assertEquals(new Block("test", Map.of("test", 5.0)), readBlock("test<test=5.0>", seed));

        Assertions.assertEquals(new Block("test", Map.of("test", false)), readBlock("{1.0=test<test=false>}", seed));
        Assertions.assertEquals(new Block("grass", Map.of("value", 7)), readBlock("{grass<value=7>}", seed));
    }

    @Test
    @DisplayName("Test InternalParser#readRawBlock")
    public void testReadRawBlock() {
        Assertions.assertEquals(new Block("test", Map.of("test", true)), readRawBlock("test<test=true>"));
        Assertions.assertEquals(new Block("ball", Map.of("bounce", 5)), readRawBlock("ball<bounce=5>"));
        Assertions.assertEquals(new Block("stone", Map.of("weight", 5.0)), readRawBlock("stone<weight=5.0>"));
    }

    @Test
    @DisplayName("Test InternalParser#readHeaders")
    public void testReadHeaders() {
        String[] h1a = new String[] {
                "@type 2",
                "@spawn default"
        };
        Map<String, String> h1 = readHeaders(h1a);
        Assertions.assertEquals(2, h1.size());
        Assertions.assertEquals("2", h1.get("type"));
        Assertions.assertEquals("default", h1.get("spawn"));

        String[] h2a = new String[] {
                "@type 3",
                "@spawn [0, 0, 0]"
        };
        Map<String, String> h2 = readHeaders(h2a);
        Assertions.assertEquals(2, h2.size());
        Assertions.assertEquals("3", h2.get("type"));
        Assertions.assertEquals("[0, 0, 0]", h2.get("spawn"));

        String[] h3a = new String[] {
                "@type 2",
                "@spawn [15, 0]",
                "@scroll horizontal-left"
        };
        Map<String, String> h3 = readHeaders(h3a);
        Assertions.assertEquals(3, h3.size());
        Assertions.assertEquals("2", h3.get("type"));
        Assertions.assertEquals("[15, 0]", h3.get("spawn"));
        Assertions.assertEquals("horizontal-left", h3.get("scroll"));

        String[] h4a = new String[] {
                "@type 3",
        };
        Assertions.assertThrows(MissingHeaderException.class, () -> readHeaders(h4a));

        String[] h5a = new String[] {
                "@spawn [15, 0]",
        };
        Assertions.assertThrows(MissingHeaderException.class, () -> readHeaders(h5a));
    }

}
