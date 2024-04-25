plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "ir.dunijet.wikipedia_m3"
    compileSdk  = 34

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "ir.dunijet.wikipedia_m3"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // my libs :
    implementation(libs.glide)
    annotationProcessor(libs.compiler)
    implementation(libs.glide.transformations)
    implementation(libs.library)
    implementation("com.github.delight-im:Android-AdvancedWebView:v3.2.1")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("androidx.core:core-splashscreen:1.0.0-alpha02")

    // implementation("com.github.EudyContreras:Indicator-Effect-View:v1.1.0")
}