plugins {
    kotlin("jvm") version "1.9.22"

    java
    `maven-publish`
    jacoco
}

val jvm = JavaVersion.VERSION_11

group = "me.gamercoder215.calcgames"
version = "0.1.0"

java {
    sourceCompatibility = jvm
    targetCompatibility = jvm
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("org.jetbrains:annotations:24.1.0")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.isDeprecation = false
        options.isWarnings = false
        options.compilerArgs.addAll(listOf("-Xlint:all", "-Xlint:-processing"))
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = jvm.toString()
        }
    }

    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
        finalizedBy(jacocoTestReport)
    }

    jacocoTestReport {
        dependsOn(test)

        reports {
            xml.required.set(true)
            xml.outputLocation.set(layout.buildDirectory.file("jacoco.xml"))

            html.required.set(true)
            html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
        }
    }
}