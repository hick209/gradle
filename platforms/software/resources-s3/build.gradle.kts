plugins {
    id("gradlebuild.distribution.api-java")
}

description = "Implementation for interacting with S3 repositories"

dependencies {
    api(projects.serviceProvider)
    api(projects.core)
    api(projects.coreApi)
    api(projects.resources)
    api(projects.resourcesHttp)

    api(oldLibs.awsS3Core)
    api(oldLibs.awsS3S3)
    api(oldLibs.awsS3Kms) {
        because("Loaded by the AWS libraries with reflection when present")
    }
    api(oldLibs.awsS3Sts) {
        because("Loaded by the AWS libraries with reflection when present: https://github.com/gradle/gradle/issues/15332")
    }
    api(oldLibs.guava)

    implementation(projects.baseServices)
    implementation(projects.hashing)

    implementation(oldLibs.commonsLang)
    implementation(oldLibs.slf4jApi)

    testImplementation(testFixtures(projects.core))
    testImplementation(testFixtures(projects.dependencyManagement))
    testImplementation(testFixtures(projects.ivy))
    testImplementation(testFixtures(projects.maven))

    integTestImplementation(projects.logging)
    integTestImplementation(oldLibs.commonsIo)
    integTestImplementation(oldLibs.groovyXml)
    integTestImplementation(oldLibs.littleproxy)
    integTestImplementation(oldLibs.jetty)

    integTestDistributionRuntimeOnly(projects.distributionsBasics)
}


dependencyAnalysis {
    issues {
        onUnusedDependencies() {
            // This need to exist to be loaded reflectively
            exclude(oldLibs.awsS3Kms)
            exclude(oldLibs.awsS3Sts)
        }
    }
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
