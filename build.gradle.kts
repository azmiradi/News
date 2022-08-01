buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(uri("https://jitpack.io"))
    }
    dependencies {
        classpath(Deps.androidGradle)
        classpath(Deps.jetbrainsKotlin)
       classpath(Deps.Navigation.navigationPlugin)
       classpath(Deps.Hilt.hiltPlugin)

    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(uri("https://jitpack.io"))

    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}