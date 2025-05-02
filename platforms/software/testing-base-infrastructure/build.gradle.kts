plugins {
    id("gradlebuild.distribution.api-java")
}

description = """Generalized test infrastructure to support executing tests in test workers."""

gradleModule {
    targetRuntimes {
        usedInWorkers = true
    }
}

dependencies {
    api(projects.baseServices)
    api(projects.concurrent)
    api(projects.stdlibJavaExtensions)
    api(projects.messaging)
    api(projects.serialization)
    api(projects.time)
    api(projects.workerMain)

    api(oldLibs.jspecify)

    implementation(projects.io)
    implementation(projects.serviceLookup)
    implementation(projects.serviceProvider)
    implementation(projects.serviceRegistryBuilder)

    implementation(oldLibs.commonsLang)
    implementation(oldLibs.slf4jApi)

    testImplementation(projects.serviceRegistryImpl)
    testImplementation(oldLibs.commonsIo)
    testImplementation(testFixtures(projects.time))
    testImplementation(testFixtures(projects.serialization))
    testImplementation(testFixtures(projects.time))

    integTestDistributionRuntimeOnly(projects.distributionsCore)
}

packageCycles {
    excludePatterns.add("org/gradle/api/internal/tasks/testing/**")
}

integTest.usesJavadocCodeSnippets = true
tasks.isolatedProjectsIntegTest {
    enabled = false
}
