plugins {
    id("org.springframework.boot")
}

dependencies {
    // Project Dependencies: 모든 모듈을 조립한다.
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation(project(":adapter:incoming"))
    implementation(project(":adapter:outgoing"))

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}
