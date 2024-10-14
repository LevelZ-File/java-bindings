import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.0.21"

    java
    `maven-publish`
    jacoco
    signing
}

val v = "0.3.0"
val jvm = JavaVersion.VERSION_11

group = "xyz.calcugames"
version = if (project.hasProperty("snapshot")) "$v-SNAPSHOT" else v

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
    compileOnly("org.jetbrains:annotations:26.0.1")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.2")
}

tasks {

    compileJava {
        options.encoding = "UTF-8"
        options.isDeprecation = false
        options.isWarnings = false
        options.compilerArgs.addAll(listOf("-Xlint:all", "-Xlint:-processing"))
    }

    compileKotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    javadoc {
        title = "LevelZ Java API v$version"
        sourceSets["main"].allJava.srcDir("src/main/javadoc")

        options {
            require(this is StandardJavadocDocletOptions)

            overview = "src/main/javadoc/overview.html"
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

    jar {
        dependsOn("javadocJar", "sourcesJar")
    }

    register("sourcesJar", Jar::class) {
        dependsOn(classes)

        archiveClassifier.set("sources")
        from(sourceSets["main"].allJava)
    }

    register("javadocJar", Jar::class) {
        dependsOn(javadoc)

        archiveClassifier.set("javadoc")
        from(javadoc)
    }
}

artifacts {
    add("archives", tasks["sourcesJar"])
    add("archives", tasks["javadocJar"])
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "xyz.calcugames"
            artifactId = "levelz-java"

            pom {
                name = "LevelZ Java API"
                description = "The Java API for LevelZ"
                url = "https://levelz.calcugames.xyz"

                licenses {
                    license {
                        name = "MIT License"
                        url = "https://opensource.org/licenses/MIT"
                    }
                }

                scm {
                    connection = "scm:git:git://github.com/LevelZ-File/java-bindings.git"
                    developerConnection = "scm:git:ssh://github.com/LevelZ-File/java-bindings.git"
                    url = "https://github.com/LevelZ-File/java-bindings"
                }
            }

            from(components["java"])
        }
    }

    repositories {
        maven {
            credentials {
                username = System.getenv("NEXUS_USERNAME")
                password = System.getenv("NEXUS_PASSWORD")
            }

            val releases = "https://repo.calcugames.xyz/repository/maven-releases/"
            val snapshots = "https://repo.calcugames.xyz/repository/maven-snapshots/"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshots else releases)
        }
    }
}
