import gradlebuild.basics.bundleGroovyMajor

/**
 * This project provides the "platform" for the Gradle distribution.
 * We want the versions that are packaged in the distribution to be used everywhere (e.g. in all test scenarios)
 * Hence, we lock the versions down here for all other subprojects.
 *
 * Note:
 * We use strictly here because we do not have any better means to do this at the moment.
 * Ideally we wound be able to say "lock down all the versions of the dependencies resolved for the distribution"
 */
plugins {
    id("gradlebuild.platform")
}

description = "Provides a platform dependency to align all distribution versions"

val antVersion = "1.10.15"
// Don't forget to bump versions in
// subprojects/base-services/src/main/java/org/gradle/internal/classanalysis/AsmConstants.java
// when upgrading ASM.
val asmVersion = "9.7.1"
val awsS3Version = "1.12.780"
val bouncycastleVersion = "1.78.1"
val hamcrestVersion = "3.0"
val jacksonVersion = "2.16.1"
val jaxbVersion = "3.0.0"
val junit5Version = "5.12.2"
val mavenVersion = "3.9.5"
val mavenResolverVersion = "1.9.16" // Should remain in-sync with `mavenVersion`
val nativePlatformVersion = "0.22-milestone-28"
/**
 * Should be in sync with:
 * tooling API-related docs & snippets
 * buildship: CreateBuildshipEclipseProjectTask#calculateManifest
 * StaticLoggerProvider#REQUESTED_API_VERSION
*/
val slf4jVersion = "2.0.17"
val spockVersion = when (bundleGroovyMajor) {
    4 -> "2.3-groovy-4.0"
    else -> error("Unsupported Groovy major version: $bundleGroovyMajor")
}
val tomljVersion = "1.0.0"

// test only
val archunitVersion = "1.2.0"
val bytebuddyVersion = "1.17.5"
val jettyVersion = "9.4.36.v20210114"
val sshdVersion = "2.12.1"

// For the junit-bom
javaPlatform.allowDependencies()

dependencies {
    api(platform("org.junit:junit-bom:${junit5Version}!!"))

    constraints {
        api(oldLibs.ansiControlSequenceUtil) { version { strictly("0.3") }}
        api(oldLibs.ant)                   { version { strictly(antVersion) }}
        api(oldLibs.antLauncher)           { version { strictly(antVersion) }}
        api(oldLibs.asm)                   { version { strictly(asmVersion) }}
        api(oldLibs.asmAnalysis)           { version { strictly(asmVersion) }}
        api(oldLibs.asmCommons)            { version { strictly(asmVersion) }}
        api(oldLibs.asmTree)               { version { strictly(asmVersion) }}
        api(oldLibs.asmUtil)               { version { strictly(asmVersion) }}
        api(oldLibs.assertj)               { version { strictly("3.23.1") }}
        api(oldLibs.awsS3Core)             { version { strictly(awsS3Version) }}
        api(oldLibs.awsS3Kms)              { version { strictly(awsS3Version) }}
        api(oldLibs.awsS3S3)               { version { strictly(awsS3Version) }}
        api(oldLibs.awsS3Sts)              { version { strictly(awsS3Version) }}
        api(oldLibs.bouncycastlePgp)       { version { strictly(bouncycastleVersion) }}
        api(oldLibs.bouncycastlePkix)      { version { strictly(bouncycastleVersion) }}
        api(oldLibs.bouncycastleProvider)  { version { strictly(bouncycastleVersion) }}
        api(oldLibs.bsh)                   { version { strictly("2.0b6") }}
        api(oldLibs.commonsCodec)          { version { strictly("1.16.1") } }
        api(oldLibs.commonsCompress)       { version { strictly("1.26.1") } }
        api(oldLibs.commonsHttpclient)     { version { strictly("4.5.14") } }
        api(oldLibs.commonsIo)             { version { strictly("2.15.1") }}
        api(oldLibs.commonsLang)           { version { strictly("3.17.0") }}
        api(oldLibs.commonsMath)           { version { strictly("3.6.1") }}
        api(oldLibs.eclipseSisuPlexus)     { version { strictly("0.3.5"); because("transitive dependency of Maven modules to process POM metadata") }}
        api(oldLibs.errorProneAnnotations) { version { strictly("2.36.0") } } // don't forget to upgrade errorprone in gradlebuild.code-quality.gradle.kts
        api(oldLibs.fastutil)              { version { strictly("8.5.2") }}
        api(oldLibs.gradleFileEvents)      { version { strictly("0.2.7") }}
        api(oldLibs.gradleProfiler)        { version { strictly("0.23.0-alpha-1") }}
        api(oldLibs.develocityTestAnnotation) { version { strictly("2.0.1") }}
        api(oldLibs.gcs)                   { version { strictly("v1-rev20220705-1.32.1") }}
        api(oldLibs.googleApiClient)       { version { strictly("1.34.0"); because("our GCS version requires 1.34.0") }}
        api(oldLibs.guava)                 { version { strictly("33.4.6-jre"); because("our Google API Client version requires at least 31.1-jre, 33.4.5 removes usage of Unsafe")  }}
        api(oldLibs.googleHttpClientGson)  { version { strictly("1.42.2"); because("our Google API Client version requires 1.42.2")  }}
        api(oldLibs.googleHttpClientApacheV2) { version { strictly("1.42.2"); because("our Google API Client version requires 1.42.2")  }}
        api(oldLibs.googleHttpClient)      { version { strictly("1.42.2"); because("our Google API Client version requires 1.42.2") }}
        api(oldLibs.googleOauthClient)     { version { strictly("1.34.1"); because("our Google API Client version requires 1.34.1") }}
        api(oldLibs.groovy)                { version { strictly(oldLibs.groovyVersion) }}
        api(oldLibs.groovyAnt)             { version { strictly(oldLibs.groovyVersion) }}
        api(oldLibs.groovyAstbuilder)      { version { strictly(oldLibs.groovyVersion) }}
        api(oldLibs.groovyConsole)         { version { strictly(oldLibs.groovyVersion) }}
        api(oldLibs.groovySql)             { version { strictly(oldLibs.groovyVersion) }}
        api(oldLibs.groovyDatetime)        { version { strictly(oldLibs.groovyVersion) }}
        api(oldLibs.groovyDateUtil)        { version { strictly(oldLibs.groovyVersion) }}
        api(oldLibs.groovyNio)             { version { strictly(oldLibs.groovyVersion) }}
        api(oldLibs.groovyDoc)             { version { strictly(oldLibs.groovyVersion) }}
        api(oldLibs.groovyJson)            { version { strictly(oldLibs.groovyVersion) }}
        api(oldLibs.groovyTemplates)       { version { strictly(oldLibs.groovyVersion) }}
        api(oldLibs.groovyTest)            { version { strictly(oldLibs.groovyVersion) }}
        api(oldLibs.groovyXml)             { version { strictly(oldLibs.groovyVersion) }}
        api(oldLibs.gson)                  { version { strictly("2.13.0") }}
        api(oldLibs.h2Database)            { version { strictly("2.2.220") }}
        api(oldLibs.hamcrest)              { version { strictly(hamcrestVersion) }}
        api("org.hamcrest:hamcrest-core") { version { strictly(hamcrestVersion) }}
        api(oldLibs.hikariCP)              { version { strictly("4.0.3"); because("5.x requires Java 11+") }}
        api(oldLibs.httpcore)              { version { strictly("4.4.14") }}
        api(oldLibs.inject)                { version { strictly("1") }}
        api(oldLibs.ivy)                   { version { strictly("2.5.3") }}
        api(oldLibs.jacksonAnnotations)    { version { strictly(jacksonVersion) }}
        api(oldLibs.jacksonCore)           { version { strictly(jacksonVersion) }}
        api(oldLibs.jacksonDatabind)       { version { strictly(jacksonVersion) }}
        api(oldLibs.jacksonDatatypeJdk8)   { version { strictly(jacksonVersion) }}
        api(oldLibs.jacksonDatatypeJsr310) { version { strictly(jacksonVersion) }}
        api(oldLibs.jacksonKotlin)         { version { strictly(jacksonVersion) }}
        api(oldLibs.jakartaActivation)     { version { strictly("2.0.1") }}
        api(oldLibs.jakartaXmlBind)        { version { strictly("3.0.0") }}
        api(oldLibs.jansi)                 { version { strictly("1.18"); because("2.x changes the API") }}
        api(oldLibs.jatl)                  { version { strictly("0.2.3") }}
        api(oldLibs.javaPoet)              { version { strictly("1.13.0") } }
        api(oldLibs.jaxbCore)              { version { strictly(jaxbVersion) }}
        api(oldLibs.jaxbImpl)              { version { strictly(jaxbVersion) }}
        api(oldLibs.jcifs)                 { version { strictly("1.3.17") }}
        api(oldLibs.jclToSlf4j)            { version { strictly(slf4jVersion) }}
        api(oldLibs.jcommander)            { version { strictly("1.78") }}
        api(oldLibs.jetbrainsAnnotations)  { version { strictly("24.0.1") }}
        api(oldLibs.jgit)                  { version { strictly("5.13.3.202401111512-r"); because("6.x requires Java 11") }}
        api(oldLibs.jgitSsh)               { version { strictly("5.13.3.202401111512-r") }}
        api(oldLibs.joda)                  { version { strictly("2.14.0"); because("Only pulled in by AWS SDK") }}
        api(oldLibs.joptSimple)            { version { strictly("5.0.4"); because("needed to create profiler in Gradle profiler API") }}
        api(oldLibs.jsch)                  { version { strictly("0.2.16") }}
        api(oldLibs.jsoup)                 { version { strictly("1.15.3") }}
        api(oldLibs.jsr305)                { version { strictly("3.0.2") }}
        api(oldLibs.jspecify)              { version { strictly("1.0.0") }}
        api(oldLibs.julToSlf4j)            { version { strictly(slf4jVersion) }}
        api(oldLibs.junit)                 { version { strictly("4.13.2") }}
        api(oldLibs.junitJupiter)          { version { strictly(junit5Version) }}
        api(oldLibs.junit5JupiterApi)      { version { strictly(junit5Version) }}
        api(oldLibs.junit5Vintage)         { version { strictly(junit5Version) }}
        api(oldLibs.junitPlatform)         { version { strictly("1.12.2") }}
        api(oldLibs.junitPlatformEngine)   { version { strictly("1.12.2") }}
        api(oldLibs.jzlib)                 { version { strictly("1.1.3") }}
        api(oldLibs.kryo)                  { version { strictly("2.24.0") }}
        api(oldLibs.log4jToSlf4j)          { version { strictly(slf4jVersion) }}
        api(oldLibs.maven3Artifact)        { version { strictly(mavenVersion); because("transitive dependency of Maven modules to process POM metadata") }}
        api(oldLibs.maven3Core)            { version { strictly(mavenVersion); because("transitive dependency of Maven modules to process POM metadata") }}
        api(oldLibs.maven3BuilderSupport)  { version { strictly(mavenVersion); because("required to load/build poms and repository settings") }}
        api(oldLibs.maven3Model)           { version { strictly(mavenVersion); because("required to load/build poms and repository settings") }}
        api(oldLibs.maven3RepositoryMetadata) { version { strictly(mavenVersion); because("required to load/build poms and repository settings") }}
        api(oldLibs.maven3Settings)        { version { strictly(mavenVersion); because("required to load/build poms and repository settings") }}
        api(oldLibs.maven3SettingsBuilder) { version { strictly(mavenVersion); because("required to load/build poms and repository settings") }}
        api(oldLibs.minlog)                { version { strictly("1.2") }}
        api(oldLibs.nativePlatform)        { version { strictly(nativePlatformVersion) }}
        api(oldLibs.objenesis)             { version { strictly("2.6") }}
        api(oldLibs.plexusCipher)          { version { strictly("2.0"); because("transitive dependency of Maven modules to process POM metadata") }}
        api(oldLibs.plexusInterpolation)   { version { strictly("1.26"); because("transitive dependency of Maven modules to process POM metadata") }}
        api(oldLibs.plexusClassworlds)     { version { strictly("2.7.0"); because("transitive dependency of Maven modules to process POM metadata") }}
        api(oldLibs.plexusSecDispatcher)   { version { strictly("2.0"); because("transitive dependency of Maven modules to process POM metadata") }}
        api(oldLibs.plexusUtils)           { version { strictly("3.5.1"); because("transitive dependency of Maven modules to process POM metadata") }}
        api(oldLibs.plist)                 { version { strictly("1.27") }}
        api(oldLibs.servletApi)            { version { strictly("3.1.0") }}
        api(oldLibs.slf4jApi)              { version { strictly(slf4jVersion) }}
        api(oldLibs.slf4jSimple)           { version { rejectAll(); because("We only need the logging API, we supply our own binding, which cause duplicate binding on class path error") }}
        api(oldLibs.snakeyaml)             { version { strictly("2.0") }}
        api(oldLibs.testng)                { version { strictly("6.3.1"); because("later versions break test cross-version test filtering") }}
        api(oldLibs.tomlj)                 { version { strictly(tomljVersion) }}
        api(oldLibs.trove4j)               { version { strictly("1.0.20200330") }}
        api(oldLibs.jna)                   { version { strictly("5.12.1") }}
        // TODO upgrade this AGP version to recent version
        api(oldLibs.agp)                   { version { strictly("3.0.0"); because("We use 3.0.0 for internal performance test") }}
        api(oldLibs.xbeanReflect)          { version { strictly("3.18") }}
        api(oldLibs.xmlApis)               { version { strictly("1.4.01"); because("2.0.x has a POM with relocation Gradle does not handle well") }}

        // compile only
        api(oldLibs.maven3Compat)          { version { strictly(mavenVersion); because("required for maven2gradle in init plugin") }}
        api(oldLibs.maven3PluginApi)       { version { strictly(mavenVersion); because("required for maven2gradle in init plugin") }}
        api(oldLibs.zinc)                  { version { strictly("1.10.4") } }

        // test only
        api(oldLibs.aircompressor)         { version { strictly("0.27") }}
        api(oldLibs.archunit)              { version { strictly(archunitVersion) }}
        api(oldLibs.archunitJunit5)        { version { strictly(archunitVersion) }}
        api(oldLibs.archunitJunit5Api)     { version { strictly(archunitVersion) }}
        api(oldLibs.awaitility)            { version { strictly("3.1.6") }}
        api(oldLibs.bytebuddy)             { version { strictly(bytebuddyVersion) }}
        api(oldLibs.bytebuddyAgent)        { version { strictly(bytebuddyVersion) }}
        api(oldLibs.cglib)                 { version { strictly("3.2.6") }}
        api(oldLibs.compileTesting)        { version { strictly("0.21.0")}}
        api(oldLibs.dockerJavaApi)         { version { strictly("3.4.0")}}
        api(oldLibs.equalsverifier)        { version { strictly("2.1.6") }}
        api(oldLibs.guice)                 { version { strictly("5.1.0") }}
        api(oldLibs.httpmime)              { version { strictly("4.5.10") }}
        api(oldLibs.javaParser)            { version { strictly("3.17.0") }}
        api(oldLibs.jetty)                 { version { strictly(jettyVersion) }}
        api(oldLibs.jettySecurity)         { version { strictly(jettyVersion) }}
        api(oldLibs.jettyServer)           { version { strictly(jettyVersion) }}
        api(oldLibs.jettyServlet)          { version { strictly(jettyVersion) }}
        api(oldLibs.jettyUtil)             { version { strictly(jettyVersion) }}
        api(oldLibs.jettyWebApp)           { version { strictly(jettyVersion) }}
        api(oldLibs.jtar)                  { version { strictly("2.3") }}
        api(oldLibs.kotlinCoroutines)      { version { strictly("1.5.2") }}
        api(oldLibs.kotlinCoroutinesDebug) { version { strictly("1.5.2") }}
        api(oldLibs.kotlinJvmAbiGenEmbeddable) { version { strictly(oldLibs.kotlinVersion) }}
        api(oldLibs.kotlinxSerializationCore)   { version { strictly("1.6.2") }}
        api(oldLibs.kotlinxSerializationJson)   { version { strictly("1.6.2") }}
        api(oldLibs.littleproxy)           { version { strictly("2.0.5") }}
        api(oldLibs.maven3ResolverProvider){ version { strictly(mavenVersion) }}
        api(oldLibs.mavenResolverApi)              { version { strictly(mavenResolverVersion) }}
        api(oldLibs.mavenResolverConnectorBasic)   { version { strictly(mavenResolverVersion) }}
        api(oldLibs.mavenResolverImpl)             { version { strictly(mavenResolverVersion) }}
        api(oldLibs.mavenResolverSupplier)         { version { strictly(mavenResolverVersion) }}
        api(oldLibs.mavenResolverTransportFile)    { version { strictly(mavenResolverVersion) }}
        api(oldLibs.mavenResolverTransportHttp)    { version { strictly(mavenResolverVersion) }}
        api(oldLibs.mina)                  { version { strictly("2.0.17") }}
        api(oldLibs.mockitoCore)           { version { strictly("5.17.0") }}
        api(oldLibs.mockitoKotlin)         { version { strictly("4.1.0") }}
        api(oldLibs.mockwebserver)         { version { strictly("4.12.0") }}
        api(oldLibs.mySqlConnector)        { version { strictly("9.2.0") }}
        api(oldLibs.netty)                 { version { strictly("4.1.63.Final") }}
        api(oldLibs.opentest4j)            { version { strictly("1.3.0") }}
        api(oldLibs.samplesCheck)          { version { strictly("1.0.3") }}
        api(oldLibs.samplesDiscovery)      { version { strictly("1.0.3") }}
        api(oldLibs.snappy)                { version { strictly("0.5") }}
        api(oldLibs.socksProxy)            { version { strictly("2.0.0") }}
        api(oldLibs.spock)                 { version { strictly(spockVersion) }}
        api(oldLibs.spockJUnit4)           { version { strictly(spockVersion) }}
        api(oldLibs.sshdCore)              { version { strictly(sshdVersion) }}
        api(oldLibs.sshdOsgi)              { version { rejectAll(); because("It contains sshd-core and sshd-common classes") }}
        api(oldLibs.sshdScp)               { version { strictly(sshdVersion) }}
        api(oldLibs.sshdSftp)              { version { strictly(sshdVersion) }}
        api(oldLibs.testcontainers)        { version { strictly("1.20.4") }}
        api(oldLibs.testcontainersSpock)   { version { strictly("1.20.4") }}
        api(oldLibs.typesafeConfig)        { version { strictly("1.3.3") }}
        api(oldLibs.xerces)                { version { strictly("2.12.0") }}
        api(oldLibs.xmlunit)               { version { strictly("1.6") }}
    }
}
