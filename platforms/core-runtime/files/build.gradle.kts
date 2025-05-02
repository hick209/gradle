plugins {
    id("gradlebuild.distribution.api-java")
    id("gradlebuild.publish-public-libraries")
}

description = "Base tools to work with files"

gradleModule {
    targetRuntimes {
        usedInWorkers = true
    }
}

dependencies {
    api(projects.stdlibJavaExtensions)

    api(oldLibs.jspecify)
    api(oldLibs.jsr305)

    implementation(oldLibs.guava)
    implementation(oldLibs.slf4jApi)

    testImplementation(projects.native)
    testImplementation(projects.baseServices) {
        because("TextUtil is needed")
    }
    testImplementation(testFixtures(projects.native))
}
