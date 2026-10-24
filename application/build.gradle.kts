dependencies {
    // Project Dependencies
    api(project(":domain"))

    // Spring: @Service, @Transactional
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")

    // Testing
    testImplementation(testFixtures(project(":domain")))
}
