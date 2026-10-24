val springDocMvcVersion: String by project

dependencies {
    // Project Dependencies
    implementation(project(":application"))

    // Spring Web
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // API Docs (Swagger)
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocMvcVersion")

    // Testing
    testImplementation(testFixtures(project(":domain")))
}
