import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.fir.resolve.calls.tower.TowerScopeLevel
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.myweatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myweatherapp"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"



        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        android.buildFeatures.buildConfig = true
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val p = Properties()
            p.load(project.rootProject.file("local.properties").reader())
            val yourKey: String = p.getProperty("API_KEY")
            buildConfigField("String", "API_KEY", "\"$yourKey\"")
        }
        debug {
            val p = Properties()
            p.load(project.rootProject.file("local.properties").reader())
            val yourKey: String = p.getProperty("API_KEY")
            buildConfigField("String", "API_KEY", "\"$yourKey\"")
        }
    }

    buildFeatures{
        viewBinding= true
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
    implementation(libs.car.ui.lib)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation (libs.android.lottie)

    // GSON converter
    implementation (libs.converter.gson)
    // retrofit
    implementation (libs.retrofit)

}