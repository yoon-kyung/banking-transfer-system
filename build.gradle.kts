import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    java
    id("org.springframework.boot") version "3.5.5" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
    id("com.diffplug.spotless") version "7.2.1" apply false
}

allprojects {
    group = "com.banking"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "com.diffplug.spotless")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    // Spring Boot BOM: 라이브러리 버전을 Spring Boot 3.5.5에 맞춰 자동으로 지정
    configure<DependencyManagementExtension> {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.5.5")
        }
    }

    // Code Quality: 컴파일 전에 palantir 스타일로 자동 정리
    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        java {
            target("src/**/*.java")
            palantirJavaFormat()
            removeUnusedImports()
            trimTrailingWhitespace()
            endWithNewline()
        }
    }

    tasks.named("compileJava") {
        dependsOn("spotlessApply")
    }
    tasks.named("compileTestJava") {
        dependsOn("spotlessApply")
    }

    dependencies {
        // Testing: JUnit5 + AssertJ + Mockito (spring-boot-starter-test에 모두 포함)
        "testImplementation"("org.springframework.boot:spring-boot-starter-test")
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.compilerArgs.add("-parameters")
    }

    val testSourceSet = the<SourceSetContainer>()["test"]
    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        testClassesDirs = testSourceSet.output.classesDirs
        classpath = testSourceSet.runtimeClasspath
        jvmArgs("-XX:+EnableDynamicAgentLoading")
    }

    // Test Configuration: 기본 test는 끄고 파일명 접미사로 나눈 태스크를 사용
    tasks.named<Test>("test") {
        enabled = false
    }

    val unitTest =
        tasks.register<Test>("unitTest") {
            group = "verification"
            description = "Runs unit tests (excludes *RepositoryTest, *IntegrationTest)."
            exclude("**/*RepositoryTest.*")
            exclude("**/*IntegrationTest.*")
        }

    tasks.register<Test>("repositoryTest") {
        group = "verification"
        description = "Runs repository tests (*RepositoryTest)."
        include("**/*RepositoryTest.*")
    }

    tasks.register<Test>("integrationTest") {
        group = "verification"
        description = "Runs integration tests (*IntegrationTest)."
        include("**/*IntegrationTest.*")
    }

    tasks.named("check") {
        dependsOn(unitTest)
    }
}
