import com.vanniktech.dependency.graph.generator.DependencyGraphGeneratorExtension
import com.vanniktech.dependency.graph.generator.DependencyGraphGeneratorPlugin
import guru.nidi.graphviz.attribute.Color
import guru.nidi.graphviz.attribute.Style
import org.gradle.kotlin.dsl.configure
import kotlin.jvm.java

plugins.apply(DependencyGraphGeneratorPlugin::class.java)

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.androidx.room) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.secrets.gradle.plugin) apply false
    alias(libs.plugins.graph.generator) apply false
}

configure<DependencyGraphGeneratorExtension> {
    generators.create("jetbrainsLibraries") {
        include = { dependency -> dependency.moduleGroup.startsWith("org.jetbrains") }
        children = { false }
        dependencyNode = { node, dependency -> node.add(Style.FILLED, Color.rgb("#AF1DF5")) }
    }
}

