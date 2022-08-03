object Plugins {
    const val kotlinParcelize = "kotlin-parcelize"
    const val pluginSerialization = "plugin.serialization"

    const val androidApp = "com.android.application"
    const val androidLib = "com.android.library"
    const val jetbrainsKotlin = "org.jetbrains.kotlin.android"

    const val hilt = "dagger.hilt.android.plugin"
    const val navigation = "androidx.navigation.safeargs"
}

object Versions {
    // Build Config
    const val minSDK = 21
    const val compileSDK = 32
    const val targetSDK = 32

    // App version
    const val appVersionCode = 1
    const val appVersionName = "1.0.0"

    // Plugins
    const val androidGradle = "7.1.2"

    //Libs
    const val kotlin = "1.6.21"
    const val viewModelVersion = "2.5.1"
    const val roomVersion = "2.4.3"
    const val coroutinesVersion = "1.6.2"
    const val hiltVersion = "2.38.1"
    const val retrofitVersion = "2.9.0"
    const val okhttpVersion = "4.9.2"
    const val glideVersion = "4.12.0"
    const val intuitVersion = "1.0.6"
    const val navVersion = "2.3.5"
    const val androidx = "1.8.0"
    const val appCompat = "1.4.2"
    const val material = "1.6.1"
    const val constraintLayout = "2.1.4"
    const val slider = "1.04"
    const val datastore="1.0.0"

    //testLib
    const val jUnit = "4.13.2"
    const val extJUnit = "1.1.3"
    const val arch = "2.1.0"
    const val espresso = "3.4.0"
    const val truth = "1.0.1"
    const val robolectric = "4.6.1"
}

object Deps {

    const val androidGradle =
        "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val jetbrainsKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"


    object Core {
        const val androidxCore = "androidx.core:core-ktx:${Versions.androidx}"
        const val androidCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    }

    object MaterialDesign {
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
         const val slider = "com.github.MoeidHeidari:banner:${Versions.slider}"

    }

    object Test {
        const val junit = "junit:junit:${Versions.jUnit}"
        const val extJunit = "androidx.test.ext:junit:${Versions.extJUnit}"
        const val core = "androidx.arch.core:core-testing:${Versions.arch}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val truth = "com.google.truth:truth:${Versions.truth}"
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    }

    object ViewModel {
        const val viewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelVersion}"
        const val liveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.viewModelVersion}"
        const val savedState =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.viewModelVersion}"
    }

    object Datastore{
      const val core= "androidx.datastore:datastore-preferences:${Versions.datastore}"
    }
    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val compiler = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val extensions = "androidx.room:room-ktx:${Versions.roomVersion}"
    }

    object Coroutines {
        const val core =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"

        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
        const val test =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"

    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
        const val hiltPlugin =
            "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"

    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val convertJson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    }

    object OkHttp {
        const val core = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"
        const val test = "com.squareup.okhttp3:mockwebserver:${Versions.okhttpVersion}"

    }

    object Glide {
        const val core = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    }

    object Intuit {
        const val sdp = "com.intuit.sdp:sdp-android:${Versions.intuitVersion}"
        const val ssp = "com.intuit.ssp:ssp-android:${Versions.intuitVersion}"
    }

    object Navigation {
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"
        const val features ="androidx.navigation:navigation-dynamic-features-fragment:${Versions.navVersion}"

        const val navigationPlugin =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}"
    }
}