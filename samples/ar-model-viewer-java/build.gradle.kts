plugins {
    id("com.android.application")
    // If you intend to use Kotlin in this Java sample (even for tests or build logic),
    // you might add kotlin("android") here.
    // For a pure Java sample with a Kotlin DSL build script, it's not strictly necessary
    // for the app's source code but good practice for the build environment.
    // kotlin("android")
}

android {
    compileSdk = libs
        .versions
        .compileSdkVersion
        .get()
        .toInt()

    // Consider making this namespace unique for the Java sample
    namespace = "com.google.ar.sceneform.samples.gltfjava" // Suggested change

    defaultConfig {
        // Consider making this applicationId unique for the Java sample
        applicationId = "com.google.ar.sceneform.samples.gltfjava" // Suggested change

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
        noCompress.addAll(kotlin.collections.listOf("filamat", "ktx"))
    }
    // If on older AGP (e.g. < 7.0), you might need:
    // aaptOptions {
    //    noCompress("filamat", "ktx")
    // }
}

dependencies {
    implementation(libs.androidx.appcompat) // From libs.versions.toml
    implementation(libs.androidx.fragment)   // From libs.versions.toml (note: not fragment-ktx for a Java sample)


    implementation(project(":sceneform"))
}
