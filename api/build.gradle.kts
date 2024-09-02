plugins {
    id("java")
}

group = "net.minesprawl"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
}

val targetJavaVersion = JavaVersion.VERSION_17
java {
    sourceCompatibility = targetJavaVersion
    targetCompatibility = targetJavaVersion
    toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion.majorVersion.toInt()))
}