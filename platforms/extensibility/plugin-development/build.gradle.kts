plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Gradle plugin development plugins"

errorprone {
    disabledChecks.addAll(
        "DefaultCharset", // 1 occurrences
    )
}

dependencies {
    api(projects.baseServices)
    api(projects.core)
    api(projects.coreApi)
    api(projects.daemonServerWorker)
    api(projects.files)
    api(projects.logging)
    api(projects.modelReflect)
    api(projects.platformJvm)
    api(projects.problemsApi)
    api(projects.resources)
    api(projects.stdlibJavaExtensions)
    api(projects.toolchainsJvmShared)
    api(projects.workers)

    api(oldLibs.groovy)
    api(oldLibs.gson)
    api(oldLibs.jspecify)
    api(oldLibs.inject)

    implementation(projects.classloaders)
    implementation(projects.serviceLookup)
    implementation(projects.serviceProvider)
    implementation(projects.serviceRegistryBuilder)
    implementation(projects.buildOption)
    implementation(projects.dependencyManagement)
    implementation(projects.execution)
    implementation(projects.fileOperations)
    implementation(projects.hashing)
    implementation(projects.ivy)
    implementation(projects.languageJava)
    implementation(projects.languageJvm)
    implementation(projects.loggingApi)
    implementation(projects.maven)
    implementation(projects.messaging)
    implementation(projects.modelCore)
    implementation(projects.modelGroovy)
    implementation(projects.pluginsGroovy)
    implementation(projects.pluginsJava)
    implementation(projects.pluginsJavaBase)
    implementation(projects.pluginsJavaLibrary)
    implementation(projects.pluginsJvmTestSuite)
    implementation(projects.pluginUse)
    implementation(projects.publish)
    implementation(projects.testingJvm)
    implementation(projects.toolchainsJvm)

    implementation(oldLibs.asm)
    implementation(oldLibs.guava)

    testImplementation(projects.fileCollections)
    testImplementation(projects.enterpriseOperations)

    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.logging))

    integTestImplementation(projects.baseServicesGroovy)

    integTestImplementation(testFixtures(projects.modelReflect))
    integTestImplementation(testFixtures(projects.toolingApi))

    integTestImplementation(oldLibs.groovyTest)
    integTestImplementation(oldLibs.jetbrainsAnnotations)

    integTestLocalRepository(projects.toolingApi) {
        because("Required by GradleImplDepsCompatibilityIntegrationTest")
    }

    testRuntimeOnly(projects.distributionsBasics) {
        because("ProjectBuilder tests load services from a Gradle distribution.")
    }
    integTestDistributionRuntimeOnly(projects.distributionsBasics)
    crossVersionTestDistributionRuntimeOnly(projects.distributionsBasics)

    testFixturesImplementation(projects.modelCore)
    testFixturesImplementation(projects.logging)
    testFixturesImplementation(oldLibs.gson)
    testFixturesImplementation(projects.baseServices)
}

integTest.usesJavadocCodeSnippets = true

strictCompile {
    ignoreDeprecations()
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
