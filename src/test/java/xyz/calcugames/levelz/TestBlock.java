package xyz.calcugames.levelz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class TestBlock {

    @Test
    @DisplayName("Test Block#fromString")
    public void testReadRawBlock() {
        Assertions.assertEquals(new Block("test", Map.of("test", true)), Block.fromString("test<test=true>"));
        Assertions.assertEquals(new Block("ball", Map.of("bounce", 5)), Block.fromString("ball<bounce=5>"));
        Assertions.assertEquals(new Block("stone", Map.of("weight", 5.0)), Block.fromString("stone<weight=5.0>"));
    }

    @Test
    @DisplayName("Test Block#toString")
    public void testWriteRawBlock() {
        Assertions.assertEquals("test<test=true>", new Block("test", Map.of("test", true)).toString());
        Assertions.assertEquals("ball<bounce=5>", new Block("ball", Map.of("bounce", 5)).toString());
        Assertions.assertEquals("stone<weight=5.0>", new Block("stone", Map.of("weight", 5.0)).toString());
    }

}
