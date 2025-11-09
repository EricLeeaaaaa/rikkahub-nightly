// patch/nightly.gradle.kts

val nightlyVersionCode = (System.currentTimeMillis() / 60000L).toInt()

android.apply {
    defaultConfig {
        versionCode = nightlyVersionCode
        versionName = "nightly-${nightlyVersionCode}"
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
                outputFileName = "rikkahub-nightly-${nightlyVersionCode}.apk"
            }
        }
    }
}
