import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"
}

kotlin {
    // This is framework used by RN iOS
    val xcf = XCFramework()
    ios {
        binaries.framework(setOf(RELEASE)) {
            baseName = "bridge"
            export(project(":shared"))
            xcf.add(this)
        }
    }
    iosSimulatorArm64()
    android {
        publishLibraryVariants("release")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":shared")) // Need to be api to be included in XCFramework
                implementation("de.voize:reakt-native-toolkit:0.2.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.adrianwitaszak.rntest"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    java {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }
}

dependencies {
    add("kspAndroid", "de.voize:reakt-native-toolkit-ksp:0.2.1")
    add("kspIosX64", "de.voize:reakt-native-toolkit-ksp:0.2.1")
    add("kspIosArm64", "de.voize:reakt-native-toolkit-ksp:0.2.1")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
