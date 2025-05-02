plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Implementation for interacting with repositories over sftp"

dependencies {
    api(projects.concurrent)
    api(projects.stdlibJavaExtensions)
    api(projects.serviceProvider)
    api(projects.coreApi)
    api(projects.resources)

    api(oldLibs.jsch)

    implementation(projects.core)

    implementation(oldLibs.commonsIo)
    implementation(oldLibs.guava)
    implementation(oldLibs.jsr305)
    implementation(oldLibs.slf4jApi)

    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.dependencyManagement))
    testImplementation(testFixtures(projects.ivy))
    testImplementation(testFixtures(projects.maven))

    integTestImplementation(projects.logging)
    integTestImplementation(oldLibs.jetty)
    integTestImplementation(oldLibs.sshdCore)
    integTestImplementation(oldLibs.sshdScp)
    integTestImplementation(oldLibs.sshdSftp)

    integTestDistributionRuntimeOnly(projects.distributionsBasics)
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
