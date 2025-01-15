import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import com.vanniktech.maven.publish.SonatypeHost
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.JavadocJar
import org.gradle.internal.os.OperatingSystem
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.dokka)
    alias(libs.plugins.mavenPublish)
}

kotlin {
    val currentOs = OperatingSystem.current()
    val strictBuild: Boolean by project.extra {
        (project.findProperty("build.strictPlatform") as? String)?.toBooleanStrictOrNull() ?: false
    }

    if (!strictBuild || currentOs.isLinux) {
        androidTarget {
            publishLibraryVariants("release")
            compilations.all {
                compileTaskProvider.configure {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_11)
                    }
                }
            }
        }
        jvm("desktop")

        js(IR) {
            browser()
            useCommonJs()
            generateTypeScriptDefinitions()
        }
        @OptIn(ExperimentalWasmDsl::class)
        wasmJs()
    }
    if (!strictBuild || currentOs.isMacOsX) {
        iosX64()
        iosArm64()
        iosSimulatorArm64()
        macosX64()
        macosArm64()
    }

    cocoapods {
        summary = "A helper library for defining spacing and textsizes as a multiple of some base unit (default 16dp/16sp)"
        homepage = findProperty("pom.url") as String
        version = findProperty("version") as String
        ios.deploymentTarget = "16.0"
        framework {
            baseName = "units"
            isStatic = true
        }
    }

    androidTarget {
        publishLibraryVariants("release")
    }
    
    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        desktopMain.dependencies {}
    }
}

description = "A helper library for defining spacing and textsizes as a multiple of some base unit (default 16dp/16sp)"
group = "cl.emilym.compose"

android {
    namespace = "cl.emilym.compose.units"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    coordinates("cl.emilym.compose", "units", findProperty("version") as String)

    pom {
        name.set("Units")
        description.set("A helper library for defining spacing and textsizes as a multiple of some base unit (default 16dp/16sp)")
        url.set(findProperty("pom.url") as String)
        licenses {
            license {
                name.set(findProperty("pom.license.name") as String)
                url.set(findProperty("pom.license.url") as String)
            }
        }
        developers {
            developer {
                name.set(findProperty("pom.developer.name") as String)
                email.set(findProperty("pom.developer.email") as String)
            }
        }
        scm {
            connection.set(findProperty("pom.scm.connection") as String)
            developerConnection.set(findProperty("pom.scm.developerConnection") as String)
            url.set(findProperty("pom.scm.url") as String)
        }
    }

    configure(
        KotlinMultiplatform(
            javadocJar = JavadocJar.Dokka("dokkaHtml"),
            sourcesJar = true
        )
    )

    signAllPublications()
}