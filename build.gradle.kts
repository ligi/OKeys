import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    application
}

group = "org.devcon"
version = "4.2-SNAPSHOT"

val kethereum_version =  "0.83.2"
repositories {
    maven("https://jitpack.io")
    mavenCentral()
}

application {
    mainClassName = "org.devcon.okeys.MainKt"
}

dependencies {

    implementation("com.squareup.okhttp3:okhttp:4.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("com.github.komputing.kethereum:keystore:$kethereum_version")
    implementation("com.github.komputing.kethereum:model:$kethereum_version")
    implementation("com.github.komputing.kethereum:crypto:$kethereum_version")
    implementation("com.github.komputing.kethereum:crypto_impl_bouncycastle:$kethereum_version")
    implementation("com.github.komputing.kethereum:extensions_transactions:$kethereum_version")
    implementation("com.github.komputing.kethereum:eip155:$kethereum_version")
    implementation("com.github.komputing.kethereum:rpc:$kethereum_version")
    implementation("com.github.komputing.kethereum:rpc_min3:$kethereum_version")
    implementation("com.github.komputing:khex:1.1.0")
}
