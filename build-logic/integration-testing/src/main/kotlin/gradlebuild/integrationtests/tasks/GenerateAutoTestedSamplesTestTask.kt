/*
 * Copyright 2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gradlebuild.integrationtests.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.internal.file.FileOperations
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.support.uppercaseFirstChar
import java.util.Locale
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * This task scans the main source set and findd samples in javadoc with `class='autoTested'`,
 * then generates a subclass of AbstractAutoTestedSamplesTest and adds it to the integTest.
 */
@CacheableTask
abstract class GenerateAutoTestedSamplesTestTask @Inject constructor(@Internal val fileOperations: FileOperations) : DefaultTask() {
    private
    val sampleStart = Pattern.compile("""<pre class=['"]autoTested(.*?)['"].*?>""")

    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val mainSources: ConfigurableFileCollection

    @get:Input
    abstract val testClassName: Property<String>

    @get:Input
    abstract val generateAutoTestedSamplesTest: Property<Boolean>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty // = project.layout.buildDirectory.dir("generated/sources/autoTested/groovy")

    init {
        outputDir.convention(project.layout.buildDirectory.dir("generated/sources/autoTested/groovy"))
        testClassName.convention("${project.name.kebabCaseToPascalCase()}AutoTestedSamplesTest")
    }

    @TaskAction
    fun generate() {
        if (generateAutoTestedSamplesTest.get() && hasTestableSnippets()) {
            generateTestFile()
        }
    }

    private
    fun CharSequence.kebabCaseToPascalCase() =
        replace("-[a-z]".toRegex()) { it.value.drop(1).uppercase(Locale.US) }.uppercaseFirstChar()

    private fun generateTestFile() {
        fileOperations.delete(outputDir.get().asFile)
        val testFile = outputDir.file("org/gradle/samples/${testClassName.get()}.groovy").get().asFile

        fileOperations.mkdir(testFile.parentFile)
        testFile.writeText(
            """
            package org.gradle.samples
            import org.gradle.integtests.fixtures.AbstractAutoTestedSamplesTest
            import org.junit.Test
            import org.gradle.testdistribution.LocalOnly

            @LocalOnly
            class ${testClassName.get()} extends AbstractAutoTestedSamplesTest {
                @Test
                void runSamples() {
                    runSamplesFrom("src/main")
                }
            }
            """.trimIndent()
        )
    }

    private
    fun hasTestableSnippets(): Boolean {
        var hasTestableSnippets = false
        mainSources.asFileTree.matching {
            include("**/*.java")
            include("**/*.groovy")
        }.visit {
            if (!isDirectory && sampleStart.matcher(file.readText()).find()) {
                hasTestableSnippets = true
                stopVisiting()
            }
        }
        return hasTestableSnippets
    }
}
