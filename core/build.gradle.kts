plugins {
    id("com.android.library")
    kotlin("android")
    id("com.vanniktech.maven.publish")
}

val groupName = project.property("GROUP") as String?
    ?: "com.google.ar.sceneform" // Provide a default or ensure it's set
val versionValue =
    project.property("VERSION_NAME") as String? ?: "0.0.0" // Provide a default or ensure it's set

group = groupName
version = versionValue

android {
    compileSdk = libs
        .versions
        .compileSdkVersion
        .get()
        .toInt() // From libs.versions.toml

    namespace = "com.google.ar.sceneform"

    defaultConfig {
        minSdk = libs
            .versions
            .minimumSdkVersion
            .get()
            .toInt()// From libs.versions.toml
        // targetSdk for libraries is usually inherited or set in testOptions/lint
        // if (libs.versions.has("androidTargetSdk")) { // Check if defined
        //     targetSdk = libs.versions.androidTargetSdk.get().toInt() // From libs.versions.toml
        // }

        consumerProguardFiles("lib-proguard-rules.pro")

        // Accessing VERSION_NAME for buildConfigField
        // Ensure VERSION_NAME is resolvable, e.g., from gradle.properties or rootProject
        val versionNameProperty = project.property("VERSION_NAME") as String? ?: version.toString()
        buildConfigField("String", "VERSION_NAME", "\"$versionNameProperty\"")
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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    androidResources {
        noCompress.addAll(listOf("filamat", "ktx"))
    }

    buildFeatures {
        buildConfig = true
    }
}


dependencies {
    //TODO: Remove it
    implementation(files("libs/libsceneform_runtime_schemas.jar"))

    // Kotlin
    implementation(libs.kotlin.stdlib) // Example: alias in libs.versions.toml
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.jdk8)

    // Filament
    api(libs.filament.android)
    api(libs.filament.gltfio)
    api(libs.filament.utils)

    // ARCore
    api(libs.arcore)

    // AndroidX
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Fuel
    implementation(libs.fuel.core)
    implementation(libs.fuel.android)
    implementation(libs.fuel.coroutines)
}

