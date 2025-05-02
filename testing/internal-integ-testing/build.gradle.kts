import gradlebuild.integrationtests.tasks.GenerateLanguageAnnotations
import java.util.Properties

plugins {
    id("gradlebuild.internal.java")
}

description = "Collection of test fixtures for integration tests, internal use only"

sourceSets {
    main {
        // Incremental Groovy joint-compilation doesn't work with the Error Prone annotation processor
        errorprone.enabled = false
    }
}

dependencies {
    api(projects.baseServices) {
        because("Part of the public API, used by spock AST transformer")
    }
    api(projects.buildCacheBase)
    api(projects.buildOperations)
    api(projects.buildOperationsTrace)
    api(projects.concurrent)
    api(projects.core)
    api(projects.coreApi)
    api(projects.dependencyManagement)
    api(projects.hashing)
    api(projects.internalTesting) {
        because("Part of the public API")
    }
    api(projects.stdlibJavaExtensions)
    api(projects.jvmServices) {
        because("Exposing jvm metadata via AvailableJavaHomes")
    }
    api(projects.logging)
    api(projects.loggingApi)
    api(projects.native)
    api(projects.persistentCache)
    api(projects.problemsApi)
    api(projects.processServices)
    api(projects.daemonProtocol)
    api(projects.serviceLookup)

    api(testFixtures(projects.core)) {
        because("HttpServer leaks PortAllocator to spock AST transformer")
    }

    api(oldLibs.gson)
    api(oldLibs.groovy)
    api(oldLibs.groovyXml)
    api(oldLibs.guava)
    api(oldLibs.hamcrest)
    api(oldLibs.jettyWebApp) {
        because("Part of the public API via HttpServer")
    }
    api(oldLibs.jansi)
    api(oldLibs.jettySecurity)
    api(oldLibs.jettyServer)
    api(oldLibs.jettyUtil)
    api(oldLibs.jgit) {
        because("Some tests require a git reportitory - see AbstractIntegrationSpec.initGitDir(")
    }
    api(oldLibs.jsr305)
    api(oldLibs.junit) {
        because("Part of the public API, used by spock AST transformer")
    }
    api(oldLibs.mavenResolverApi) {
        because("For ApiMavenResolver. API we interact with to resolve Maven graphs & artifacts")
    }
    api(oldLibs.samplesCheck) {
        exclude(module = "groovy-all")
    }
    api(oldLibs.samplesDiscovery)
    api(oldLibs.servletApi)
    api(oldLibs.slf4jApi)
    api(oldLibs.socksProxy)
    api(oldLibs.spock) {
        because("Part of the public API")
    }

    implementation(projects.baseServicesGroovy)
    implementation(projects.buildCache)
    implementation(projects.buildEvents)
    implementation(projects.buildOption)
    implementation(projects.buildProcessServices)
    implementation(projects.buildState)
    implementation(projects.classloaders)
    implementation(projects.cli)
    implementation(projects.clientServices)
    implementation(projects.daemonServices)
    implementation(projects.enterpriseLogging)
    implementation(projects.enterpriseOperations)
    implementation(projects.fileCollections)
    implementation(projects.fileTemp)
    implementation(projects.files)
    implementation(projects.gradleCli)
    implementation(projects.instrumentationAgentServices)
    implementation(projects.io)
    implementation(projects.launcher)
    implementation(projects.messaging)
    implementation(projects.modelCore)
    implementation(projects.modelReflect)
    implementation(projects.scopedPersistentCache)
    implementation(projects.serialization)
    implementation(projects.serviceProvider)
    implementation(projects.serviceRegistryBuilder)
    implementation(projects.time)
    implementation(projects.wrapperShared)

    implementation(testFixtures(projects.buildOperations))
    implementation(testFixtures(projects.buildProcessServices))

    implementation(oldLibs.ansiControlSequenceUtil)
    implementation(oldLibs.commonsCompress)
    implementation(oldLibs.commonsLang)
    implementation(oldLibs.commonsIo)
    implementation(oldLibs.groovyJson)
    implementation(oldLibs.httpcore)
    implementation(oldLibs.inject)
    implementation(oldLibs.ivy)
    implementation(oldLibs.jcifs)
    implementation(oldLibs.jetty)
    implementation(oldLibs.jettyServlet)
    implementation(oldLibs.littleproxy)
    implementation(oldLibs.mavenResolverSupplier) {
        because("For ApiMavenResolver. Wires together implementation for maven-resolver-api")
    }
    implementation(oldLibs.maven3ResolverProvider) {
        because("For ApiMavenResolver. Provides MavenRepositorySystemUtils")
    }
    implementation(oldLibs.nativePlatform)
    implementation(oldLibs.netty)
    implementation(oldLibs.opentest4j)
    // we depend on both: sshd platforms and libraries
    implementation(oldLibs.sshdCore)
    implementation(platform(oldLibs.sshdCore))
    implementation(oldLibs.sshdScp)
    implementation(platform(oldLibs.sshdScp))
    implementation(oldLibs.sshdSftp)
    implementation(platform(oldLibs.sshdSftp))

    compileOnly(projects.configurationCache) {
        because("""Fixes:
            compiler message file broken: key=compiler.misc.msg.bug arguments=11.0.21, {1}, {2}, {3}, {4}, {5}, {6}, {7}
            java.lang.AssertionError: typeSig ERROR""")
    }

    runtimeOnly(oldLibs.mavenResolverImpl) {
        because("For ApiMavenResolver. Implements maven-resolver-api")
    }
    runtimeOnly(oldLibs.mavenResolverConnectorBasic) {
        because("For ApiMavenResolver. To use resolver transporters")
    }
    runtimeOnly(oldLibs.mavenResolverTransportFile) {
        because("For ApiMavenResolver. To resolve file:// URLs")
    }
    runtimeOnly(oldLibs.mavenResolverTransportHttp) {
        because("For ApiMavenResolver. To resolve http:// URLs")
    }

    testRuntimeOnly(projects.distributionsCore) {
        because("Tests instantiate DefaultClassLoaderRegistry which requires a 'gradle-plugins.properties' through DefaultPluginModuleRegistry")
    }

    integTestDistributionRuntimeOnly(projects.distributionsCore)
}

packageCycles {
    excludePatterns.add("org/gradle/**")
}

val prepareVersionsInfo = tasks.register<PrepareVersionsInfo>("prepareVersionsInfo") {
    destFile = layout.buildDirectory.file("generated-resources/all-released-versions/all-released-versions.properties")
    versions = gradleModule.identity.releasedVersions.map {
        it.allPreviousVersions.joinToString(" ") { it.version }
    }
    mostRecent = gradleModule.identity.releasedVersions.map { it.mostRecentRelease.version }
    mostRecentSnapshot = gradleModule.identity.releasedVersions.map { it.mostRecentSnapshot.version }
}

val copyTestedVersionsInfo by tasks.registering(Copy::class) {
    from(isolated.rootProject.projectDirectory.file("gradle/dependency-management/agp-versions.properties"))
    from(isolated.rootProject.projectDirectory.file("gradle/dependency-management/kotlin-versions.properties"))
    into(layout.buildDirectory.dir("generated-resources/tested-versions"))
}

val generateLanguageAnnotations by tasks.registering(GenerateLanguageAnnotations::class) {
    classpath.from(configurations.integTestDistributionRuntimeClasspath)
    packageName = "org.gradle.integtests.fixtures"
    destDir = layout.buildDirectory.dir("generated/sources/language-annotations/groovy/main")
}

sourceSets.main {
    groovy.srcDir(generateLanguageAnnotations.flatMap { it.destDir })
    output.dir(prepareVersionsInfo.map { it.destFile.get().asFile.parentFile })
    output.dir(copyTestedVersionsInfo)
}

@CacheableTask
abstract class PrepareVersionsInfo : DefaultTask() {

    @get:OutputFile
    abstract val destFile: RegularFileProperty

    @get:Input
    abstract val mostRecent: Property<String>

    @get:Input
    abstract val versions: Property<String>

    @get:Input
    abstract val mostRecentSnapshot: Property<String>

    @TaskAction
    fun prepareVersions() {
        val properties = Properties()
        properties["mostRecent"] = mostRecent.get()
        properties["mostRecentSnapshot"] = mostRecentSnapshot.get()
        properties["versions"] = versions.get()
        gradlebuild.basics.util.ReproduciblePropertiesWriter.store(properties, destFile.get().asFile)
    }
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
