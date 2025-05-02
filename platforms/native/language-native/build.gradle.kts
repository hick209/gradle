plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Plugins and domain objects for building different native languages"

errorprone {
    disabledChecks.addAll(
        "DefaultCharset", // 1 occurrences
        "JavaLangClash", // 1 occurrences
        "MixedMutabilityReturnType", // 1 occurrences
        "UnusedMethod", // 2 occurrences
        "UnusedVariable", // 10 occurrences
    )
}

dependencies {
    api(projects.baseServices)
    api(projects.buildOperations)
    api(projects.concurrent)
    api(projects.core)
    api(projects.coreApi)
    api(projects.dependencyManagement)
    api(projects.files)
    api(projects.fileCollections)
    api(projects.fileOperations)
    api(projects.fileTemp)
    api(projects.hashing)
    api(projects.stdlibJavaExtensions)
    api(projects.modelCore)
    api(projects.persistentCache)
    api(projects.platformBase)
    api(projects.platformNative)
    api(projects.scopedPersistentCache)
    api(projects.serialization)
    api(projects.serviceLookup)
    api(projects.serviceProvider)
    api(projects.snapshots)

    api(oldLibs.guava)
    api(oldLibs.jspecify)
    api(oldLibs.inject)

    implementation(projects.loggingApi)
    implementation(projects.maven)
    implementation(projects.publish)
    implementation(projects.versionControl)

    implementation(oldLibs.commonsLang)
    implementation(oldLibs.groovy)
    implementation(oldLibs.slf4jApi)

    testFixturesApi(projects.baseServices) {
        because("Test fixtures export the Named class")
    }
    testFixturesApi(projects.platformBase) {
        because("Test fixtures export the Platform class")
    }

    testFixturesImplementation(projects.internalIntegTesting)
    testFixturesImplementation(testFixtures(projects.platformNative))

    testImplementation(projects.native)
    testImplementation(projects.resources)
    testImplementation(projects.baseServicesGroovy)
    testImplementation(oldLibs.commonsIo)
    testImplementation(testFixtures(projects.serialization))
    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.versionControl))
    testImplementation(testFixtures(projects.platformNative))
    testImplementation(testFixtures(projects.platformBase))
    testImplementation(testFixtures(projects.messaging))
    testImplementation(testFixtures(projects.snapshots))

    integTestImplementation(projects.native)
    integTestImplementation(projects.enterpriseOperations)
    integTestImplementation(projects.resources)
    integTestImplementation(oldLibs.nativePlatform)
    integTestImplementation(oldLibs.ant)
    integTestImplementation(oldLibs.jgit)

    testRuntimeOnly(projects.distributionsCore) {
        because("ProjectBuilder tests load services from a Gradle distribution.")
    }
    integTestDistributionRuntimeOnly(projects.distributionsNative)
}

packageCycles {
    excludePatterns.add("org/gradle/language/nativeplatform/internal/**")
}

integTest.usesJavadocCodeSnippets = true
tasks.isolatedProjectsIntegTest {
    enabled = false
}
