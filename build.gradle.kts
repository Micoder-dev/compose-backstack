// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  ext.versions = [
      targetSdk: 29,
      compose: '0.1.0-dev07',
      kotlin: '1.3.70',
  ]

  rootProject.ext.defaultAndroidConfig = {
    compileSdkVersion versions.targetSdk
    buildToolsVersion '29.0.2'

    compileOptions {
      sourceCompatibility JavaVersion.VERSION_1_8
      targetCompatibility JavaVersion.VERSION_1_8
    }

    defaultConfig {
      minSdkVersion 21
      targetSdkVersion versions.targetSdk
      versionCode 1
      versionName "1.0"
    }

    buildFeatures {
      compose = true
    }

    composeOptions {
      kotlinCompilerExtensionVersion versions.compose
    }
  }

  ext.deps = [
      android_gradle_plugin: "com.android.tools.build:gradle:4.1.0-alpha04",

      androidx: [
          activity: "androidx.activity:activity:1.1.0",
          annotations: "androidx.annotation:annotation:1.1.0",
          appcompat: "androidx.appcompat:appcompat:1.1.0",
          constraint_layout: "androidx.constraintlayout:constraintlayout:1.1.3",
          fragment: "androidx.fragment:fragment:1.2.2",
          // Note that we're not using the actual androidx material dep yet, it's still alpha.
          material: "com.google.android.material:material:1.1.0",
          recyclerview: "androidx.recyclerview:recyclerview:1.1.0",
          // Note that we are *not* using lifecycle-viewmodel-savedstate, which at this
          // writing is still in beta and still fixing bad bugs. Probably we'll never bother to,
          // it doesn't really add value for us.
          savedstate: "androidx.savedstate:savedstate:1.0.0",
          transition: "androidx.transition:transition:1.3.1",
          viewbinding: "androidx.databinding:viewbinding:3.6.1",
      ],
      compose: [
          foundation: "androidx.ui:ui-foundation:${versions.compose}",
          icons: "androidx.ui:ui-material-icons-extended:${versions.compose}",
          layout: "androidx.ui:ui-layout:${versions.compose}",
          material: "androidx.ui:ui-material:${versions.compose}",
          test: "androidx.ui:ui-test:${versions.compose}",
          tooling: "androidx.ui:ui-tooling:${versions.compose}",
      ],
      kotlin: [
          binaryCompatibilityValidatorPlugin: "org.jetbrains.kotlinx:binary-compatibility-validator:0.2.1",
          dokka: "org.jetbrains.dokka:dokka-gradle-plugin:0.10.0",
          gradlePlugin: "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}",
          stdlib: "org.jetbrains.kotlin:kotlin-stdlib-jdk8",
          reflect: "org.jetbrains.kotlin:kotlin-reflect:${versions.kotlin}",
          test: [
              common: "org.jetbrains.kotlin:kotlin-test-common",
              annotations: "org.jetbrains.kotlin:kotlin-test-annotations-common",
              jdk: "org.jetbrains.kotlin:kotlin-test-junit",
              mockito: "com.nhaarman:mockito-kotlin-kt1.1:1.6.0"
          ]
      ],
      ktlint: "org.jlleitschuh.gradle:ktlint-gradle:9.2.0",
  ]

  repositories {
    mavenCentral()
    gradlePluginPortal()
    google()
  }

  dependencies {
    classpath deps.android_gradle_plugin
    classpath deps.kotlin.dokka
    classpath deps.kotlin.gradlePlugin
    classpath deps.ktlint
  }
}

subprojects {
  repositories {
    google()
    mavenCentral()
    jcenter()
  }

  tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).all {
    kotlinOptions {
      jvmTarget = "1.8"
    }
  }
}
