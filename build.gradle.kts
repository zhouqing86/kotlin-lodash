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

mavenPublishing {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    pom {
        name.set(project.property("POM_NAME") as String)
        description.set(project.property("POM_DESCRIPTION") as String)
        inceptionYear.set(project.property("POM_INCEPTION_YEAR") as String)
        url.set(project.property("POM_URL") as String)

        licenses {
            license {
                name.set(project.property("POM_LICENCE_NAME") as String)
                url.set(project.property("POM_LICENCE_URL") as String)
                distribution.set(project.property("POM_LICENCE_DIST") as String)
            }
        }

        developers {
            developer {
                id.set(project.property("POM_DEVELOPER_ID") as String)
                name.set(project.property("POM_DEVELOPER_NAME") as String)
                url.set(project.property("POM_DEVELOPER_URL") as String)
            }
        }

        scm {
            url.set(project.property("POM_SCM_URL") as String)
            connection.set(project.property("POM_SCM_CONNECTION") as String)
            developerConnection.set(project.property("POM_SCM_DEV_CONNECTION") as String)
        }
    }
}
