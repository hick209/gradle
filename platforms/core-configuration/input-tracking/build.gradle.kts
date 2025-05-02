plugins {
    id("gradlebuild.distribution.implementation-java")
}

description = "Configuration input discovery code"

dependencies {
    api(oldLibs.jspecify)
    api(oldLibs.guava)

    integTestDistributionRuntimeOnly(projects.distributionsCore)
}
