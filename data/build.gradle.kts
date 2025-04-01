plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    namespace = "com.moviescatalog.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    // Hilt
    implementation(libs.hilt)
    ksp(libs.hiltCompiler)

    // Retrofit & Network
    implementation(libs.retrofit)
    implementation(libs.gsonConverter)
    implementation(libs.okhttpLogging)

    // Room (Offline Cache)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)




    // Test dependencies
    testImplementation (libs.truth)
    // JUnit for unit testing
    testImplementation(libs.junit)

    // Mockito for mocking dependencies
    testImplementation(libs.mockito.core)

    // Room testing dependencies
    testImplementation(libs.androidx.room.testing)

    // Coroutine support for testing
    testImplementation(libs.kotlinx.coroutines.test)

    // Hilt testing dependencies
    testImplementation(libs.hilt.android.testing)
    kspTest(libs.hilt.android.compiler)

    // AndroidX test libraries
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.androidx.junit.v113)


    // Internal modules
    implementation(project(":domain"))
    implementation(project(":core:util"))
    testImplementation(kotlin("test"))

}
