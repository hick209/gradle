plugins {
    id("gradlebuild.internal.java")
}

description = "Collection of test fixtures for both unit tests and integration tests, internal use only"

sourceSets {
    main {
        // Incremental Groovy joint-compilation doesn't work with the Error Prone annotation processor
        errorprone.enabled = false
    }
}

dependencies {
    api(projects.baseServices)
    api(projects.concurrent)
    api(projects.hashing)
    api(projects.serviceLookup)
    api(projects.stdlibJavaExtensions)

    api(oldLibs.groovy)
    api(oldLibs.groovyXml)
    api(oldLibs.hamcrest)
    api(oldLibs.jsr305)
    api(oldLibs.junit)
    api(oldLibs.junit5JupiterApi)
    api(oldLibs.spock)
    api(oldLibs.spockJUnit4)

    implementation(projects.baseAsm)
    implementation(projects.buildOperations)
    implementation(projects.buildProcessServices)
    implementation(projects.functional)
    implementation(projects.native)
    implementation(projects.serialization)

    implementation(oldLibs.ant)
    implementation(oldLibs.asm)
    implementation(oldLibs.commonsCompress)
    implementation(oldLibs.commonsIo)
    implementation(oldLibs.commonsLang)
    implementation(oldLibs.guava)
    implementation(oldLibs.jsoup)
    implementation(oldLibs.kotlinCompilerEmbeddable)
    implementation(oldLibs.slf4jApi)
    implementation(oldLibs.testcontainers)
    implementation(oldLibs.dockerJavaApi)

    compileOnly(oldLibs.kotlinStdlib)

    runtimeOnly(oldLibs.groovyJson)
    runtimeOnly(oldLibs.bytebuddy)
}
