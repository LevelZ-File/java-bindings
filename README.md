# levelz-java

> Java & Kotlin Parser & API For LevelZ File Format

[![JitPack](https://jitpack.io/v/LevelZ-File/java-bindings.svg)](https://jitpack.io/#LevelZ-File/java-bindings)
![GitHub Release](https://img.shields.io/github/v/release/LevelZ-File/java-bindings)

## Overview

Provides Java & Kotlin Parsing/Support for the LevelZ File Format. 

### Download

Maven
```xml
<!-- Add Calculus Games Repository -->

<repositories>
    <repository>
        <id>calculus-games</id>
        <url>https://repo.calcugames.xyz/repository/maven-releases/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>xyz.calcugames</groupId>
        <artifactId>levelz-java</artifactId>
        <version>[VERSION]</version>
    </dependency>
</dependencies>
```

Gradle (Groovy)
```groovy
// Add Calculus Games Repository
repositories {
    maven { url 'https://repo.calcugames.xyz/repository/maven-releases/' }
}

dependencies {
    implementation 'xyz.calcugames:levelz-java:[VERSION]'
}
```

Gradle (Kotlin DSL)
```kts
// Add Calculus Games Repository
repositories {
    maven("https://repo.calcugames.xyz/repository/maven-releases/")
}

dependencies {
    implementation("xyz.calcugames:levelz-java:[VERSION]")
}
```
## Usage

### Java

```java
Coordinate2D coordinate2D = new Coordinate2D(1, 2);
Coordinate3D coordinate3D = new Coordinate3D(1, 2, 3);
```

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

### Kotlin

```kotlin
val (x, y) = Coordinate2D(1, 2)
val (x, y, z) = Coordinate3D(1, 2, 3)
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