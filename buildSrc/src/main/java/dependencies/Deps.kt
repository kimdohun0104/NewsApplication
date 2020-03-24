package dependencies

object Deps {
    object GradlePlugin {
        const val gradle = "com.android.tools.build:gradle:3.6.1"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    }

    object Kotlin {
        const val version = "1.3.61"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
        const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val coreKtx = "androidx.core:core-ktx:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"

        object LifeCycle {
            private const val version = "2.2.0"
            const val lifecycleKtx =  "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
        }
    }
}