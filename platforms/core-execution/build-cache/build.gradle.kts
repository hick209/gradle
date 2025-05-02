plugins {
    id("gradlebuild.distribution.api-java")
    id("gradlebuild.publish-public-libraries")
    id("gradlebuild.jmh")
}

description = "Implementation of build cache controller and factories"

dependencies {
    api(projects.buildCacheBase)
    api(projects.buildCachePackaging)
    api(projects.buildCacheSpi)
    api(projects.buildOperations)
    api(projects.enterpriseOperations)
    api(projects.files)
    api(projects.hashing)
    api(projects.snapshots)

    api(oldLibs.jspecify)

    api(projects.stdlibJavaExtensions)
    implementation(oldLibs.commonsIo)
    api(oldLibs.guava)
    implementation(oldLibs.slf4jApi)

    jmhImplementation(projects.baseServices)
    jmhImplementation(projects.native)
    jmhImplementation(platform(projects.distributionsDependencies))
    jmhImplementation(oldLibs.aircompressor)
    jmhImplementation(oldLibs.commonsCompress)
    jmhImplementation(oldLibs.commonsIo)
    jmhImplementation(oldLibs.jtar)
    jmhImplementation(oldLibs.snappy)

    testImplementation(testFixtures(projects.baseServices))
    testImplementation(testFixtures(projects.buildOperations))
    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.snapshots))

    testFixturesImplementation(testFixtures(projects.hashing))

    integTestDistributionRuntimeOnly(projects.distributionsCore)
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
