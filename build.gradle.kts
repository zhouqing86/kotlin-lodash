plugins {
    kotlin("multiplatform") version "2.0.21"
    id("org.jetbrains.dokka") version "1.9.20"
    id("com.vanniktech.maven.publish") version "0.29.0"
}

group = project.property("GROUP") as String
version = providers.fileContents(layout.projectDirectory.file("version.txt"))
    .asText
    .get()
    .trim()

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    js(IR) { browser() }
    wasmJs { browser() }
    linuxX64()
    mingwX64()
    macosX64()
    macosArm64()
    iosArm64()
    iosSimulatorArm64()
    iosX64()

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit5"))
                implementation("org.junit.jupiter:junit-jupiter:5.11.0")
            }
        }
    }

    tasks.dokkaHtml {
        outputDirectory.set(file("$buildDir/dokka"))
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
