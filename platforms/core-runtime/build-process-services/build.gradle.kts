plugins {
    id("gradlebuild.distribution.implementation-java")
}

description = "Services and types used to setup a build process from a Gradle distribution."

dependencies {
    api(projects.classloaders)
    api(projects.stdlibJavaExtensions)

    api(oldLibs.jspecify)

    implementation(projects.baseServices)

    implementation(oldLibs.guava)

    testImplementation(oldLibs.asm)
    testImplementation(oldLibs.asmTree)

    testRuntimeOnly(projects.resources)
}
