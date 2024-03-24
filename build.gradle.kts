plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation ("org.apache.kafka:kafka-clients:3.6.0")
}

application {
    mainClass.set("org.example.KafkaProducerHello")
}

tasks.test {
    useJUnitPlatform()
}