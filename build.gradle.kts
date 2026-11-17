plugins {
    id("org.jetbrains.intellij.platform") version "2.1.0"
}

group = "dev.thejoltjoker"
version = "1.0.0"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2024.3.1")
        instrumentationTools()
    }
}

intellijPlatform {
    pluginConfiguration {
        name = "Bearded Monokai Terra"
        id = "dev.thejoltjoker.bearded-monokai-terra"
        description = "A dark Bearded Monokai Terra UI theme and editor color scheme for JetBrains IDEs."
    }

    pluginVerification {
        ides {
            ide("IC", "2024.3.1")
        }
    }

    publishing {
        token = providers.environmentVariable("JETBRAINS_MARKETPLACE_TOKEN")
    }

    signing {
        certificateChain = providers.environmentVariable("CERTIFICATE_CHAIN")
        privateKey = providers.environmentVariable("PRIVATE_KEY")
        password = providers.environmentVariable("PRIVATE_KEY_PASSWORD")
    }
}

tasks {
    patchPluginXml {
        sinceBuild = "243"
    }

    buildSearchableOptions {
        enabled = false
    }
}
