plugins {
    id("gradlebuild.distribution.api-java")
}

description = "File system watchers for keeping the VFS up-to-date"

dependencies {
    api(projects.snapshots)
    api(projects.buildOperations)
    api(projects.files)
    api(projects.stdlibJavaExtensions)

    api(oldLibs.gradleFileEvents)
    api(oldLibs.jspecify)
    api(oldLibs.jsr305)
    api(oldLibs.nativePlatform)
    api(oldLibs.slf4jApi)
    implementation(projects.functional)

    implementation(oldLibs.guava)

    testImplementation(projects.processServices)
    testImplementation(projects.resources)
    testImplementation(projects.persistentCache)
    testImplementation(projects.buildOption)
    testImplementation(projects.enterpriseOperations)
    testImplementation(testFixtures(projects.buildOperations))
    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.fileCollections))
    testImplementation(testFixtures(projects.toolingApi))
    testImplementation(testFixtures(projects.launcher))
    testImplementation(testFixtures(projects.snapshots))

    testImplementation(oldLibs.commonsIo)

    integTestDistributionRuntimeOnly(projects.distributionsJvm) {
        because("Uses application plugin.")
    }
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
