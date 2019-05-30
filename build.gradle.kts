plugins {
    // https://github.com/spring-gradle-plugins/dependency-management-plugin
    id("io.spring.dependency-management") version "1.0.7.RELEASE"

    // https://docs.spring.io/spring-boot/docs/2.1.x/gradle-plugin/reference/html/
    id("org.springframework.boot") version "2.1.5.RELEASE"

    // https://github.com/n0mer/gradle-git-properties
    id("com.gorylenko.gradle-git-properties") version "2.0.0"

    // https://github.com/diffplug/spotless/tree/master/plugin-gradle
    id("com.diffplug.gradle.spotless") version "3.23.0"

    // https://github.com/ben-manes/gradle-versions-plugin
    id("com.github.ben-manes.versions") version "0.21.0"

    java
    idea
}

repositories {
    mavenCentral()
    jcenter()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

configurations {
    all {
        exclude("junit", "junit")
    }
}

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("com.google.guava:guava:27.1-jre")

    testCompile("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testCompile("io.projectreactor:reactor-test")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}

spotless {
    java {
        googleJavaFormat("1.7")
    }
}

tasks.compileJava {
    dependsOn("processResources", "spotlessJavaApply")
    options.encoding = "UTF-8"
}

tasks.test {
    failFast = false
    enableAssertions = true
    useJUnitPlatform()

    testLogging {
        events("PASSED", "STARTED", "FAILED", "SKIPPED")
        // Set to true if you want to see output from tests
        showStandardStreams = false
        setExceptionFormat("FULL")
    }

    systemProperty("io.netty.leakDetectionLevel", "paranoid")
}

springBoot {
    // https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-build-info
    buildInfo()
}
tasks.bootRun {
    jvmArgs = listOf("-Dspring.output.ansi.enabled=ALWAYS")
}

defaultTasks("build")