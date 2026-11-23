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

    namespace = "com.google.ar.sceneform.samples.gltf"

    defaultConfig {
        applicationId = "com.google.ar.sceneform.samples.gltf"

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

    // For AGP 7.0+ (aaptOptions is deprecated)
    androidResources {
        noCompress.addAll(listOf("filamat", "ktx"))
    }
    // If on older AGP (e.g. < 7.0) and the above doesn't work, you might need:
    // aaptOptions {
    //    noCompress("filamat", "ktx") // Syntax for older Kotlin DSL might vary
    // }

    buildTypes {
        release {
            isMinifyEnabled = false // Groovy's `minifyEnabled false` becomes `isMinifyEnabled = false`
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(libs.kotlin.stdlib) // Example alias from libs.versions.toml
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment.ktx)

    // Material
    implementation(libs.google.android.material) // Example alias

    // Sceneform

    implementation(project(":sceneform"))
}
