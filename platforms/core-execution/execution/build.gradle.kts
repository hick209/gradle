plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Execution engine that takes a unit of work and makes it happen"

dependencies {
    api(oldLibs.guava)
    api(oldLibs.jspecify)
    api(oldLibs.slf4jApi)

    api(projects.concurrent)
    api(projects.stdlibJavaExtensions)
    api(projects.serialization)
    compileOnly(oldLibs.errorProneAnnotations)
    api(projects.baseServices)
    api(projects.buildCache)
    api(projects.buildCacheBase)
    api(projects.buildCacheSpi)
    api(projects.buildOperations)
    api(projects.coreApi)
    api(projects.files)
    api(projects.functional)
    api(projects.hashing)
    api(projects.modelCore)
    api(projects.modelReflect)
    api(projects.persistentCache)
    api(projects.problemsApi)
    api(projects.snapshots)

    implementation(projects.time)
    implementation(projects.logging)
    implementation(projects.enterpriseOperations) {
        because("Adds generic build operations for the execution engine")
    }

    implementation(oldLibs.commonsLang)
    implementation(oldLibs.commonsIo)
    implementation(oldLibs.jsr305)

    testImplementation(projects.native)
    testImplementation(projects.logging)
    testImplementation(projects.processServices)
    testImplementation(projects.baseServicesGroovy)
    testImplementation(projects.resources)
    testImplementation(oldLibs.commonsIo)
    testImplementation(testFixtures(projects.serialization))
    testImplementation(testFixtures(projects.baseServices))
    testImplementation(testFixtures(projects.buildOperations))
    testImplementation(testFixtures(projects.fileCollections))
    testImplementation(testFixtures(projects.messaging))
    testImplementation(testFixtures(projects.snapshots))
    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.modelReflect))

    testFixturesImplementation(oldLibs.guava)
    testFixturesImplementation(projects.baseServices)
    testFixturesImplementation(projects.buildCache)
    testFixturesImplementation(projects.problems)
    testFixturesImplementation(projects.snapshots)

    integTestDistributionRuntimeOnly(projects.distributionsCore)
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
