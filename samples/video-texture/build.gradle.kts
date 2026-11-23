plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = libs
        .versions
        .compileSdkVersion
        .get()
        .toInt()

    namespace = "com.google.ar.sceneform.samples.videotexture"

    defaultConfig {
        applicationId = "com.google.ar.sceneform.samples.videotexture"

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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    // For AGP 7.0+ (aaptOptions is deprecated)
    androidResources {
        noCompress.addAll(listOf("filamat", "ktx"))
    }
    // If on older AGP (e.g. < 7.0), you might need:
    // aaptOptions {
    //    noCompress("filamat", "ktx")
    // }
}

dependencies {
    // Assuming these aliases are defined in your libs.versions.toml
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.google.android.material) // Example alias


    implementation(project(":sceneform"))
}

