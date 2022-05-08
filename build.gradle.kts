import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    application
}

group = "com.mrx"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        name = "阿里云镜像"
        setUrl("https://maven.aliyun.com/repository/central")
    }
    mavenCentral()
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.36.0.3")
    implementation("com.alibaba:druid:1.2.9")
    implementation("org.mybatis:mybatis:3.5.9")
    implementation("org.apache.logging.log4j:log4j-api:2.17.0")
    implementation("org.apache.logging.log4j:log4j-core:2.17.2")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.17.2")
    implementation("commons-beanutils:commons-beanutils:1.9.4")

    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}