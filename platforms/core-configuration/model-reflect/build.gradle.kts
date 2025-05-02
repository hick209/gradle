plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Implementation of model reflection"

dependencies {
    api(projects.baseServices)
    api(projects.coreApi)
    api(projects.logging)
    api(projects.persistentCache)
    api(projects.problemsApi)
    api(projects.stdlibJavaExtensions)

    api(oldLibs.guava)
    api(oldLibs.jspecify)

    implementation(oldLibs.commonsLang)
    implementation(oldLibs.groovy)
    implementation(oldLibs.inject)

    testFixturesApi(projects.internalIntegTesting)
    testFixturesImplementation(oldLibs.guava)
}
