plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Version control integration (with git) for source dependencies"

dependencies {
    api(projects.baseServices)
    api(projects.concurrent)
    api(projects.core)
    api(projects.coreApi)
    api(projects.dependencyManagement)
    api(projects.fileCollections)
    api(projects.scopedPersistentCache)
    api(projects.serviceProvider)
    api(projects.stdlibJavaExtensions)

    api(oldLibs.jgit)
    api(oldLibs.inject)
    api(oldLibs.jspecify)

    implementation(projects.files)
    implementation(projects.functional)
    implementation(projects.hashing)
    implementation(projects.loggingApi)
    implementation(projects.persistentCache)
    implementation(projects.serialization)

    implementation(oldLibs.guava)
    implementation(oldLibs.jgitSsh) {
        exclude("org.apache.sshd", "sshd-osgi") // Because it duplicates sshd-core and sshd-commons contents
    }
    implementation(oldLibs.jsr305)

    testImplementation(projects.native)
    testImplementation(projects.snapshots)
    testImplementation(projects.processServices)
    testImplementation(testFixtures(projects.core))

    testFixturesImplementation(projects.baseServices)
    testFixturesImplementation(projects.internalIntegTesting)

    testFixturesImplementation(oldLibs.jgit)
    testFixturesImplementation(oldLibs.jgitSsh) {
        exclude("org.apache.sshd", "sshd-osgi") // Because it duplicates sshd-core and sshd-commons contents
    }
    testFixturesImplementation(oldLibs.commonsIo)
    testFixturesImplementation(oldLibs.commonsHttpclient)
    testFixturesImplementation(oldLibs.guava)

    integTestImplementation(projects.enterpriseOperations)
    integTestImplementation(projects.launcher)
    integTestDistributionRuntimeOnly(projects.distributionsBasics)
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
