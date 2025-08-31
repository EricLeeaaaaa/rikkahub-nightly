// patch/nightly.gradle.kts

android {
    defaultConfig {
        versionCode = 1
        versionName = "nightly"
    }
    buildTypes {
        named("release") {
            // Append .nightly to application ID
            applicationIdSuffix = ".nightly"

            // Use debug signing for CI builds
            signingConfig = signingConfigs.getByName("debug")

            // Set app display name for Nightly builds
            resValue("string", "app_name", "Rikkahub Nightly")
        }
    }

    // Customize Nightly APK output filename
    applicationVariants.all {
        outputs.all {
            if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
                val variantName = name
                outputFileName = "rikkahub-nightly-${variantName}.apk"
            }
        }
    }
}
