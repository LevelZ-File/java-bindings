package xyz.calcugames.levelz.parser;

import xyz.calcugames.levelz.Level2D;
import xyz.calcugames.levelz.Level3D;
import xyz.calcugames.levelz.Scroll;
import xyz.calcugames.levelz.coord.Coordinate2D;
import xyz.calcugames.levelz.coord.Coordinate3D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class TestLevelParser {

    private static final LevelParser.Builder builder = LevelParser.builder();

    @Test
    @DisplayName("Test LevelParser#parse")
    public void testParse() {
        InputStream f1 = TestLevelParser.class.getResourceAsStream("/examples/2D/grasslands/1.lvlz");
        LevelParser p1 = builder.input(f1).build();
        Level2D l1 = (Level2D) p1.parse();

        Assertions.assertEquals(l1.getScroll(), Scroll.NONE);
        Assertions.assertEquals(l1.getSpawn(), new Coordinate2D(0, 0));

        InputStream f2 = TestLevelParser.class.getResourceAsStream("/examples/2D/grasslands/3.lvlz");
        LevelParser p2 = builder.input(f2).build();
        Level2D l2 = (Level2D) p2.parse();

        Assertions.assertEquals(l2.getScroll(), Scroll.HORIZONTAL_RIGHT);
        Assertions.assertEquals(l2.getSpawn(), new Coordinate2D(0, 10));

        InputStream f3 = TestLevelParser.class.getResourceAsStream("/examples/3D/grasslands/1.lvlz");
        LevelParser p3 = builder.input(f3).build();
        Level3D l3 = (Level3D) p3.parse();

        Assertions.assertEquals(l3.getSpawn(), new Coordinate3D(0, 0, 0));

        InputStream f4 = TestLevelParser.class.getResourceAsStream("/examples/2D/volcano/4.lvlz");
        LevelParser p4 = builder.input(f4).build();
        Level2D l4 = (Level2D) p4.parse();

        Assertions.assertEquals(l4.getScroll(), Scroll.NONE);
        Assertions.assertEquals(l4.getSpawn(), new Coordinate2D(5, 1));

        InputStream f5 = TestLevelParser.class.getResourceAsStream("/examples/3D/grasslands/3.lvlz");
        LevelParser p5 = builder.input(f5).build();
        Level3D l5 = (Level3D) p5.parse();

        Assertions.assertEquals(l5.getSpawn(), new Coordinate3D(0, 10, 0));
    }

}
