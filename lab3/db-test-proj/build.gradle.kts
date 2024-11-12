val exposed_version: String = "0.56.0"
val flyway_version: String = "10.21.0"
val postgres_version: String = "42.2.20"

plugins {
    kotlin("jvm") version "2.0.0"
    application
    id("org.flywaydb.flyway") version "9.16.3"
}

application {
    mainClass.set("org.example.ApplicationKt")
}

group = "org.example"
version = "1.0-SNAPSHOT"

sourceSets.main {
    java.srcDir("main/kotlin")
    resources.srcDir("main/resources")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")

    implementation("org.flywaydb:flyway-core:$flyway_version")
    implementation("org.flywaydb:flyway-database-postgresql:$flyway_version")
    implementation("org.postgresql:postgresql:$postgres_version")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

flyway {
    baselineOnMigrate = true
    locations = arrayOf("classpath:db/migration")
}
