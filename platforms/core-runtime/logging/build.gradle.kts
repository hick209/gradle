plugins {
    id("gradlebuild.distribution.api-java")
    id("gradlebuild.jmh")
}

description = "Logging infrastructure"

gradleModule {
    targetRuntimes {
        usedInWorkers = true
    }
}

dependencies {
    api(projects.stdlibJavaExtensions)
    api(projects.serialization)
    api(projects.serviceLookup)
    api(projects.serviceProvider)
    api(projects.time)
    api(projects.baseServices)
    api(projects.buildOperations)
    api(projects.buildOption)
    api(projects.cli)
    api(projects.enterpriseLogging)
    api(projects.enterpriseWorkers)
    api(projects.loggingApi)
    api(projects.native)
    api(projects.problemsApi)

    api(oldLibs.jansi)
    api(oldLibs.jspecify)
    api(oldLibs.jsr305)
    api(oldLibs.slf4jApi)

    implementation(projects.concurrent)
    implementation(projects.io)
    implementation(projects.messaging)
    implementation(projects.serviceRegistryBuilder)

    implementation(oldLibs.errorProneAnnotations)
    implementation(oldLibs.julToSlf4j)
    implementation(oldLibs.commonsLang)
    implementation(oldLibs.commonsIo)
    implementation(oldLibs.guava)

    // GSon is not strictly required here but removing it moves the dependency in the distribution from lib to lib/plugins
    // TODO Check if this is an issue
    runtimeOnly(oldLibs.gson)
    runtimeOnly(oldLibs.jclToSlf4j)
    runtimeOnly(oldLibs.log4jToSlf4j)

    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.time))
    testImplementation(testFixtures(projects.testingJvm))
    testImplementation(oldLibs.groovyDatetime)
    testImplementation(oldLibs.groovyDateUtil)

    integTestImplementation(projects.problems)

    integTestImplementation(oldLibs.ansiControlSequenceUtil)

    testFixturesImplementation(projects.baseServices)
    testFixturesImplementation(projects.enterpriseWorkers)
    testFixturesImplementation(testFixtures(projects.core))
    testFixturesImplementation(testFixtures(projects.time))
    testFixturesImplementation(oldLibs.slf4jApi)

    integTestDistributionRuntimeOnly(projects.distributionsCore)
}

packageCycles {
    excludePatterns.add("org/gradle/internal/featurelifecycle/**")
    excludePatterns.add("org/gradle/util/**")
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
