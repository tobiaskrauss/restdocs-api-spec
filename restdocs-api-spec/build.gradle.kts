import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    signing
}
repositories {
    mavenCentral()
}

val jacksonVersion: String by extra
val springBootVersion: String by extra
val springRestDocsVersion: String by extra
val junitVersion: String by extra

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.springframework.restdocs:spring-restdocs-core:$springRestDocsVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion") {
        exclude("junit")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.junit-pioneer:junit-pioneer:0.2.2")
    testImplementation("org.springframework.boot:spring-boot-starter-hateoas:$springBootVersion")
    testImplementation("org.hibernate.validator:hibernate-validator:6.0.10.Final")
    testImplementation("org.assertj:assertj-core:3.10.0")
    testImplementation("com.jayway.jsonpath:json-path:2.3.0")

    testImplementation("com.github.java-json-tools:json-schema-validator:2.2.10")
    testImplementation("com.github.erosb:everit-json-schema:1.11.0")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("REST Doc API Spec")
                description.set("Adds API specification support to Spring REST Docs ")
                url.set("https://github.com/ePages-de/restdocs-api-spec")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/ePages-de/restdocs-api-spec/blob/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("ePages")
                        name.set("ePages Devs")
                        email.set("info@epages.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/ePages-de/restdocs-api-spec.git")
                    developerConnection.set("scm:git:ssh://github.com/ePages-de/restdocs-api-spec.git")
                    url.set("https://github.com/ePages-de/restdocs-api-spec")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

java {
    withJavadocJar()
    withSourcesJar()
}
