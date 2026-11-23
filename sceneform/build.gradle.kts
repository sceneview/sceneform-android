plugins {
    id("com.android.library")
    kotlin("android")
    id("com.vanniktech.maven.publish")
}

val GROUP: String by rootProject.extra
val VERSION_NAME: String by rootProject.extra

group = GROUP
version = VERSION_NAME

android {
    /**
     * compileSdkVersion specifies the Android API level Gradle should use to
     * compile your app. This means your app can use the API features included in
     * this API level and lower.
     */
    compileSdk = libs
        .versions
        .compileSdkVersion
        .get()
        .toInt()

    namespace = "com.google.ar.sceneform.sceneform"

    defaultConfig {
        /**
         * Defines the minimum API level required to run the app.
         */
        minSdk = libs
            .versions
            .minimumSdkVersion
            .get()
            .toInt()
        /**
         * Specifies the API level used to test the app.
         */
        targetSdk = libs
            .versions
            .targetSdkVersion
            .get()
            .toInt()
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

    // 1.23 shipped a BuildConfig class in every module.
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(project(":core"))
    api(project(":ux"))
}

