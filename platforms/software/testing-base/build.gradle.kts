plugins {
    id("gradlebuild.distribution.api-java")
}

description = """Basic testing related plugins, which establish conventions for testing output directories,
and setup basic testing-related features like a testSuites container and the testing extension.  It provides most of the
testing-related abstract base types and interfaces for things like Test tasks, listeners and filters.

This project is a implementation dependency of many other testing-related subprojects in the Gradle build.
"""

dependencies {
    api(projects.baseServices)
    api(projects.buildOperations)
    api(projects.core)
    api(projects.coreApi)
    api(projects.enterpriseLogging)
    api(projects.stdlibJavaExtensions)
    api(projects.logging)
    api(projects.loggingApi)
    api(projects.messaging)
    api(projects.native)
    api(projects.problemsApi)
    api(projects.reportRendering)
    api(projects.reporting)
    api(projects.serialization)
    api(projects.serviceProvider)
    api(projects.testingBaseInfrastructure)
    api(projects.time)

    api(oldLibs.groovy)
    api(oldLibs.guava)
    api(oldLibs.jspecify)
    api(oldLibs.inject)

    implementation(projects.baseServicesGroovy)
    implementation(projects.concurrent)
    implementation(projects.files)
    implementation(projects.modelCore)

    implementation(oldLibs.commonsLang)
    implementation(oldLibs.commonsIo)
    implementation(oldLibs.kryo)
    implementation(oldLibs.slf4jApi)

    testImplementation(projects.fileCollections)
    testImplementation(projects.enterpriseOperations)
    testImplementation(testFixtures(projects.baseServices))
    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.logging))
    testImplementation(testFixtures(projects.messaging))
    testImplementation(testFixtures(projects.platformBase))
    testImplementation(testFixtures(projects.serialization))
    testImplementation(testFixtures(projects.time))

    testImplementation(oldLibs.commonsIo)

    testFixturesImplementation(projects.baseServices)
    testFixturesImplementation(projects.internalIntegTesting)
    testFixturesImplementation(projects.logging)
    testFixturesImplementation(projects.modelCore)

    testFixturesImplementation(oldLibs.guava)
    testFixturesImplementation(oldLibs.jsoup)

    testRuntimeOnly(projects.distributionsCore) {
        because("ProjectBuilder tests load services from a Gradle distribution.")
    }
    integTestDistributionRuntimeOnly(projects.distributionsCore)
}

strictCompile {
    ignoreRawTypes() // raw types used in public API (org.gradle.api.tasks.testing.AbstractTestTask)
}

packageCycles {
    excludePatterns.add("org/gradle/api/internal/tasks/testing/**")
}

integTest.usesJavadocCodeSnippets = true
tasks.isolatedProjectsIntegTest {
    enabled = false
}
