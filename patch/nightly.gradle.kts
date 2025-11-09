// patch/nightly.gradle.kts

val epochStart = 1762550400000L
val millisInDay = 86400000L
val daysSinceEpoch = ((System.currentTimeMillis() - epochStart) / millisInDay).toInt()
val nightlyVersionCode = if (daysSinceEpoch > 0) daysSinceEpoch + 1 else 1

android.apply {
    defaultConfig {
        versionName = "nightly"
        versionCode = nightlyVersionCode
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
