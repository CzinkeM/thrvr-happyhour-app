// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.5.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
    kotlin("kapt") version "2.0.0"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}