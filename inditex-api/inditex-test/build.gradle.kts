dependencies {
    testImplementation(project(":inditex-app"))
    testImplementation(project(":inditex-common"))
    testImplementation(project(":inditex-client"))
    testImplementation(project(":inditex-core"))
    implementation("org.springframework.boot:spring-boot-starter") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "junit-vintage-engine")
        exclude(module = "spring-boot-starter-logging")
    }
    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.4.0")
    testCompileOnly("org.junit.jupiter:junit-jupiter-engine:5.4.0")
    testCompileOnly("org.junit.vintage:junit-vintage-engine:5.4.0")
    implementation("org.mock-server:mockserver-junit-jupiter:5.8.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}