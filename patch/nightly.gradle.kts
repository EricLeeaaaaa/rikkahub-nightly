// patch/nightly.gradle.kts

android {
    defaultConfig {
        versionCode = 1
        versionName = "nightly"
    }
    buildTypes {
        named("release") {
            // Add .nightly suffix to application ID
            applicationIdSuffix = ".nightly"

            // Use debug signing for CI
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    // Customize nightly APK output filename
    applicationVariants.all {
        outputs.all {
            if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
                val variantName = name
                // Example: rikkahub-nightly-release.apk
                outputFileName = "rikkahub-nightly-${variantName}.apk"
            }
        }
    }
}
