plugins {
    // Uninstrumented since it is a mix of Groovy and Java code,
    // and additionally we don't plan to have upgrades for IDE plugins.
    id("gradlebuild.distribution.uninstrumented.api-java")
}

description = "Plugins for integration with native projects in XCode and Visual Studio IDEs"

sourceSets {
    main {
        // Incremental Groovy joint-compilation doesn't work with the Error Prone annotation processor
        errorprone.enabled = false
    }
}

dependencies {
    compileOnly(oldLibs.jetbrainsAnnotations)

    api(oldLibs.groovy)
    api(oldLibs.guava)
    api(oldLibs.inject)
    api(oldLibs.jspecify)
    api(oldLibs.plist)
    api(projects.baseIdePlugins)
    api(projects.baseServices)
    api(projects.core)
    api(projects.coreApi)
    api(projects.fileCollections)
    api(projects.ide)
    api(projects.stdlibJavaExtensions)
    api(projects.languageNative)
    api(projects.platformNative)
    api(projects.serviceProvider)

    implementation(projects.modelCore)
    implementation(projects.testingNative)
    implementation(projects.loggingApi)
    implementation(projects.serviceLookup)
    implementation(projects.functional)
    implementation(projects.platformBase)
    implementation(oldLibs.commonsLang)
    implementation(oldLibs.jsr305)

    runtimeOnly(projects.dependencyManagement)
    runtimeOnly(projects.testingBase)

    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.platformNative))
    testImplementation(testFixtures(projects.languageNative))
    testImplementation(testFixtures(projects.versionControl))

    integTestImplementation(projects.native)
    integTestImplementation(oldLibs.commonsIo)
    integTestImplementation(oldLibs.jgit)

    testFixturesApi(testFixtures(projects.ide))
    testFixturesImplementation(oldLibs.plist)
    testFixturesImplementation(oldLibs.guava)
    testFixturesImplementation(oldLibs.groovyXml)
    testFixturesImplementation(testFixtures(projects.ide))

    testRuntimeOnly(projects.distributionsCore) {
        because("Tests instantiate DefaultClassLoaderRegistry which requires a 'gradle-plugins.properties' through DefaultPluginModuleRegistry")
    }
    integTestDistributionRuntimeOnly(projects.distributionsNative)
}

integTest.usesJavadocCodeSnippets = true
tasks.isolatedProjectsIntegTest {
    enabled = false
}
