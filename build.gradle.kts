import com.vanniktech.maven.publish.Checksum
import com.vanniktech.maven.publish.DeploymentValidation

plugins {
    kotlin("jvm") version "2.4.0"
    kotlin("plugin.serialization") version "2.4.0"
    id("com.vanniktech.maven.publish") version "0.37.0"
}

repositories {
    gradlePluginPortal()
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.2.build.+")
    implementation("net.mamoe.yamlkt:yamlkt:0.13.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.11.0")
}

kotlin {
    jvmToolchain(25)
}

mavenPublishing {
    publishToMavenCentral(true, DeploymentValidation.PUBLISHED)

    signAllPublications()
    checksums(Checksum.MD5, Checksum.SHA1, Checksum.SHA256, Checksum.SHA512)
    excludeSignatureChecksums(false)

    coordinates("io.github.runkang10", "compact-mono", findProperty("version").toString())
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