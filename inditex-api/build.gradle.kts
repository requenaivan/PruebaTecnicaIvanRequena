plugins {
    id("java")
    id("jacoco")
    kotlin("jvm") version "1.3.61"
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

subprojects {
    group = "com.inditex.mordor"
}

buildscript {
    val kotlinVersion = "1.3.61"
    val springBootVersion = "2.7.0"

    repositories {
        mavenCentral()
        maven ( url = "https://repo.spring.io/snapshot" )
        maven ( url = "https://repo.spring.io/milestone" )
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        classpath(kotlin("gradle-plugin"))
        classpath(kotlin("allopen",kotlinVersion))
        classpath(kotlin("noarg",kotlinVersion))
    }
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "jacoco")

    repositories {
        mavenCentral()
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://repo.spring.io/milestone")
    }

    java.sourceCompatibility = JavaVersion.VERSION_17

    sourceSets {
        main {
            java.srcDir("src/main/java")
        }
        test {
            java.srcDir("src/test/java")
        }
    }

    dependencies {
        testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.4.0")
        testCompileOnly("org.junit.jupiter:junit-jupiter-engine:5.4.0")
        testCompileOnly("org.junit.vintage:junit-vintage-engine:5.4.0")

        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.cloud:spring-cloud-starter-openfeign:2.2.0.RELEASE") {
            exclude("io.github.openfeign","feign-core")
            exclude("io.github.openfeign","feign-httpclient")
        }
        implementation("io.github.openfeign:feign-core:10.12")
        implementation("io.github.openfeign:feign-httpclient:10.12")

        implementation("org.springframework.boot:spring-boot-starter-jetty")
        implementation("com.google.code.gson:gson:2.10.1")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.3")
        }
    }

    configurations {
        all {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }

    tasks.getByName<Jar>("jar") {
        enabled = true
    }

    tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
        mainClass.set("com.inditex.mordor.Application")
        archiveClassifier.set("boot")
        archiveClassifier.convention("boot")
    }

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
        useJUnit()
        testLogging {
            events("passed", "skipped", "failed", "standardError")
        }

        addTestListener(object : TestListener {
            override fun beforeTest(testDescriptor: TestDescriptor) {}
            override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {}
            override fun beforeSuite(testDescriptor: TestDescriptor) {
                println("=> Running ${testDescriptor.name} ...")
            }

            override fun afterSuite(testDescriptor: TestDescriptor, testResult: TestResult) {
                val summary = mapOf(
                    "total" to testResult.testCount,
                    "passed" to testResult.successfulTestCount,
                    "failed" to testResult.failedTestCount,
                    "skipped" to testResult.skippedTestCount
                )

                println()

                if (testDescriptor.parent == null) {
                    println(">>> Overall test results: ${testResult.resultType} $summary <<<")
                } else {
                    println("=> Finished ${testDescriptor.name} $summary")
                }
            }
        })
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        useJUnit()
        testLogging {
            events("passed", "skipped", "failed", "standardError")
        }

        addTestListener(object : TestListener {
            override fun beforeTest(testDescriptor: TestDescriptor) {}
            override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {}
            override fun beforeSuite(testDescriptor: TestDescriptor) {
                println("=> Running ${testDescriptor.name} ...")
            }

            override fun afterSuite(testDescriptor: TestDescriptor, testResult: TestResult) {
                val summary = mapOf(
                    "total" to testResult.testCount,
                    "passed" to testResult.successfulTestCount,
                    "failed" to testResult.failedTestCount,
                    "skipped" to testResult.skippedTestCount
                )

                println()

                if (testDescriptor.parent == null) {
                    println(">>> Overall test results: ${testResult.resultType} $summary <<<")
                } else {
                    println("=> Finished ${testDescriptor.name} $summary")
                }
            }
        })
    }
}


task<JacocoReport>("jacocoRootReport") {
    dependsOn(subprojects.map { it.tasks.withType<Test>() })
    dependsOn(subprojects.map { it.tasks.withType<JacocoReport>() })
    additionalSourceDirs.setFrom(subprojects.map { it.the<SourceSetContainer>()["main"].allSource.srcDirs })
    sourceDirectories.setFrom(subprojects.map { it.the<SourceSetContainer>()["main"].allSource.srcDirs })
    classDirectories.setFrom(subprojects.map { it.the<SourceSetContainer>()["main"].output })
    executionData.setFrom(project.fileTree(".") { include("**/build/jacoco/test.exec") })
    reports {
        xml.required.set(true)
        html.required.set(true)
        html.outputLocation.set(file("$buildDir/reports/jacoco/test/html"))
        xml.outputLocation.set(file("$buildDir/reports/jacoco/test/jacocoTestReport.xml"))
    }
}