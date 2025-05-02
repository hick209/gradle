// tag::plugin[]
plugins {
    `java-library`
    alias(oldLibs.plugins.versions)
}
// end::plugin[]

dependencies {
    api(oldLibs.bundles.groovy)
}
