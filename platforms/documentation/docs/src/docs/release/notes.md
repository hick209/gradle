<meta property="og:image" content="https://gradle.org/images/releases/gradle-default.png" />
<meta property="og:type"  content="article" />
<meta property="og:title" content="Gradle @version@ Release Notes" />
<meta property="og:site_name" content="Gradle Release Notes">
<meta property="og:description" content="We are excited to announce Gradle @version@.">
<meta name="twitter:card" content="summary_large_image">
<meta name="twitter:site" content="@gradle">
<meta name="twitter:creator" content="@gradle">
<meta name="twitter:title" content="Gradle @version@ Release Notes">
<meta name="twitter:description" content="We are excited to announce Gradle @version@.">
<meta name="twitter:image" content="https://gradle.org/images/releases/gradle-default.png">

We are excited to announce Gradle @version@ (released [@releaseDate@](https://gradle.org/releases/)).

This release features [1](), [2](), ... [n](), and more.

<!-- 
Include only their name, impactful features should be called out separately below.
 [Some person](https://github.com/some-person)

 THIS LIST SHOULD BE ALPHABETIZED BY [PERSON NAME] - the docs:updateContributorsInReleaseNotes task will enforce this ordering, which is case-insensitive.
-->

We would like to thank the following community members for their contributions to this release of Gradle:

Be sure to check out the [public roadmap](https://roadmap.gradle.org) for insight into what's planned for future releases.

## Upgrade instructions

Switch your build to use Gradle @version@ by updating the [wrapper](userguide/gradle_wrapper.html) in your project:

```text
./gradlew wrapper --gradle-version=@version@ && ./gradlew wrapper
```

See the [Gradle 8.x upgrade guide](userguide/upgrading_version_8.html#changes_@baseVersion@) to learn about deprecations, breaking changes, and other considerations when upgrading to Gradle @version@.

For Java, Groovy, Kotlin, and Android compatibility, see the [full compatibility notes](userguide/compatibility.html).   

## New features and usability improvements

<!-- Do not add breaking changes or deprecations here! Add them to the upgrade guide instead. -->

<!--

================== TEMPLATE ==============================

<a name="FILL-IN-KEY-AREA"></a>
### FILL-IN-KEY-AREA improvements

<<<FILL IN CONTEXT FOR KEY AREA>>>
Example:
> The [configuration cache](userguide/configuration_cache.html) improves build performance by caching the result of
> the configuration phase. Using the configuration cache, Gradle can skip the configuration phase entirely when
> nothing that affects the build configuration has changed.

#### FILL-IN-FEATURE
> HIGHLIGHT the use case or existing problem the feature solves
> EXPLAIN how the new release addresses that problem or use case
> PROVIDE a screenshot or snippet illustrating the new feature, if applicable
> LINK to the full documentation for more details

To embed videos, use the macros below. 
You can extract the URL from YouTube by clicking the "Share" button. 
For Wistia, contact Gradle's Video Team.
@youtube(Summary,6aRM8lAYyUA?si=qeXDSX8_8hpVmH01)@
@wistia(Summary,a5izazvgit)@

================== END TEMPLATE ==========================


==========================================================
ADD RELEASE FEATURES BELOW
vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv -->


<a name="build-authoring"></a>
### Build authoring improvements

#### Introduce `AttributeContainer#addAllLater`

This release introduces a new API on `AttributeContainer` allowing all attributes from one attribute container to be lazily added to another.

Consider the following example demonstrating the new API's behavior:

```kotlin
val color = Attribute.of("color", String::class.java)
val shape = Attribute.of("shape", String::class.java)

val foo = configurations.create("foo").attributes
foo.attribute(color, "green")

val bar = configurations.create("bar").attributes
bar.attribute(color, "red")
bar.attribute(shape, "square")
assert(bar.getAttribute(color) == "red")    // `color` is originally red

bar.addAllLater(foo)
assert(bar.getAttribute(color) == "green")  // `color` gets overwritten
assert(bar.getAttribute(shape) == "square") // `shape` does not

foo.attribute(color, "purple")
bar.getAttribute(color) == "purple"         // addAllLater is lazy

bar.attribute(color, "orange")
assert(bar.getAttribute(color) == "orange") // `color` gets overwritten again
assert(bar.getAttribute(shape) == "square") // `shape` remains the same
```

<!-- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
ADD RELEASE FEATURES ABOVE
==========================================================

-->

### Task graph diagnostic

A new task dependency graph is available to visualize the dependencies between tasks without executing them.
You can enable it using the `--task-graph` option on the command line. For example:
```
./gradlew root r2 --task-graph
```
This prints a visual representation of the task graph for the specified tasks:
```
Tasks graph for: root r2
+--- :root (org.gradle.api.DefaultTask)
|    \\--- :middle (org.gradle.api.DefaultTask)
|         +--- :leaf1 (org.gradle.api.DefaultTask)
|         \\--- :leaf2 (org.gradle.api.DefaultTask, disabled)
\\--- :root2 (org.gradle.api.DefaultTask)
    +--- :leaf1 (org.gradle.api.DefaultTask) (*)
    |--- other build task :included:fromIncluded (org.gradle.api.DefaultTask)
    \\--- :leaf4 (org.gradle.api.DefaultTask, finalizer)
         \\--- :leaf3 (org.gradle.api.DefaultTask)
         
(*) - details omitted (listed previously)
```

This feature provides a quick overview of the task graph, helping users understand the dependencies between tasks without running them.
You can iterate by diving into a subgraph by adjusting an invocation.

This feature is incubating and may change in future releases.
Additionally, it shares a known [issue](https://github.com/gradle/gradle/issues/2517) with `--dry-run`:
Tasks from included builds may still be executed.

## Promoted features

Promoted features are features that were incubating in previous versions of Gradle but are now supported and subject to backward compatibility.
See the User Manual section on the "[Feature Lifecycle](userguide/feature_lifecycle.html)" for more information.

The following are the features that have been promoted in this Gradle release.

<!--
### Example promoted
-->

## Fixed issues

<!--
This section will be populated automatically
-->

## Known issues

Known issues are problems that were discovered post-release that are directly related to changes made in this release.

<!--
This section will be populated automatically
-->

## External contributions

We love getting contributions from the Gradle community. For information on contributing, please see [gradle.org/contribute](https://gradle.org/contribute).

## Reporting problems

If you find a problem with this release, please file a bug on [GitHub Issues](https://github.com/gradle/gradle/issues) adhering to our issue guidelines.
If you're not sure if you're encountering a bug, please use the [forum](https://discuss.gradle.org/c/help-discuss).

We hope you will build happiness with Gradle, and we look forward to your feedback via [Twitter](https://twitter.com/gradle) or on [GitHub](https://github.com/gradle).
