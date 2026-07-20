import com.vanniktech.maven.publish.Checksum
import com.vanniktech.maven.publish.DeploymentValidation

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.maven.publish)
}

repositories {
    gradlePluginPortal()
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.kotlin.stdlib)
    compileOnly(libs.paper)

    testImplementation(kotlin("test"))
    testImplementation(libs.junit)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(libs.versions.jdk.get().toInt())
}

mavenPublishing {
    publishToMavenCentral(true, DeploymentValidation.PUBLISHED)

    signAllPublications()
    checksums(Checksum.MD5, Checksum.SHA1, Checksum.SHA256, Checksum.SHA512)
    excludeSignatureChecksums(false)

    coordinates(
        "io.github.runkang10",
        "compact-mono",
        System.getenv("version") ?: error("'version' variable is missing!")
    )
    pom {
        name.set(rootProject.name)
        description.set("A Kotlin library for Paper plugins.")
        inceptionYear.set("2026")
        url.set("https://github.com/Runkang10/compact-mono")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://github.com/Runkang10/compact-mono?tab=MIT-1-ov-file")
                distribution.set("https://github.com/Runkang10/compact-mono?tab=MIT-1-ov-file")
            }
        }
        developers {
            developer {
                id.set("runkang10")
                name.set("Runkang10")
                url.set("https://github.com/Runkang10")
            }
        }
        scm {
            url.set("https://github.com/Runkang10/compact-mono")
            connection.set("scm:git:git://github.com/Runkang10/compact-mono.git")
            developerConnection.set("scm:git:ssh://git@github.com/Runkang10/compact-mono.git")
        }
    }
}