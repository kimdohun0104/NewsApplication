package dependencies

object Deps {
    const val jsoup = "org.jsoup:jsoup:1.13.1"
    const val material = "com.google.android.material:material:1.1.0"
    const val shimmer = "com.facebook.shimmer:shimmer:0.5.0"

    object GradlePlugin {
        const val gradle = "com.android.tools.build:gradle:3.6.1"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    }

    object Glide {
        private const val version = "4.11.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
    }

    object Kotlin {
        const val version = "1.3.61"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
        const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.2.0-alpha02"
        const val coreKtx = "androidx.core:core-ktx:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"

        object LifeCycle {
            private const val version = "2.2.0"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
        }

        object Koin {
            private const val version = "2.1.5"
            const val viewModel = "org.koin:koin-androidx-viewmodel:$version"
            const val ext = "org.koin:koin-androidx-ext:$version"
        }

        object Room {
            private const val version = "2.2.3"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
        }
    }

    object Test {
        const val junit = "junit:junit:4.12"
        const val mockitoCore = "org.mockito:mockito-core:2.21.0"
        const val mockitoInline = "org.mockito:mockito-inline:3.2.4"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.3"
        const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
        const val liveDataTesting = "com.jraska.livedata:testing-ktx:1.1.0"
    }
}