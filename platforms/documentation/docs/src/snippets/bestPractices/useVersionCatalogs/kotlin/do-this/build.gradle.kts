// tag::do-this[]
plugins {
    id("java-library")
    alias(oldLibs.plugins.versions)
}
// end::do-this[]

repositories {
    mavenCentral()
}

// tag::do-this[]
dependencies {
    api(oldLibs.bundles.groovy)
    testImplementation(oldLibs.junit.jupiter)
    implementation(oldLibs.commons.lang3)
}
// end::do-this[]
