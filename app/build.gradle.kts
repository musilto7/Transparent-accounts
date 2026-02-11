plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.openapi.generator)
}

val dataPackageName = "cz.musilto5.transparentaccounts.features.accounts.data"
val openapiInputSpec = file("src/main/openapi/transparent-accounts-api.yaml")
// Generated sources only under app/build (never in project/src)
val openapiOutputDir = project.layout.buildDirectory.dir("generated/sources/openapi").get().asFile

val apiKey: String = run {
    (project.findProperty("API_KEY_ID") as String?).takeIf { !it.isNullOrBlank() }
        ?: System.getenv("API_KEY_ID").takeIf { !it.isNullOrBlank() }
        ?: run {
            val localFile = rootProject.file("local.properties")
            if (!localFile.exists()) return@run ""
            localFile.readLines()
                .firstOrNull { it.startsWith("API_KEY_ID=") }
                ?.substringAfter("API_KEY_ID=", "")
                ?.trim()
                ?.removeSurrounding("\"")
                ?: ""
        }
}

android {
    namespace = "cz.musilto5.transparentaccounts"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "cz.musilto5.transparentaccounts"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", "\"$apiKey\"")
        buildConfigField("String", "TRANSPARENT_ACCOUNTS_BASE_URL", "\"https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v3/transparentAccounts\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    sourceSets {
        getByName("main") {
            @Suppress("DEPRECATION")
            kotlin.srcDirs(openapiOutputDir)
        }
    }
}

openApiGenerate {
    generatorName.set("kotlin")
    library.set("multiplatform")
    inputSpec.set(openapiInputSpec.absolutePath)
    // Must be under build dir (app/build/generated/sources/openapi)
    outputDir.set(project.layout.buildDirectory.dir("generated/sources/openapi").get().asFile.absolutePath)
    apiPackage.set("$dataPackageName.api")
    modelPackage.set("$dataPackageName.dto")
    generateApiTests.set(false)
    generateApiDocumentation.set(false)
    generateModelTests.set(false)
    generateModelDocumentation.set(false)
    additionalProperties.set(mapOf(
        "dateLibrary" to "kotlinx-datetime",
        "sourceFolder" to "",
        "modelNameSuffix" to "Dto"
    ))
}

tasks.whenTaskAdded {
    if (name == "compileDebugKotlin" || name == "compileReleaseKotlin") {
        dependsOn("openApiGenerate")
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.io)
    implementation(libs.kotlinx.io.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)
    implementation(libs.lifecycle.viewmodel.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
