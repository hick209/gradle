plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Implementation for interacting with HTTP build caches"

dependencies {
    api(projects.stdlibJavaExtensions)
    api(projects.serviceProvider)

    api(oldLibs.httpcore)
    api(oldLibs.inject)
    api(oldLibs.jspecify)

    api(projects.baseServices)
    api(projects.buildCacheSpi)
    api(projects.coreApi)
    api(projects.resourcesHttp)

    implementation(projects.core)
    implementation(projects.logging)
    implementation(projects.resources)

    implementation(oldLibs.commonsHttpclient)
    implementation(oldLibs.guava)
    implementation(oldLibs.slf4jApi)

    testImplementation(testFixtures(projects.core))
    testImplementation(oldLibs.servletApi)

    integTestImplementation(projects.enterpriseOperations)
    integTestImplementation(testFixtures(projects.buildCache))
    integTestImplementation(oldLibs.jetty)

    integTestDistributionRuntimeOnly(projects.distributionsJvm) {
        because("Uses application plugin.")
    }
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
