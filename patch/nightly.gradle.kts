// patch/nightly.gradle.kts

android {
    buildTypes {
        named("release") {
            // 1. 为 release 构建类型添加 .nightly 后缀，防止与正式版冲突
            // 最终的 application ID 将是 "me.rerere.rikkahub.nightly"
            applicationIdSuffix = ".nightly"

            // 2. 使用 debug 签名，避免在 CI 环境中需要正式签名密钥
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    // 3. 自定义 nightly 版本的 APK 输出文件名
    // 这会覆盖 app/build.gradle.kts 中已有的设置
    applicationVariants.all {
        outputs.all {
            if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
                val variantName = name
                val versionName = defaultConfig.versionName
                // 输出文件名示例: rikkahub-1.3.2-nightly-release.apk
                outputFileName = "rikkahub-${versionName}-nightly-${variantName}.apk"
            }
        }
    }
}
