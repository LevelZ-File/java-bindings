plugins {
    kotlin("jvm") version "1.9.23"

    java
    `maven-publish`
    jacoco
    signing
}

val jvm = JavaVersion.VERSION_11

group = "me.gamercoder215.calcgames"
version = "0.1.1"

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
            groupId = "me.gamercoder215.calcgames"
            artifactId = "levelz-java"

            pom {
                name = "LevelZ Java API"
                description = "The Java API for LevelZ"
                url = "https://levelz.gamercoder215.me"

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
}