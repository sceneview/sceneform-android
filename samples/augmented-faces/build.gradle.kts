plugins {
    id("com.android.application")
    kotlin("android") // Add if you have Kotlin code in src/main/kotlin or src/test/kotlin
}

android {
    // Assuming androidCompileSdk, androidMinSdk, androidTargetSdk are in libs.versions.toml
    compileSdk = libs
        .versions
        .compileSdkVersion
        .get()
        .toInt()

    namespace = "com.google.ar.sceneform.samples.augmentedfaces"

    defaultConfig {
        applicationId = "com.google.ar.sceneform.samples.augmentedfaces"

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
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // For AGP 7.0+ (aaptOptions is deprecated)
    androidResources {
        noCompress.addAll(listOf("filamat", "ktx"))
    }
    // If on older AGP (e.g. < 7.0), you might need the old aaptOptions block:
    // aaptOptions {
    //    noCompress("filamat", "ktx") // Syntax for older Kotlin DSL might vary
    // }

    // If you have kotlinOptions to set (like jvmTarget, if not already handled by AGP defaults based on compileOptions)
    // kotlinOptions {
    //     jvmTarget = JavaVersion.VERSION_17.toString()
    // }
}

dependencies {
    // Assuming these aliases are defined in your libs.versions.toml
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment) // Ensure this is the correct one (ktx or non-ktx)

    // Using alias from libs.versions.toml
    // Alternatively, if not using version catalog for this specific dependency:

    implementation(project(":sceneform"))
}
