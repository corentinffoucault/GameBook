plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.guava)
    implementation("com.google.code.gson:gson:2.10.1")
    implementation ("org.apache.logging.log4j:log4j-core:2.25.1")
    implementation("org.apache.logging.log4j:log4j-api:2.25.1")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnit("4.13.2")
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(24)
    }
}

application {
    mainClass = "com.reader.adventure.Main"
}
