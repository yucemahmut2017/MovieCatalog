plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    namespace = "com.moviescatalog.core.di"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        buildConfigField("String", "TMDB_API_KEY", "\"7a07a6c0e2f26829c38f3aeb0793fa43\"")
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Hilt
    implementation(libs.hilt)
    ksp(libs.hiltCompiler)

    // OkHttp Logging
    implementation(libs.okhttpLogging)


    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.gsonConverter)

    // OkHttp Logging
    implementation(libs.okhttpLogging)

    implementation(project(":core:util"))
    implementation(project(":data"))
    implementation(project(":domain"))
}