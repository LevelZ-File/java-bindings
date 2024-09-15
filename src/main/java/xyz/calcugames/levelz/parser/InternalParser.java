package xyz.calcugames.levelz.parser;

import xyz.calcugames.levelz.*;
import xyz.calcugames.levelz.coord.Coordinate2D;
import xyz.calcugames.levelz.coord.Coordinate3D;
import xyz.calcugames.levelz.coord.CoordinateMatrix2D;
import xyz.calcugames.levelz.coord.CoordinateMatrix3D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.logging.Logger;

interface InternalParser {

    static String[] lines(Reader r, Logger logger) {
        BufferedReader reader = new BufferedReader(r);
        String[] lines = reader.lines().toArray(String[]::new);

        try {
            reader.close();
        } catch (IOException e) {
            logger.severe("Failed to close reader");
            logger.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
        }

        return lines;
    }

    static <T> T roll(Map<T, Double> map, Random seed) {
        double sum = map.values().stream().mapToDouble(Double::doubleValue).sum();
        if (sum > 1.0)
            throw new ParseException(String.format(Errors.INVALID_PROBABILITIES, sum));

        T t = null;

        double r = seed.nextDouble();
        double cumulative = 0.0;
        for (Map.Entry<T, Double> entry : map.entrySet()) {
            cumulative += entry.getValue();
            if (r <= cumulative) {
                t = entry.getKey();
                break;
            }
        }

        return t;
    }

    // Parsing

    static String[][] split(String[] file) {
        int index = 0;
        for (int i = 0; i < file.length; i++) {
            if (file[i].equals(Keywords.HEADER_END)) {
                index = i;
                break;
            }
        }

        String[] header = new String[index];
        String[] body = new String[file.length - index - 1];

        System.arraycopy(file, 0, header, 0, index);
        System.arraycopy(file, index + 1, body, 0, body.length);

        return new String[][]{header, body};
    }

    static Map<String, String> readHeaders(String[] headers) {
        Map<String, String> headers0 = new HashMap<>();
        for (String l : headers) {
            if (!l.startsWith("@")) throw new ParseException(String.format(Errors.INVALID_HEADER, l));
            String[] parts = l.split("\\s", 2);

            if (parts.length < 2) throw new ParseException(String.format(Errors.INVALID_HEADER, l));

            headers0.put(parts[0].substring(1), parts[1]);
        }

        if (!headers0.containsKey("type"))
            throw new xyz.calcugames.levelz.parser.MissingHeaderException(Errors.MISSING_DIMENSION_TYPE);

        if (!headers0.containsKey("spawn"))
            throw new xyz.calcugames.levelz.parser.MissingHeaderException(Errors.MISSING_SPAWNPOINT);

        return headers0;
    }

    static Set<Coordinate2D> read2DPoints(String input) {
        Set<Coordinate2D> points = new HashSet<>();
        String[] inputs = input.split("\\*");

        for (String s0 : inputs) {
            String s = s0.trim();
            if (s.isEmpty()) continue;

            if (s.startsWith("(") && s.endsWith("]"))
                points.addAll(CoordinateMatrix2D.fromString(s).getCoordinates());
            else
                points.add(Coordinate2D.fromString(s));
        }

        return points;
    }

    static Set<Coordinate3D> read3DPoints(String input) {
        Set<Coordinate3D> points = new HashSet<>();
        String[] inputs = input.split("\\*");

        for (String s0 : inputs) {
            String s = s0.trim();
            if (s.isEmpty()) continue;

            if (s.startsWith("(") && s.endsWith("]"))
                points.addAll(CoordinateMatrix3D.fromString(s).getCoordinates());
            else
                points.add(Coordinate3D.fromString(s));
        }
        
        return points;
    }

    static Map<Block, Coordinate2D[]> read2DLine(String line, Random seed) {
        String[] split = line.replaceAll("\\s", "").split(":");
        Map<Block, Coordinate2D[]> map = new HashMap<>();

        Block block = readBlock(split[0], seed);
        if (block == null) return map;

        Set<Coordinate2D> points = read2DPoints(split[1]);

        map.put(block, points.toArray(Coordinate2D[]::new));
        return map;
    }

    static Map<Block, Coordinate3D[]> read3DLine(String line, Random seed) {
        String[] split = line.replaceAll("\\s", "").split(":");
        Map<Block, Coordinate3D[]> map = new HashMap<>();

        Block block = readBlock(split[0], seed);
        if (block == null) return map;

        Set<Coordinate3D> points = read3DPoints(split[1]);

        map.put(block, points.toArray(Coordinate3D[]::new));
        return map;
    }

    static Block readBlock(String blockLine, Random seed) {
        Block block;
        if (blockLine.startsWith("{") && blockLine.endsWith("}")) {
            String block0 = blockLine.replaceAll("[{}]", "");
            String[] blocks;

            if (blockLine.contains(">,")) {
                blocks = block0.split(">,");
                for (int i = 0; i < blocks.length; i++)
                    if (blocks[i].contains("<"))
                        blocks[i] = blocks[i] + '>';
            } else
                blocks = block0.split(",");

            int l = blocks.length;

            Map<String, Double> blockToChance = new HashMap<>();
            for (String b : blocks) {
                String[] split0 = b.split("=", 2);
                try {
                    blockToChance.put(split0[1], Double.parseDouble(split0[0]));
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    blockToChance.put(b, 1.0 / l);
                }

            }

            block = Block.fromString(roll(blockToChance, seed));
        } else
            block = Block.fromString(blockLine);

        return block;
    }

    static Level parse(String[] file, Random seed) {
        Level level;

        String[][] split = split(file);
        Map<String, String> headers = readHeaders(split[0]);
        Dimension dimension = Dimension.values()[Integer.parseInt(headers.get("type")) - 2];
        boolean is2D = dimension.is2D();

        headers.compute("spawn", (k, v) -> v.equals("default") ? dimension.getDefaultCoordinate() : v);
        if (is2D)
            headers.putIfAbsent("scroll", "none");

        Set<LevelObject> blocks = new HashSet<>();
        for (String line : split[1]) {
            if (line.startsWith(Keywords.COMMENT)) continue;
            if (line.equalsIgnoreCase(Keywords.END)) break;

            int ci = line.indexOf(Keywords.COMMENT_CHAR);
            String line0 = line.substring(0, ci == -1 ? line.length() : ci).trim();

            if (is2D) {
                Map<Block, Coordinate2D[]> blocks2D = read2DLine(line0, seed);
                blocks2D.forEach((k, v) -> {
                    for (Coordinate2D c : v) blocks.add(new LevelObject(k, c));
                });
            } else {
                Map<Block, Coordinate3D[]> blocks3D = read3DLine(line0, seed);
                blocks3D.forEach((k, v) -> {
                    for (Coordinate3D c : v) blocks.add(new LevelObject(k, c));
                });
            }
        }

        if (is2D)
            level = new Level2D(headers, blocks);
        else
            level = new Level3D(headers, blocks);

        return level;
    }

}
