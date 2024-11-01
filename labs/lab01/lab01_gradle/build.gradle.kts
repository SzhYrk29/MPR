plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.apache.pdfbox:pdfbox:2.0.29")
    implementation("commons-io:commons-io:2.14.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Delete>("deleteFiles"){
    description = "Deletes pdf and jpg files."
    delete("download.pdf", "capybara.jpg" ,"updated_pdf.pdf")
}