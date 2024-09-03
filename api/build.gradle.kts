plugins {
    id("java")
    `maven-publish`
}

group = "net.minesprawl"
version = "1.0.1"

repositories {
    mavenCentral()
    maven {
        name = "spigotmc-repo"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}



dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.4-R0.1-SNAPSHOT")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "net.minesprawl"
            artifactId = "parkourplus-api"
            version = "1.0.1"
        }
    }
}

tasks.named("publishToMavenLocal") {
    dependsOn(tasks.named("assemble"))
}

val targetJavaVersion = JavaVersion.VERSION_17
java {
    sourceCompatibility = targetJavaVersion
    targetCompatibility = targetJavaVersion
    toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion.majorVersion.toInt()))
}
