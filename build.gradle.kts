plugins {
    id("java")
}

group = "edu.vlu"
version = "1.0"

repositories {
    mavenCentral()
}

tasks {
    withType<JavaCompile>().configureEach { options.encoding = "UTF-8" }
    withType<JavaExec>().configureEach { defaultCharacterEncoding = "UTF-8" }
    withType<Javadoc>().configureEach { options.encoding = "UTF-8" }
    withType<Test>().configureEach { defaultCharacterEncoding = "UTF-8" }
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(22)
    }
}
dependencies {
//    testImplementation(platform("org.junit:junit-bom:5.10.0"))
//    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.testng:testng:7.11.0")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.28.1")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.9.2")
    testImplementation("org.seleniumhq.selenium:selenium-chromium-driver:4.28.1")
    testImplementation("org.testng:reportng:1.2.2")
//    testImplementation("org.apache.poi:poi:5.4.0")
//    testImplementation("org.apache.poi:poi-ooxml:5.4.0")
}

tasks.named<Test>("test") {
    // Sử dụng TestNG cho unit testCases
    useTestNG {
        useDefaultListeners = true
        outputDirectory = file("$projectDir/TestNG_Reports")
    }
    reports {
        html.required.set(true)
        html.outputLocation.set(file("$projectDir/GradleReports"))
    }
}
tasks.test {
//    useJUnitPlatform()
    useTestNG()
}

tasks.javadoc {
    options {
        (this as StandardJavadocDocletOptions).apply {
            addStringOption("tag", "Caution:a:Caution:")
        }
    }
}