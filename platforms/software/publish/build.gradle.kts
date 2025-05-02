plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Base plugin for the maven and ivy publish plugins. Defines the publishing extension."

dependencies {
    api(projects.baseServices)
    api(projects.core)
    api(projects.coreApi)
    api(projects.dependencyManagement)
    api(projects.fileCollections)
    api(projects.hashing)
    api(projects.logging)
    api(projects.loggingApi)
    api(projects.modelCore)
    api(projects.serviceProvider)
    api(projects.stdlibJavaExtensions)

    api(oldLibs.inject)
    api(oldLibs.jspecify)

    implementation(projects.serviceLookup)
    implementation(projects.baseServicesGroovy) {
        because("Required for Specs")
    }
    implementation(projects.functional)

    implementation(oldLibs.commonsLang)
    implementation(oldLibs.gson)
    implementation(oldLibs.guava)
    implementation(oldLibs.jsr305)

    testImplementation(testFixtures(projects.core))

    testRuntimeOnly(projects.distributionsCore) {
        because("Tests instantiate DefaultClassLoaderRegistry which requires a 'gradle-plugins.properties' through DefaultPluginModuleRegistry")
    }
    integTestDistributionRuntimeOnly(projects.distributionsCore)
}

integTest.usesJavadocCodeSnippets = true
tasks.isolatedProjectsIntegTest {
    enabled = false
}
