plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Public and internal 'core' Gradle APIs that are required by other subprojects"

errorprone {
    disabledChecks.addAll(
        "NonApiType", // 1 occurrences
        "ReferenceEquality", // 2 occurrences
        "StringCharset", // 1 occurrences
    )
}

dependencies {
    compileOnly(oldLibs.jetbrainsAnnotations)

    api(projects.stdlibJavaExtensions)
    api(projects.buildCacheSpi)
    api(projects.loggingApi)
    api(projects.baseServices)
    api(projects.files)
    api(projects.resources)
    api(projects.persistentCache)
    api(projects.declarativeDslApi)
    api(oldLibs.jspecify)
    api(oldLibs.groovy)
    api(oldLibs.groovyAnt)
    api(oldLibs.guava)
    api(oldLibs.ant)
    api(oldLibs.inject)

    implementation(projects.io)
    implementation(projects.baseServicesGroovy)
    implementation(projects.logging)
    implementation(projects.buildProcessServices)

    implementation(oldLibs.commonsLang)
    implementation(oldLibs.jsr305)
    implementation(oldLibs.slf4jApi)

    runtimeOnly(oldLibs.kotlinReflect)

    testImplementation(oldLibs.asm)
    testImplementation(oldLibs.asmCommons)
    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.logging))

    testFixturesImplementation(projects.baseServices)

    integTestDistributionRuntimeOnly(projects.distributionsBasics)
}

packageCycles {
    excludePatterns.add("org/gradle/**")
}

strictCompile {
    ignoreRawTypes() // raw types used in public API
}

integTest.usesJavadocCodeSnippets = true
testFilesCleanup.reportOnly = true
tasks.isolatedProjectsIntegTest {
    enabled = false
}
