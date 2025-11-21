plugins {
    id("org.springframework.boot") version "4.0.0"
    id("io.spring.dependency-management") version "1.1.4"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.guava)

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.slf4j:slf4j-api:2.0.13")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.20")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")
    implementation("com.fasterxml.jackson.core:jackson-core:2.20.0")
    implementation("org.apache.pdfbox:pdfbox:3.0.5")
    implementation("org.mapstruct:mapstruct:1.6.3")
    implementation("org.glassfish.expressly:expressly:6.0.0")
    implementation("org.odftoolkit:odfdom-java:0.12.0") {
        exclude(group = "org.slf4j", module = "slf4j-api")
    }

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("de.redsix:pdfcompare:1.2.5")

    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
}

configurations.all {
    exclude(group = "org.slf4j", module = "slf4j-simple")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("5.13.4")
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_24
    targetCompatibility = JavaVersion.VERSION_24
    toolchain {
        languageVersion = JavaLanguageVersion.of(24)
    }
    sourceSets["main"].resources.srcDir("src/main/resources")
}

application {
    mainClass = "com.reader.adventure.Main"
}

sourceSets {
    main {
        resources {
            srcDirs("src/main/resources")
        }
    }
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
