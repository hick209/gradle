plugins {
    application
}

repositories {
    mavenCentral()
}

// tag::usage[]
dependencies {
    // Platform
    implementation(platform(project(":platform")))
    // Catalog
    testImplementation(oldLibs.junit.jupiter)
    testRuntimeOnly(oldLibs.junit.jupiter.launcher)
    implementation(oldLibs.guava)
}
// end::usage[]
