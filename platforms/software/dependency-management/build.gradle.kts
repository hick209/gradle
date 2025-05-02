plugins {
    id("gradlebuild.distribution.implementation-java")
}

description = """This project contains most of the dependency management logic of Gradle:
    |* the resolution engine,
    |* how to retrieve and process dependencies and their metadata,
    |* the dependency locking and verification implementations.
    |
    |DSL facing APIs are to be found in 'core-api'""".trimMargin()

errorprone {
    disabledChecks.addAll(
        "AmbiguousMethodReference", // 1 occurrences
        "ClassCanBeStatic",
        "DefaultCharset", // 3 occurrences
        "Finally", // 4 occurrences
        "IdentityHashMapUsage", // 2 occurrences
        "InlineFormatString", // 5 occurrences
        "InvalidParam", // 1 occurrences
        "MutablePublicArray", // 1 occurrences
        "NonApiType", // 3 occurrences
        "NonCanonicalType", // 3 occurrences
        "ReferenceEquality", // 10 occurrences
        "StringCharset", // 1 occurrences
        "TypeParameterShadowing", // 4 occurrences
        "TypeParameterUnusedInFormals", // 2 occurrences
        "UndefinedEquals", // 1 occurrences
        "UnusedMethod", // 34 occurrences
    )
}


dependencies {
    api(projects.baseServices)
    api(projects.buildOperations)
    api(projects.buildOption)
    api(projects.buildProcessServices)
    api(projects.classloaders)
    api(projects.concurrent)
    api(projects.core)
    api(projects.coreApi)
    api(projects.enterpriseLogging)
    api(projects.enterpriseOperations)
    api(projects.execution)
    api(projects.fileCollections)
    api(projects.fileTemp)
    api(projects.files)
    api(projects.functional)
    api(projects.hashing)
    api(projects.logging)
    api(projects.messaging)
    api(projects.modelCore)
    api(projects.modelReflect)
    api(projects.persistentCache)
    api(projects.problemsApi)
    api(projects.resources)
    api(projects.scopedPersistentCache)
    api(projects.security)
    api(projects.serialization)
    api(projects.serviceLookup)
    api(projects.serviceProvider)
    api(projects.snapshots)
    api(projects.stdlibJavaExtensions)
    api(projects.versionedCache)

    api(oldLibs.bouncycastlePgp)
    api(oldLibs.groovy)
    api(oldLibs.guava)
    api(oldLibs.inject)
    api(oldLibs.ivy)
    api(oldLibs.jspecify)
    api(oldLibs.jsr305)
    api(oldLibs.maven3Settings)
    api(oldLibs.maven3SettingsBuilder)
    api(oldLibs.slf4jApi)

    implementation(projects.fileOperations)
    implementation(projects.time)
    implementation(projects.baseAsm)
    implementation(projects.baseServicesGroovy)
    implementation(projects.loggingApi)
    implementation(projects.resourcesHttp)
    implementation(projects.serviceRegistryBuilder)

    implementation(oldLibs.asm)
    implementation(oldLibs.asmCommons)
    implementation(oldLibs.commonsIo)
    implementation(oldLibs.commonsLang)
    implementation(oldLibs.fastutil)
    implementation(oldLibs.gson)
    implementation(oldLibs.httpcore)

    testImplementation(projects.buildCachePackaging)
    testImplementation(projects.softwareDiagnostics)

    testImplementation(projects.processServices)
    testImplementation(oldLibs.asmUtil)
    testImplementation(oldLibs.commonsHttpclient)
    testImplementation(oldLibs.groovyXml)
    testImplementation(oldLibs.jsoup)

    testImplementation(testFixtures(projects.serialization))
    testImplementation(testFixtures(projects.baseServices))
    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.coreApi))
    testImplementation(testFixtures(projects.execution))
    testImplementation(testFixtures(projects.messaging))
    testImplementation(testFixtures(projects.resourcesHttp))
    testImplementation(testFixtures(projects.snapshots))
    testImplementation(testFixtures(projects.toolingApi))
    testImplementation(testFixtures(projects.versionControl))

    integTestImplementation(projects.buildOption)
    integTestImplementation(oldLibs.jansi)
    integTestImplementation(oldLibs.ansiControlSequenceUtil)
    integTestImplementation(oldLibs.groovyJson)
    integTestImplementation(oldLibs.socksProxy) {
        because("SOCKS proxy not part of internal-integ-testing api, since it has limited usefulness, so must be explicitly depended upon")
    }
    integTestImplementation(testFixtures(projects.core))
    integTestImplementation(testFixtures(projects.signing))
    integTestImplementation(testFixtures(projects.modelReflect))

    testFixturesApi(projects.baseServices) {
        because("Test fixtures export the Action class")
    }
    testFixturesApi(projects.persistentCache) {
        because("Test fixtures export the CacheAccess class")
    }

    testFixturesApi(oldLibs.jetty)
    testFixturesImplementation(projects.core)
    testFixturesImplementation(testFixtures(projects.core))
    testFixturesImplementation(testFixtures(projects.resourcesHttp))
    testFixturesImplementation(projects.coreApi)
    testFixturesImplementation(projects.messaging)
    testFixturesImplementation(projects.internalIntegTesting)
    testFixturesImplementation(oldLibs.slf4jApi)
    testFixturesImplementation(oldLibs.inject)
    testFixturesImplementation(oldLibs.groovyJson)
    testFixturesImplementation(oldLibs.guava) {
        because("Groovy compiler reflects on private field on TextUtil")
    }
    testFixturesImplementation(oldLibs.bouncycastlePgp)
    testFixturesApi(oldLibs.testcontainersSpock) {
        because("API because of Groovy compiler bug leaking internals")
    }
    testFixturesImplementation(projects.jvmServices) {
        because("Groovy compiler bug leaks internals")
    }
    testFixturesImplementation(oldLibs.jettyWebApp) {
        because("Groovy compiler bug leaks internals")
    }

    testRuntimeOnly(projects.distributionsCore) {
        because("ProjectBuilder tests load services from a Gradle distribution.")
    }
    integTestImplementation(projects.launcher) {
        because("Daemon fixtures need DaemonRegistry")
    }
    integTestDistributionRuntimeOnly(projects.distributionsJvm) {
        because("Need access to java platforms")
    }
    crossVersionTestDistributionRuntimeOnly(projects.distributionsCore)
    crossVersionTestImplementation(oldLibs.jettyWebApp)
}

packageCycles {
    excludePatterns.add("org/gradle/**")
}

testFilesCleanup.reportOnly = true

tasks.clean {
    val testFiles = layout.buildDirectory.dir("tmp/teŝt files")
    doFirst {
        // On daemon crash, read-only cache tests can leave read-only files around.
        // clean now takes care of those files as well
        testFiles.get().asFileTree.matching {
            include("**/read-only-cache/**")
        }.visit { this.file.setWritable(true) }
    }
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
