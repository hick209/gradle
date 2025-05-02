plugins {
    id("gradlebuild.distribution.implementation-java")
    id("gradlebuild.publish-public-libraries")
}

description = "Build operations are our way to inspect the process of executing a build"

gradleModule {
    targetRuntimes {
        usedInWorkers = true
    }
}

dependencies {
    api(projects.stdlibJavaExtensions)
    api(projects.time)

    api(oldLibs.jspecify)

    implementation(oldLibs.slf4jApi)

    testFixturesImplementation(oldLibs.guava)

    testImplementation(testFixtures(projects.time))
}

