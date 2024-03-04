# levelz-java

> Java & Kotlin Parser & API For LevelZ File Format

## Overview

Provides Java & Kotlin Parsing/Support for the LevelZ File Format. 

## Usage

```java

public class Main {
    public static void main(String[] args) {
        // Resource from JAR
        InputStream is = Main.class.getResourceAsStream("/example_2d.lvlz");
        Level2D level = (Level2D) LevelParser.builder()
                .input(is)
                .build()
                .parse();
        
        // Resource from File
        File file = new File("example_2d.lvlz");
        Level2D level = (Level2D) LevelParser.builder()
                .input(file)
                .build()
                .parse();
    }
}

```

```kotlin

fun main() {
    // Resource from JAR
    val input = Main::class.java.getResourceAsStream("/example_3d.lvlz")
    val level = LevelParser.builder()
        .input(input)
        .build()
        .parse().as3D

    // Resource from File
    val file = File("example_3d.lvlz")
    val level = LevelParser.builder()
        .input(file)
        .build()
        .parse().as3D
}

```