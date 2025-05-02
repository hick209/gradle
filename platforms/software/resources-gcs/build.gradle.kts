plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Implementation for interacting with Google Cloud Storage (GCS) repositories"


dependencies {
    api(projects.serviceProvider)
    api(projects.resources)

    api(oldLibs.gcs)
    api(oldLibs.jspecify)

    implementation(projects.stdlibJavaExtensions)
    implementation(projects.hashing)
    implementation(projects.loggingApi)

    implementation(oldLibs.commonsLang)
    implementation(oldLibs.googleApiClient)
    implementation(oldLibs.googleHttpClientGson)
    implementation(oldLibs.googleHttpClient)
    implementation(oldLibs.googleOauthClient)
    implementation(oldLibs.guava)
    implementation(oldLibs.slf4jApi)

    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.dependencyManagement))
    testImplementation(testFixtures(projects.ivy))
    testImplementation(testFixtures(projects.maven))

    testImplementation(oldLibs.groovy)

    integTestImplementation(projects.coreApi)
    integTestImplementation(projects.modelCore)

    integTestImplementation(oldLibs.commonsIo)
    integTestImplementation(oldLibs.jetty)

    integTestDistributionRuntimeOnly(projects.distributionsBasics)
}

strictCompile {
    ignoreDeprecations()
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
