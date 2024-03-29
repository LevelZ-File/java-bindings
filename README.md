# levelz-java

> Java & Kotlin Parser & API For LevelZ File Format

[![](https://jitpack.io/v/LevelZ-File/java-bindings.svg)](https://jitpack.io/#LevelZ-File/java-bindings)
[![JitCi](https://jitci.com/gh/LevelZ-File/java-bindings/svg)](https://jitci.com/gh/LevelZ-File/java-bindings)

## Overview

Provides Java & Kotlin Parsing/Support for the LevelZ File Format. 

### Download

Maven
```xml
<!-- Add JitPack Repository -->

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.LevelZ-File</groupId>
        <artifactId>java-bindings</artifactId>
        <version>[VERSION]</version>
    </dependency>
</dependencies>
```

Gradle (Groovy)
```groovy
// Add JitPack Repository
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.LevelZ-File:java-bindings:[VERSION]'
}
```

Gradle (Kotlin DSL)
```kts
// Add JitPack Repository
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.LevelZ-File:java-bindings:[VERSION]")
}
```
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