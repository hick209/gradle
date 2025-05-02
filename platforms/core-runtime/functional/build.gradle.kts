plugins {
    id("gradlebuild.distribution.implementation-java")
    id("gradlebuild.publish-public-libraries")
}

description = "Tools to work with functional code, including data structures"

dependencies {
    api(oldLibs.jspecify)
    api(oldLibs.jsr305)
    api(projects.stdlibJavaExtensions)

    implementation(oldLibs.guava)
    implementation(oldLibs.fastutil)
}
