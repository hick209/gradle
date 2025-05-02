plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Plugin for cryptographic signing of publications, artifacts or files."

dependencies {
    api(projects.baseServices)
    api(projects.core)
    api(projects.coreApi)
    api(projects.fileCollections)
    api(projects.publish)
    api(projects.stdlibJavaExtensions)

    api(oldLibs.bouncycastlePgp)
    api(oldLibs.jspecify)
    api(oldLibs.groovy)
    api(oldLibs.inject)

    implementation(projects.functional)
    implementation(projects.loggingApi)
    implementation(projects.modelCore)
    implementation(projects.platformBase)

    implementation(oldLibs.guava)

    testFixturesImplementation(projects.baseServices) {
        because("Required to access org.gradle.internal.SystemProperties")
    }

    testImplementation(projects.maven)
    testImplementation(projects.ivy)
    testImplementation(testFixtures(projects.core))

    testRuntimeOnly(projects.distributionsPublishing) {
        because("ProjectBuilder tests load services from a Gradle distribution.")
    }

    integTestDistributionRuntimeOnly(projects.distributionsPublishing)

    testFixturesImplementation(projects.baseServices)
    testFixturesImplementation(projects.internalIntegTesting)
    testFixturesImplementation(projects.security)
    testFixturesImplementation(testFixtures(projects.core))

    testFixturesImplementation(oldLibs.slf4jApi)
    testFixturesImplementation(oldLibs.jetty)
    testFixturesImplementation(oldLibs.jettyWebApp)
}

strictCompile {
    ignoreRawTypes() // raw types used in public API
}

packageCycles {
    excludePatterns.add("org/gradle/plugins/signing/**")
}

tasks {
    integTest {
        usesJavadocCodeSnippets = true
    }
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
