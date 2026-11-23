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

    namespace = "com.google.ar.sceneform.samples.sceneviewbackground"

    defaultConfig {
        applicationId = "com.google.ar.sceneform.samples.sceneviewbackground"

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
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    // aaptOptions is deprecated and its settings moved.
    // 'noCompress' is now part of androidResources in AGP 7.0+
    // or packagingOptions for older AGP versions.
    // Assuming AGP 7.0+ based on previous migrations:
    androidResources {
        noCompress.addAll(listOf("filamat", "ktx"))
    }
    // If using an older AGP version (e.g., 4.x), it would be:
    // packagingOptions {
    //     doNotStrip("*/filamat") // Example, syntax might vary
    //     doNotStrip("*/ktx")    // Example, syntax might vary
    // }
    // However, 'aaptOptions.noCompress' directly translates to 'androidResources.noCompress' in modern AGP.
}

dependencies {
    implementation(libs.androidx.appcompat) // Assuming alias in libs.versions.toml
    implementation(libs.androidx.fragment.ktx) // Assuming alias in libs.versions.toml



    implementation(project(":sceneform"))
}
