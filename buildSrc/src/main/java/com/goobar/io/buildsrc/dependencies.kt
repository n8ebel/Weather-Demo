@file:Suppress("ClassName", "unused", "MemberVisibilityCanBePrivate")

package com.goobar.io.buildsrc

object Versions {
  const val kotlin = "1.3.61"
}

object BuildConfig {
  const val compileSdk = 29
  const val minSdk = 21
  const val targetSdk = 29

  object version {
    const val major = 0
    const val minor = 0
    const val patch = 1
    val build = System.getenv("BUILD_NUMBER")?.toInt() ?: 1

    const val name = "$major.$minor.$patch"
    val code = build
  }
}

object Deps {

  const val material = "com.google.android.material:material:1.0.0"
  const val junit4 = "junit:junit:4.12"

  object groupie {
    const val version = "2.3.0"
    const val groupie = "com.xwray:groupie:$version"
    const val android = "com.xwray:groupie-kotlin-android-extensions:$version"
  }

  object koin {
    const val version = "2.1.1"
    const val gradlePlugin = "org.koin:koin-gradle-plugin:$version"

    object core {
      const val core = "org.koin:koin-core:$version"
    }

    object android {
      const val android = "org.koin:koin-android:$version"
    }

    object androidx {
      const val scope = "org.koin:koin-androidx-scope:$version"
      const val viewModel = "org.koin:koin-androidx-viewmodel:$version"
      const val fragment = "org.koin:koin-androidx-fragment:$version"
    }
  }

  object androidx {
    const val annotation = "androidx.annotation:annotation:1.1.0"
    const val appcompat = "androidx.appcompat:appcompat:1.0.2"
    const val cardview = "androidx.cardview:cardview:1.0.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    const val core = "androidx.core:core-ktx:1.0.2"
    const val recyclerview = "androidx.recyclerview:recyclerview:1.0.0"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"

    object arch {
      private const val version = "2.0.1"
      const val coreCommon = "androidx.arch.core:core-common:$version"
      const val coreRuntime = "androidx.arch.core:core-runtime:$version"
      const val coreTesting = "androidx.arch.core:core-testing:$version"
    }

    object lifecycle {
      private const val version = "2.0.0"
      const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
      const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
      const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0-alpha03"
      const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0"
    }

    object navigation {
      private const val version = "2.2.0"
      const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
      const val ui = "androidx.navigation:navigation-ui-ktx:$version"
      const val safeargsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
    }
  }

  object kotlin {
    private const val version = Versions.kotlin
    const val allopen = "org.jetbrains.kotlin:kotlin-allopen:$version"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
    const val test = "org.jetbrains.kotlin:kotlin-test:$version"

    object coroutines {
      private const val version = "1.3.0"
      const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
      const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
      const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }
  }

  object moshi {
    private const val version = "1.8.0"
    const val client = "com.squareup.moshi:moshi:$version"
    const val adapters = "com.squareup.moshi:moshi-adapters:$version"
    const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
  }

  object retrofit {
    private const val version = "2.7.2"
    const val client = "com.squareup.retrofit2:retrofit:$version"
    const val moshi = "com.squareup.retrofit2:converter-moshi:$version"
  }
}
