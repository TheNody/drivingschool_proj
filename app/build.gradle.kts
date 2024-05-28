plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.googleAndroidLibrariesMapsplatformSecretsGradlePlugin)
    kotlin("plugin.serialization") version "1.9.0"
}

android {
    namespace = "com.example.drivingschool76"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.drivingschool76"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.appcompat)
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.0")
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.7")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.generativeai)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.play.services.maps)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Supabase API
    implementation(platform("io.github.jan-tennert.supabase:bom:2.3.0-rc-1"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.4.1:")
    implementation("io.github.jan-tennert.supabase:realtime-kt:2.2.3")
    implementation("io.ktor:ktor-client-android:2.3.10")
    implementation("io.github.jan-tennert.supabase:storage-kt:2.4.1")

    //Yandex MapKit

    //Модуль для карт
    implementation("com.yandex.android:maps.mobile:4.6.1-full")

    //Google Maps API
    implementation("com.google.maps.android:android-maps-utils:3.8.2")

    //Coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    //Accompanist
    implementation("com.google.accompanist:accompanist-pager:0.35.0-alpha")

    //For Time
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

}