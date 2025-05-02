plugins {
    id("gradlebuild.internal.java")
}

description = "Collection of test fixtures and tests for architecture testing Gradle code"

dependencies {
    api(platform(projects.distributionsDependencies))
    api(oldLibs.archunit)
    api(oldLibs.archunitJunit5Api)

    runtimeOnly(oldLibs.archunitJunit5) {
        because("This is what we use to write our architecture tests")
    }
}
