plugins {
    // Uninstrumented since this very low-level subproject contains API classes,
    // but instrumentation annotation processor depends on it.
    id("gradlebuild.distribution.uninstrumented.api-java")
    id("gradlebuild.publish-public-libraries")
}

description = "Extensions to the Java language that are used across the Gradle codebase"

gradleModule {
    targetRuntimes {
        usedInWorkers = true
    }
}

dependencies {
    compileOnly(oldLibs.jetbrainsAnnotations)

    api(oldLibs.jsr305)
    api(oldLibs.jspecify)
}
