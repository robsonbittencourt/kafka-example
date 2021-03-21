import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

group = "com.rbittencourt"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.apache.kafka:kafka-clients:2.7.0")
    implementation("com.google.code.gson:gson:2.8.6")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.rbittencourt.kafka.MainKt"
    }
    from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
