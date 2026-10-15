plugins {
    id("java")
}

group = "space.wayvlyte.randomizer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "${group}.Window"
        )
    }
}

tasks.test {
    useJUnitPlatform()
}