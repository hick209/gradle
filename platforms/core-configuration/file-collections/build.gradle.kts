plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Implementation of types that represent containers of files"

dependencies {
    api(projects.stdlibJavaExtensions)
    api(projects.baseServices)
    api(projects.coreApi)
    api(projects.files)
    api(projects.modelCore)
    api(projects.logging)
    api(projects.native)

    api(oldLibs.groovy)
    api(oldLibs.guava)
    api(oldLibs.inject)
    api(oldLibs.jspecify)

    implementation(projects.io)
    implementation(projects.baseServicesGroovy)

    implementation(oldLibs.slf4jApi)
    implementation(oldLibs.commonsIo)

    compileOnly(oldLibs.jetbrainsAnnotations)

    testImplementation(projects.processServices)
    testImplementation(projects.resources)
    testImplementation(projects.snapshots)
    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.coreApi))
    testImplementation(testFixtures(projects.modelCore))
    testImplementation(testFixtures(projects.modelReflect))
    testImplementation(oldLibs.groovyDateUtil)

    testFixturesImplementation(projects.baseServices)
    testFixturesImplementation(projects.coreApi)
    testFixturesImplementation(projects.native)

    testFixturesImplementation(oldLibs.guava)

    testRuntimeOnly(projects.distributionsCore) {
        because("Tests instantiate DefaultClassLoaderRegistry which requires a 'gradle-plugins.properties' through DefaultPluginModuleRegistry")
    }
    integTestDistributionRuntimeOnly(projects.distributionsCore)
}

strictCompile {
    ignoreRawTypes() // raw types used in public API
}

packageCycles {
    // Some cycles have been inherited from the time these classes were in :core
    excludePatterns.add("org/gradle/api/internal/file/collections/**")
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
