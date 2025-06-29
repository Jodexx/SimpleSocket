plugins {
    java
    `java-library`
    `maven-publish`
}

group = "com.jodexindustries.simplesocket"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    api("io.netty:netty-all:4.2.2.Final")
    api("org.jetbrains:annotations:26.0.2")
}

val targetJavaVersion = 8
java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = project.group.toString()
            artifactId = "simplesocket"
            version = project.version.toString()
        }
    }

    repositories {
        maven {
            url = uri("https://repo.jodex.xyz/releases")

            credentials {
                username = findProperty("repoUser") as String? ?: System.getenv("REPO_USER")
                password = findProperty("repoPassword") as String? ?: System.getenv("REPO_PASSWORD")
            }
        }
    }
}