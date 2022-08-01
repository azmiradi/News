import org.jetbrains.kotlin.storage.CacheResetOnProcessCanceled.enabled

plugins {
    id(Plugins.androidApp)
    id(Plugins.jetbrainsKotlin)
    id(Plugins.hilt)
    id(Plugins.navigation)
    kotlin("kapt")
}

android {

    compileSdk = Versions.compileSDK

    defaultConfig {
        applicationId = "com.azmiradi.news"
        minSdk = Versions.minSDK
        targetSdk = Versions.targetSDK
        versionCode = Versions.appVersionCode
        versionName = Versions.appVersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        buildConfigField("String", "NEWS_KEY", "\"c045de1344e24899bffd2bb6d9c08bd5\"")
        buildConfigField("String", "BASE_URL", "\"https://newsapi.org/\"")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        dataBinding = true
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
    implementation(Deps.Core.androidxCore)
    implementation(Deps.Core.androidCompat)

    implementation(Deps.MaterialDesign.material)
    implementation(Deps.MaterialDesign.constraintLayout)
    implementation(Deps.MaterialDesign.spotDialog)
    implementation(Deps.MaterialDesign.slider)

    implementation(Deps.ViewModel.viewModel)
    implementation(Deps.ViewModel.liveData)
    implementation(Deps.ViewModel.savedState)
     implementation(Deps.Room.runtime)
    kapt(Deps.Room.compiler)
    implementation(Deps.Room.extensions)

    implementation(Deps.Coroutines.core)
    implementation(Deps.Coroutines.android)

    implementation(Deps.Hilt.android)
    kapt(Deps.Hilt.compiler)

    implementation(Deps.Retrofit.core)
    implementation(Deps.Retrofit.convertJson)

    implementation(Deps.OkHttp.core)
    implementation(Deps.OkHttp.interceptor)

    implementation(Deps.Glide.core)
    kapt(Deps.Glide.compiler)

    implementation(Deps.Intuit.sdp)
    implementation(Deps.Intuit.ssp)

    implementation(Deps.Navigation.fragment)
    implementation(Deps.Navigation.ui)
    implementation(Deps.Navigation.features)

    //testImplementation
    testImplementation(Deps.Test.core)
    testImplementation(Deps.Test.junit)
    testImplementation(Deps.Coroutines.test)
    testImplementation(Deps.Test.truth)
    testImplementation(Deps.Test.extJunit)
    testImplementation(Deps.Test.robolectric)
    testImplementation(Deps.OkHttp.test)

    //androidTestImplementation
    androidTestImplementation(Deps.Test.core)
    androidTestImplementation(Deps.Test.junit)
    androidTestImplementation(Deps.Coroutines.test)
    androidTestImplementation(Deps.Test.truth)
    androidTestImplementation(Deps.Test.extJunit)
    androidTestImplementation(Deps.Test.espresso)
}