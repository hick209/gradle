plugins {
    id("gradlebuild.internal.java")
    id("gradlebuild.performance-testing")
    id("gradlebuild.performance-templates")
}

description = "Performance tests for the Gradle build tool"

dependencies {
    performanceTestImplementation(projects.baseServices)
    performanceTestImplementation(projects.core)
    performanceTestImplementation(projects.internalTesting)
    performanceTestImplementation(projects.stdlibJavaExtensions)
    performanceTestImplementation(projects.toolingApi)

    performanceTestImplementation(testFixtures(projects.toolingApi))

    performanceTestImplementation(oldLibs.commonsLang)
    performanceTestImplementation(oldLibs.commonsIo)
    performanceTestImplementation(oldLibs.gradleProfiler)
    performanceTestImplementation(oldLibs.jettyServer)
    performanceTestImplementation(oldLibs.jettyWebApp)
    performanceTestImplementation(oldLibs.junit)
    performanceTestImplementation(oldLibs.servletApi)

    performanceTestRuntimeOnly(projects.coreApi)
    performanceTestRuntimeOnly(oldLibs.jetty)

    performanceTestDistributionRuntimeOnly(projects.distributionsFull) {
        because("All Gradle features have to be available.")
    }
    performanceTestLocalRepository(projects.toolingApi) {
        because("IDE tests use the Tooling API.")
    }
}

dependencyAnalysis {
    issues {
        onUnusedDependencies {
            exclude(oldLibs.junitJupiter)
        }

        ignoreSourceSet(sourceSets.performanceTest.name)
    }
}
