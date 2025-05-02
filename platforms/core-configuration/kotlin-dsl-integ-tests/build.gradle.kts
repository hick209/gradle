plugins {
    id("gradlebuild.internal.kotlin")
    id("gradlebuild.kotlin-dsl-plugin-bundle-integ-tests")
}

description = "Kotlin DSL Integration Tests"

dependencies {
    testImplementation(testFixtures(projects.kotlinDsl))

    integTestImplementation(projects.messaging)
    integTestImplementation(projects.baseServices)
    integTestImplementation(projects.coreApi)
    integTestImplementation(projects.core)
    integTestImplementation(projects.modelCore)
    integTestImplementation(projects.internalTesting)
    integTestImplementation(projects.logging)
    integTestImplementation(projects.languageJvm)
    integTestImplementation(projects.platformJvm)
    integTestImplementation(testFixtures(projects.jacoco))
    integTestImplementation(oldLibs.mockwebserver) {
        exclude(group = "org.bouncycastle").because("MockWebServer uses a different version of BouncyCastle")
    }
    integTestImplementation(oldLibs.kotlinCompilerEmbeddable)
    integTestImplementation(oldLibs.mockitoKotlin)
    integTestImplementation(oldLibs.kotlinStdlib)
    integTestImplementation(oldLibs.kotlinReflect) {
        because("mockito-kotlin 1.6 requires kotlin-reflect in 1.0.7, we want to overrule that")
    }

    integTestDistributionRuntimeOnly(projects.distributionsFull)

    crossVersionTestImplementation(projects.coreApi)
    crossVersionTestImplementation(projects.logging)

    crossVersionTestDistributionRuntimeOnly(projects.distributionsFull)
    crossVersionTestLocalRepository(projects.kotlinDslPlugins)
}

testFilesCleanup.reportOnly = true
tasks.isolatedProjectsIntegTest {
    enabled = false
}
