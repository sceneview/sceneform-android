plugins {
    id("com.android.library")
    kotlin("android")
    //id("com.vanniktech.maven.publish")
}

val groupProperty = project.findProperty("GROUP")?.toString()
    ?: "default.ux.group" // Ensure these are set or provide sensible defaults
val versionProperty = project.findProperty("VERSION_NAME")?.toString() ?: "0.0.1-SNAPSHOT"

group = groupProperty
version = versionProperty

android {
    compileSdk = libs
        .versions
        .compileSdkVersion
        .get()
        .toInt() // From libs.versions.toml

    namespace = "com.google.ar.sceneform.ux"

    defaultConfig {
        minSdk = libs
            .versions
            .minimumSdkVersion
            .get()
            .toInt() // From libs.versions.toml
        // targetSdk is generally not set directly in defaultConfig for libraries.
        // If you need it for testOptions or lint, and have androidTargetSdk in your TOML:
        // if (libs.versions.has("androidTargetSdk")) {
        //     targetSdk = libs.versions.androidTargetSdk.get().toInt()
        // }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildTypes {
        release {
            isMinifyEnabled =
                false // Groovy's `minifyEnabled false` becomes `isMinifyEnabled = false`
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    androidResources {
        noCompress.addAll(listOf("filamat", "ktx"))
    }

    // 1.23 shipped a BuildConfig class in every module.
    buildFeatures {
        buildConfig = true
    }
}


dependencies {
    api(project(":core")) // project() call needs parentheses

    // Kotlin - assuming aliases are defined in libs.versions.toml
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // AndroidX - assuming aliases are defined in libs.versions.toml
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.appcompat)
}

/*
mavenPublish {
    releaseSigningEnabled =
        project.hasProperty("signing.keyId") && project.hasProperty("signing.password")
}
*/