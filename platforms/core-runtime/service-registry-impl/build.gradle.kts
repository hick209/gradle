plugins {
    id("gradlebuild.distribution.implementation-java")
}

description = "Implementation of the service registry framework"

gradleModule {
    targetRuntimes {
        usedInWorkers = true
    }
}

dependencies {
    api(projects.serviceLookup)
    api(projects.serviceProvider)

    api(oldLibs.jspecify)

    implementation(projects.concurrent)
    implementation(projects.stdlibJavaExtensions)

    implementation(oldLibs.inject)
}

