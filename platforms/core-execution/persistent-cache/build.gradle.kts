plugins {
    id("gradlebuild.distribution.api-java")
    id("gradlebuild.publish-public-libraries")
}

description = """Persistent caches on disk and cross process locking.
    | Mostly for persisting Maps to the disk.
    | Also contains implementations for in-memory caches in front of the disk cache.
""".trimMargin()

dependencies {
    api(projects.buildOperations)
    api(projects.concurrent)
    api(projects.files)
    api(projects.serialization)
    api(projects.stdlibJavaExtensions)

    api(oldLibs.jspecify)

    implementation(projects.io)
    implementation(projects.time)

    implementation(oldLibs.guava)
    implementation(oldLibs.jsr305)
    implementation(oldLibs.slf4jApi)
    implementation(oldLibs.commonsIo)
    implementation(oldLibs.commonsLang)

    testImplementation(projects.messaging)
    testImplementation(projects.coreApi)
    testImplementation(projects.functional)
    testImplementation(testFixtures(projects.core))

    testRuntimeOnly(projects.distributionsCore) {
        because("DefaultPersistentDirectoryCacheTest instantiates DefaultClassLoaderRegistry which requires a 'gradle-plugins.properties' through DefaultPluginModuleRegistry")
    }

    integTestImplementation(projects.messaging)

    integTestDistributionRuntimeOnly(projects.distributionsCore)
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
