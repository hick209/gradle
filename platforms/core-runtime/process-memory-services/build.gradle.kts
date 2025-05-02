plugins {
    id("gradlebuild.distribution.implementation-java")
}

description = "Process memory abstractions."

gradleModule {
    targetRuntimes {
        usedInWorkers = true
    }
}

dependencies {
    api(projects.concurrent)
    api(projects.stdlibJavaExtensions)
    api(projects.baseServices)
    api(projects.messaging)

    api(oldLibs.jspecify)

    implementation(projects.native)
    implementation(projects.serviceLookup)

    implementation(oldLibs.slf4jApi)
    implementation(oldLibs.guava)
    implementation(oldLibs.nativePlatform)

    testImplementation(testFixtures(projects.core))

    integTestDistributionRuntimeOnly(projects.distributionsCore)
}

packageCycles {
    excludePatterns.add("org/gradle/process/internal/**")
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
