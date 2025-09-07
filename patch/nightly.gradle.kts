// patch/nightly.gradle.kts

android.apply {
    defaultConfig {
        versionCode = 1
        versionName = "nightly"
    }

    buildTypes.configureEach {
        if (name == "release") {
            applicationIdSuffix = ".nightly"
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    applicationVariants.all {
        outputs.all {
            this as com.android.build.gradle.internal.api.ApkVariantOutputImpl
            if (name == "release") {
                outputFileName = "rikkahub-nightly.apk"
            }
        }
    }
}
