plugins {
    id("gradlebuild.internal.java")
}

description = "Integration tests for our documentation snippets (aka samples)"

dependencies {
    integTestImplementation(projects.baseServices)
    integTestImplementation(projects.coreApi)
    integTestImplementation(projects.processServices)
    integTestImplementation(projects.persistentCache)
    integTestImplementation(oldLibs.groovy)
    integTestImplementation(oldLibs.slf4jApi)
    integTestImplementation(oldLibs.guava)
    integTestImplementation(oldLibs.ant)
    integTestImplementation(oldLibs.samplesCheck) {
        exclude(group = "org.codehaus.groovy", module = "groovy-all")
    }
    integTestImplementation(testFixtures(projects.core))
    integTestImplementation(testFixtures(projects.modelCore))

    integTestDistributionRuntimeOnly(projects.distributionsFull)
}

testFilesCleanup.reportOnly = true
