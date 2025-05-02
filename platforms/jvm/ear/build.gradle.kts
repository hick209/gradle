plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Adds support for assembling web application EAR files"

errorprone {
    disabledChecks.addAll(
        "DefaultCharset", // 2 occurrences
    )
}

dependencies {
    api(oldLibs.groovy)
    api(oldLibs.inject)
    api(oldLibs.jspecify)

    api(projects.baseServices)
    api(projects.coreApi)
    api(projects.languageJvm)
    api(projects.modelCore)
    api(projects.platformJvm)

    implementation(projects.serviceLookup)
    implementation(projects.stdlibJavaExtensions)
    implementation(projects.core)
    implementation(projects.fileOperations)
    implementation(projects.dependencyManagement)
    implementation(projects.execution)
    implementation(projects.fileCollections)
    implementation(projects.languageJava)
    implementation(projects.platformBase)
    implementation(projects.pluginsJava)
    implementation(projects.pluginsJavaBase)

    implementation(oldLibs.groovyXml)
    implementation(oldLibs.guava)
    implementation(oldLibs.commonsLang)

    testImplementation(projects.baseServicesGroovy)
    testImplementation(testFixtures(projects.core))
    testImplementation(projects.native)
    testImplementation(projects.war)
    testImplementation(oldLibs.ant)

    testRuntimeOnly(projects.distributionsJvm) {
        because("ProjectBuilder tests load services from a Gradle distribution.")
    }
    integTestDistributionRuntimeOnly(projects.distributionsJvm)
}

strictCompile {
    ignoreRawTypes() // raw types used in public API
}

packageCycles {
    excludePatterns.add("org/gradle/plugins/ear/internal/*")
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
