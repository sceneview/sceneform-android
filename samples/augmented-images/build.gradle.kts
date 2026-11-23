plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    // Assuming androidCompileSdk, androidMinSdk, androidTargetSdk are in libs.versions.toml
    compileSdk = libs
        .versions
        .compileSdkVersion
        .get()
        .toInt()

    namespace = "com.google.ar.sceneform.samples.augmentedimages"

    defaultConfig {
        applicationId = "com.google.ar.sceneform.samples.augmentedimages"

        minSdk = libs
            .versions
            .minimumSdkVersion
            .get()
            .toInt()
        targetSdk = libs
            .versions
            .targetSdkVersion
            .get()
            .toInt() // targetSdk is used in defaultConfig for app modules
        versionCode = 1
        versionName = "1.0.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    // For AGP 7.0+ (aaptOptions is deprecated)
    androidResources {
        noCompress.addAll(listOf("filamat", "ktx"))
    }
    // If on older AGP (e.g. < 7.0) and the above doesn't work, you might need:
    // aaptOptions {
    //    noCompress("filamat", "ktx") // Syntax for older Kotlin DSL might vary
    // }
}


dependencies {
    // Assuming filamentVersion, appcompatVersion, fragmentKtxVersion, materialVersion, and sceneformVersion
    // are defined in libs.versions.toml and appropriate aliases exist.

    implementation(libs.filament.filamat.android) // Example alias

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.google.android.material) // Example alias


    implementation(project(":sceneform"))
}
