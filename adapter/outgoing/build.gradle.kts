dependencies {
    // Project Dependencies
    implementation(project(":application"))

    // Spring
    implementation("org.springframework:spring-context")

    // JPA, MySQL, Redis, Kafka는 처음 사용하는 기능을 만들 때 추가한다.
    // (지금 추가하면 DB 없이 서버가 뜨지 않는다.)

    // Testing
    testImplementation(testFixtures(project(":domain")))
}
