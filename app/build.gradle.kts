plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.shopper"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.shopper"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


        testInstrumentationRunner = ("androidx.test.runner.AndroidJUnitRunner")

        vectorDrawables {
            useSupportLibrary = true

        }
    }

    buildTypes {
//        debug{
//            buildConfigField("String", "SERVER", "https://github.com/ChingisB/Shop/tree/main/database")
//        }

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
    buildFeatures {
        viewBinding = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt{
        correctErrorTypes = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.core:core-ktx:1.12.0")
//    implementation("androidx.legacy:legacy-support-v4:1.0.0")
//    implementation("androidx.recyclerview:recyclerview:1.3.2")

    //Splash Api
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.test:monitor:1.6.1")


//    Navigation component
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")


    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    val coroutines_version = "1.8.0"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")


    val lifecycle_version = "2.7.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")
    kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")


    //Room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:2.6.1")


    val dagger_version = "2.51"
    implementation("com.google.dagger:hilt-android:$dagger_version")
    kapt("com.google.dagger:hilt-android-compiler:$dagger_version")
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")


    //Glider
    val glider_version = "4.16.0"
    implementation("com.github.bumptech.glide:glide:$glider_version")
    kapt("com.github.bumptech.glide:compiler:$glider_version")


    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")


//    // GMS - Google Mobile Services
//    implementation("com.google.android.gms:play-services-location:21.2.0")
//
//    // Permissions
//    implementation("com.google.accompanist:accompanist-permissions:0.31.1-alpha")
//
//
//    //Datastore
//    implementation("androidx.datastore:datastore-preferences:1.0.0")
//
//    //Paging 3
//    val paging_version = "3.2.1"
//    implementation("androidx.paging:paging-runtime:$paging_version")


}