plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt") // Or id("org.jetbrains.kotlin.kapt")
}

android {
    // Assuming androidCompileSdk, androidMinSdk, androidTargetSdk are in libs.versions.toml
    compileSdk = libs
        .versions
        .compileSdkVersion
        .get()
        .toInt()

    namespace = "com.google.ar.sceneform.samples.environmentlights"

    buildFeatures {
        dataBinding = true // In Kotlin DSL, direct assignment
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.google.ar.sceneform.samples.environmentlights"

        minSdk = libs
            .versions
            .minimumSdkVersion
            .get()
            .toInt()
        targetSdk = libs
            .versions
            .targetSdkVersion
            .get()
            .toInt()
        versionCode = 2
        versionName = "1.0.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    // This was already in a format compatible with Kotlin DSL
    androidResources {
        noCompress.addAll(listOf("filamat", "ktx")) // Using addAll for clarity with a list
    }
}


dependencies {
    // Assuming these aliases are defined in your libs.versions.toml
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.google.android.material)


    implementation(project(":sceneform"))

    // Ensures Kapt uses the correct Kotlin version for annotation processing
    kapt(libs.kotlin.compiler.embeddable)
}

