// tag::do-this[]
plugins {
    id("java-library")
    alias(oldLibs.plugins.versions)
}

repositories {
    mavenCentral()
}

dependencies {
    // SLF4J
    implementation(oldLibs.slf4j.api)

    // Jackson
    implementation(oldLibs.jackson.databind)
    implementation(oldLibs.jackson.dataformatCsv)

    // Groovy bundle
    api(oldLibs.bundles.groovy)

    // Commons Lang
    implementation(oldLibs.commons.lang3)
}
// end::do-this[]
