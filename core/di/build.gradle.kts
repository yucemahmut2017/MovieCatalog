plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    namespace = "com.moviescatalog.core.di"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
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
    // Hilt (Dependency Injection)
    implementation(libs.hilt)
    ksp(libs.hiltCompiler)

    // Retrofit + Gson for Networking
    implementation(libs.retrofit)
    implementation(libs.gsonConverter)

    // OkHttp Logging for HTTP logs
    implementation(libs.okhttpLogging)

    // Room (for providing DAOs or Database instance)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Modules
    implementation(project(":core:util"))
    implementation(project(":data"))
    implementation(project(":domain"))
    testImplementation(kotlin("test"))
}
