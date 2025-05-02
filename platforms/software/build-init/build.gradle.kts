plugins {
    id("gradlebuild.distribution.api-java")
    id("gradlebuild.update-init-template-versions")
}

description = """This project contains the Build Init plugin, which is automatically applied to the root project of every build, and provides the init and wrapper tasks.

This project should NOT be used as an implementation dependency anywhere (except when building a Gradle distribution)."""

errorprone {
    disabledChecks.addAll(
        "DefaultCharset", // 6 occurrences
        "GetClassOnEnum", // 1 occurrences
        "ImmutableEnumChecker", // 2 occurrences
        "ReferenceEquality", // 1 occurrences
    )
}

dependencies {
    api(oldLibs.inject)
    api(oldLibs.jspecify)
    api(oldLibs.maven3Settings)

    api(projects.baseServices)
    api(projects.buildInitSpecs)
    api(projects.core)
    api(projects.coreApi)
    api(projects.daemonServerWorker)
    api(projects.daemonServices)
    api(projects.dependencyManagement)
    api(projects.fileCollections)
    api(projects.logging)
    api(projects.platformJvm)
    api(projects.serviceProvider)
    api(projects.stdlibJavaExtensions)
    api(projects.jvmServices)
    api(projects.workers)

    implementation(projects.buildInitSpecsApi)
    implementation(projects.fileOperations)
    implementation(projects.loggingApi)
    implementation(projects.platformNative)
    implementation(projects.pluginsApplication) {
        because("Needs access to StartScriptGenerator.")
    }
    implementation(projects.pluginsJvmTestSuite)
    implementation(projects.serviceLookup)
    implementation(projects.wrapperShared)

    implementation(oldLibs.groovy)
    implementation(oldLibs.groovyTemplates)
    implementation(oldLibs.guava)
    implementation(oldLibs.gson)
    implementation(oldLibs.commonsLang)
    implementation(oldLibs.maven3SettingsBuilder)
    implementation(oldLibs.maven3Model)
    implementation(oldLibs.slf4jApi)
    implementation(oldLibs.plexusUtils)

    // We need to handle the Maven dependencies specially otherwise it breaks some cross version tests
    // TODO Figure out why and fix it - Move the two deps below to implementation and api and run ProjectTheExtensionCrossVersionSpec
    compileOnly(oldLibs.eclipseSisuPlexus) {
        exclude(module = "cdi-api") // To respect the Maven exclusion
    }
    compileOnly(oldLibs.maven3Compat)

    // 3 dependencies below are recommended as implementation but doing so adds them to the distribution
    // TODO Check why we reference them and if so, why they don't need to be in the distribution
    compileOnly(oldLibs.maven3Artifact)
    compileOnly(oldLibs.mavenResolverApi)
    compileOnly(oldLibs.plexusClassworlds)

    compileOnly(oldLibs.maven3Core)
    compileOnly(oldLibs.maven3PluginApi)

    compileOnly(projects.platformBase)

    runtimeOnly(projects.wrapperMain) {
        because("WrapperGenerator uses the /gradle-wrapper.jar resource")
    }

    testFixturesImplementation(projects.baseServices)
    testFixturesImplementation(projects.platformBase)
    testFixturesImplementation(projects.coreApi)
    testFixturesImplementation(projects.logging)
    testFixturesImplementation(projects.pluginsJava)
    testFixturesImplementation(projects.testingBase)
    testFixturesImplementation(projects.testSuitesBase)
    testFixturesImplementation(projects.pluginsJvmTestSuite)


    testImplementation(projects.cli)
    testImplementation(projects.baseServicesGroovy)
    testImplementation(projects.native)
    testImplementation(projects.snapshots)
    testImplementation(projects.processServices)
    testImplementation(projects.wrapperMain)
    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.platformNative))

    testRuntimeOnly(oldLibs.maven3Compat)
    testRuntimeOnly(oldLibs.maven3PluginApi)

    testRuntimeOnly(projects.distributionsFull) {
        because("ProjectBuilder tests load services from a Gradle distribution.  Toolchain usage requires JVM distribution.")
    }

    integTestImplementation(projects.native)
    integTestImplementation(oldLibs.jetty)

    integTestRuntimeOnly(oldLibs.maven3Compat)

    integTestDistributionRuntimeOnly(projects.distributionsFull)
}

packageCycles {
    excludePatterns.add("org/gradle/api/tasks/wrapper/internal/*")
}

integTest.testJvmXmx = "1g"
