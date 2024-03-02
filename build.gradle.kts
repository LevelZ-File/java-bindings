plugins {
    kotlin("jvm") version "1.9.21"
    java
}

val jvm = JavaVersion.VERSION_11

java {
    sourceCompatibility = jvm
    targetCompatibility = jvm
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib")
}