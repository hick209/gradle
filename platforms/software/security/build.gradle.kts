plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Shared classes for projects requiring GPG support"

dependencies {
    api(projects.resources)

    api(oldLibs.bouncycastlePgp)
    api(oldLibs.jspecify)

    implementation(projects.stdlibJavaExtensions)
    implementation(projects.time)
    implementation(projects.loggingApi)

    implementation(oldLibs.bouncycastleProvider)
    implementation(oldLibs.guava)

    testRuntimeOnly(projects.logging)
}
