plugins {
    id("gradlebuild.distribution.implementation-kotlin")
}

description = "Kotlin DSL Tooling Builders for IDEs"

dependencies {
    api(projects.coreApi)
    api(projects.core)
    api(oldLibs.kotlinStdlib)

    implementation(projects.classloaders)
    implementation(projects.serviceLookup)
    implementation(projects.stdlibJavaExtensions)
    implementation(projects.time)
    implementation(projects.kotlinDsl)
    implementation(projects.baseServices)
    implementation(projects.resources)
    implementation(projects.platformBase)
    implementation(projects.platformJvm)
    implementation(projects.pluginsJavaBase)
    implementation(projects.toolingApi)
    implementation(projects.kotlinDslToolingModels)
    implementation(projects.buildProcessServices)

    implementation(oldLibs.jspecify)

    testImplementation(testFixtures(projects.kotlinDsl))
    integTestImplementation(testFixtures(projects.toolingApi))

    integTestImplementation(projects.internalTesting)
    testFixturesImplementation(projects.kotlinDsl)
    testFixturesImplementation(projects.toolingApi)
    testFixturesImplementation(projects.internalIntegTesting)

    crossVersionTestImplementation(projects.persistentCache)
    crossVersionTestImplementation(oldLibs.slf4jApi)
    crossVersionTestImplementation(oldLibs.guava)
    crossVersionTestImplementation(oldLibs.ant)

    integTestDistributionRuntimeOnly(projects.distributionsBasics)
    crossVersionTestDistributionRuntimeOnly(projects.distributionsJvm) {
        because("Uses application plugin.")
    }
}

testFilesCleanup.reportOnly = true

// Kotlin DSL tooling builders should not be part of the public API
// TODO Find a way to not register this and the task instead
configurations.remove(configurations.apiStubElements.get())
