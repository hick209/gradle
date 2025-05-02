plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Plugins and model builders for integration with Eclipse and IntelliJ IDEs"

dependencies {
    api(projects.baseServices)
    api(projects.core)
    api(projects.coreApi)
    api(projects.dependencyManagement)
    api(projects.fileCollections)
    api(projects.fileOperations)
    api(projects.jvmServices)
    api(projects.stdlibJavaExtensions)
    api(projects.modelCore)
    api(projects.platformJvm)
    api(projects.serviceProvider)
    api(projects.toolingApi)

    api(oldLibs.guava)
    api(oldLibs.groovy)
    api(oldLibs.inject)
    api(oldLibs.jspecify)

    implementation(projects.baseServicesGroovy)
    implementation(projects.ear)
    implementation(projects.languageJava)
    implementation(projects.loggingApi)
    implementation(projects.platformBase)
    implementation(projects.pluginsJava)
    implementation(projects.pluginsJavaBase)
    implementation(projects.serviceLookup)
    implementation(projects.war)

    implementation(oldLibs.groovyXml)
    implementation(oldLibs.slf4jApi)
    implementation(oldLibs.commonsIo)
    implementation(oldLibs.commonsLang)

    runtimeOnly(projects.languageJvm)
    runtimeOnly(projects.testingBase)
    runtimeOnly(projects.testingJvm)

    testFixturesApi(projects.baseServices) {
        because("test fixtures export the Action class")
    }
    testFixturesApi(projects.logging) {
        because("test fixtures export the ConsoleOutput class")
    }
    testFixturesApi(projects.toolingApi) {
        because("test fixtures export the EclipseWorkspace and EclipseWorkspaceProject classes")
    }
    testFixturesImplementation(projects.dependencyManagement)
    testFixturesImplementation(projects.internalIntegTesting)
    testFixturesImplementation(projects.modelCore)
    testFixturesImplementation(oldLibs.groovyXml)
    testFixturesImplementation(oldLibs.xmlunit)

    testImplementation(projects.dependencyManagement)
    testImplementation(oldLibs.xmlunit)
    testImplementation(oldLibs.equalsverifier)
    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.dependencyManagement))
    testImplementation(testFixtures(projects.languageGroovy))

    testRuntimeOnly(projects.distributionsJvm) {
        because("ProjectBuilder tests load services from a Gradle distribution.")
    }
    integTestDistributionRuntimeOnly(projects.distributionsJvm)
    crossVersionTestDistributionRuntimeOnly(projects.distributionsJvm)
}

strictCompile {
    ignoreRawTypes()
}

packageCycles {
    excludePatterns.add("org/gradle/plugins/ide/internal/*")
    excludePatterns.add("org/gradle/plugins/ide/eclipse/internal/*")
    excludePatterns.add("org/gradle/plugins/ide/idea/internal/*")
    excludePatterns.add("org/gradle/plugins/ide/eclipse/model/internal/*")
    excludePatterns.add("org/gradle/plugins/ide/idea/model/internal/*")
}

integTest.usesJavadocCodeSnippets = true
testFilesCleanup.reportOnly = true
tasks.isolatedProjectsIntegTest {
    enabled = false
}
