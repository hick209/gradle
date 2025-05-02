plugins {
    id("gradlebuild.distribution.api-java")
    id("gradlebuild.launchable-jar")
}

description = "Implementation for launching, controlling and communicating with Gradle Daemon from CLI and TAPI"

dependencies {
    api(projects.baseServices)
    api(projects.buildEvents)
    api(projects.buildOperations)
    api(projects.buildOption)
    api(projects.buildState)
    api(projects.classloaders)
    api(projects.cli)
    api(projects.concurrent)
    api(projects.core)
    api(projects.coreApi)
    api(projects.daemonProtocol)
    api(projects.enterpriseLogging)
    api(projects.execution)
    api(projects.fileCollections)
    api(projects.fileWatching)
    api(projects.files)
    api(projects.hashing)
    api(projects.instrumentationAgentServices)
    api(projects.stdlibJavaExtensions)
    api(projects.jvmServices)
    api(projects.logging)
    api(projects.loggingApi)
    api(projects.messaging)
    api(projects.modelCore)
    api(projects.native)
    api(projects.persistentCache)
    api(projects.problemsApi)
    api(projects.processMemoryServices)
    api(projects.serialization)
    api(projects.serviceLookup)
    api(projects.serviceProvider)
    api(projects.snapshots)
    api(projects.time)
    api(projects.toolingApi)

    // This project contains the Gradle client, daemon and tooling API provider implementations.
    // It should be split up, but for now, add dependencies on both the client and daemon pieces
    api(projects.clientServices)
    api(projects.daemonServices)

    api(oldLibs.guava)
    api(oldLibs.jspecify)

    implementation(projects.buildProcessServices)
    implementation(projects.enterpriseOperations)
    implementation(projects.functional)
    implementation(projects.io)
    implementation(projects.serviceRegistryBuilder)

    implementation(oldLibs.slf4jApi)

    runtimeOnly(projects.gradleCliMain)
    runtimeOnly(projects.declarativeDslProvider)
    runtimeOnly(projects.problems)

    runtimeOnly(oldLibs.commonsIo)
    runtimeOnly(oldLibs.commonsLang)
    runtimeOnly(oldLibs.slf4jApi)

    // The wrapper expects the launcher Jar to have classpath entries that contain the main class and its runtime classpath
    manifestClasspath(projects.gradleCliMain)

    testImplementation(projects.internalIntegTesting)
    testImplementation(testFixtures(projects.serialization))
    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.time))
    testImplementation(testFixtures(projects.logging))
    testImplementation(testFixtures(projects.toolingApi))
    testImplementation(testFixtures(projects.daemonProtocol))

    integTestImplementation(projects.persistentCache)
    integTestImplementation(oldLibs.slf4jApi)
    integTestImplementation(oldLibs.guava)
    integTestImplementation(oldLibs.commonsLang)
    integTestImplementation(oldLibs.commonsIo)
    integTestImplementation(testFixtures(projects.buildConfiguration))
    integTestImplementation(testFixtures(projects.buildProcessServices))
    integTestImplementation(testFixtures(projects.toolchainsJvmShared))

    integTestDistributionRuntimeOnly(projects.distributionsFull) {
        because("built-in options are required to be present at runtime for 'TaskOptionsSpec'")
    }
}

strictCompile {
    ignoreRawTypes() // raw types used in public API
}

testFilesCleanup.reportOnly = true
tasks.isolatedProjectsIntegTest {
    enabled = false
}
