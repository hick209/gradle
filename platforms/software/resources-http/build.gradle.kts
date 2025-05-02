plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Implementation for interacting with repositories over HTTP"

dependencies {
    api(projects.stdlibJavaExtensions)
    api(projects.serviceProvider)
    api(projects.coreApi)
    api(projects.core)
    api(projects.logging)
    api(projects.resources)

    api(oldLibs.commonsHttpclient)
    api(oldLibs.httpcore)
    api(oldLibs.jspecify)

    implementation(projects.baseServices)
    implementation(projects.hashing)
    implementation(projects.loggingApi)

    implementation(oldLibs.commonsIo)
    implementation(oldLibs.commonsLang)
    implementation(oldLibs.guava)
    implementation(oldLibs.jcifs)
    implementation(oldLibs.jsoup)
    implementation(oldLibs.slf4jApi)

    testImplementation(projects.internalIntegTesting)
    testImplementation(oldLibs.jettyWebApp)
    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.logging))

    testFixturesImplementation(projects.baseServices)
    testFixturesImplementation(projects.logging)
    testFixturesImplementation(projects.internalIntegTesting)
    testFixturesImplementation(oldLibs.slf4jApi)

    integTestDistributionRuntimeOnly(projects.distributionsCore)
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
