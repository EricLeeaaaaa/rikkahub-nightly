// patch/nightly.gradle.kts

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import kotlin.math.max

val epochStart = 1762550400000L // 2025-11-08 00:00:00 Asia/Shanghai
val millisInDay = 86400000L
val nightlyVersionCode = max(1, ((System.currentTimeMillis() - epochStart) / millisInDay).toInt() + 1)

val timestamp = SimpleDateFormat("yyyyMMdd-HHmm", java.util.Locale.US).apply {
    timeZone = TimeZone.getTimeZone("UTC")
}.format(Date())

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
                outputFileName = "rikkahub-nightly-${timestamp}.apk"
            }
        }
    }
}
