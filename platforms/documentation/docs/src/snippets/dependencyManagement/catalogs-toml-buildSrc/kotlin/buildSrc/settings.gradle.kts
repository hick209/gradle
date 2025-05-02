// Add the version catalog to buildSrc using dependencyResolutionManagement
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/oldLibs.versions.toml"))
        }
    }
}
