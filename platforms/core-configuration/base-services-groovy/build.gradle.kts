plugins {
    id("gradlebuild.distribution.api-java")
}

description = "A set of generic services and utilities specific for Groovy."

dependencies {
    api(projects.baseServices)
    api(projects.stdlibJavaExtensions)

    api(oldLibs.jspecify)
    api(oldLibs.groovy)
    api(oldLibs.guava)

    testImplementation(testFixtures(projects.core))
}
