# Bearded Monokai Terra for JetBrains IDEs

[Bearded Monokai Terra](https://github.com/BeardedBear/bearded-theme) is a dark
theme for IntelliJ-based IDEs. This repository packages the Terra variant as a
standalone JetBrains Marketplace plugin with a matching editor color scheme.

## Supported IDEs

The plugin uses the IntelliJ Platform theme API and supports JetBrains IDEs
built on IntelliJ Platform 2024.3 or newer. This includes IntelliJ IDEA,
WebStorm, PyCharm, CLion, GoLand, RubyMine, Rider, and other compatible IDEs
whose platform version is 2024.3+.

Compatibility is declared by the plugin's `since-build`; it has no upper
compatibility limit.

## Installation

### From the JetBrains Marketplace

1. Open **Settings/Preferences > Plugins**.
2. Open the **Marketplace** tab.
3. Search for **Bearded Monokai Terra**.
4. Install the plugin and restart the IDE.
5. Open **Settings/Preferences > Appearance & Behavior > Appearance** and select
   **Bearded Monokai Terra**.
6. Optionally select **Bearded Monokai Terra** under
   **Editor > Color Scheme**.

### Local installation

```bash
./gradlew buildPlugin
```

The installable ZIP is created at
`build/distributions/jetbrains-bearded-monokai-terra-1.0.0.zip`.

In the IDE, open **Settings/Preferences > Plugins**, click the gear icon, choose
**Install Plugin from Disk...**, and select the generated ZIP.

## Development

Prerequisites: **JDK 21** (not 25+) and internet access to resolve Gradle
dependencies. Gradle 8.10’s Kotlin DSL cannot parse Java 25/27 version strings,
so builds fail with a bare `25.0.x` / `27.0.x` error on those JDKs. The project
pins the Gradle daemon to JDK 21 via `gradle/gradle-daemon-jvm.properties`.

```bash
brew install openjdk@21
# make keg-only Homebrew JDK 21 visible to Gradle (no sudo):
mkdir -p ~/Library/Java/JavaVirtualMachines
ln -sfn "$(brew --prefix openjdk@21)/libexec/openjdk.jdk" \
  ~/Library/Java/JavaVirtualMachines/openjdk-21.jdk

./gradlew runIde
```

This starts a test IDE with the theme installed.

Theme resources are focused in these files:

- `src/main/resources/bearded-monokai-terra.theme.json` — UI colors and control
  mappings.
- `src/main/resources/colorSchemes/Bearded Monokai Terra.xml` — editor syntax
  colors.
- `src/main/resources/META-INF/plugin.xml` — plugin registration and metadata.
- `src/main/resources/pluginIcon.svg` — Marketplace/plugin icon.

After changing the theme JSON or color scheme, run `./gradlew clean buildPlugin`
and start `runIde` to review the result. Keep the UI and editor palette
consistent: `262329` is the Terra base, `B0A2A6` is the primary accent, and the
Monokai accent colors are defined in the theme JSON.

## Validation and build

```bash
./gradlew buildPlugin
./gradlew verifyPlugin
```

- `buildPlugin` creates the distributable ZIP.
- `verifyPlugin` validates plugin compatibility and structure.
- `runIde` launches a test IDE.

The GitHub Actions workflow runs `buildPlugin` and `verifyPlugin` for pushes and
pull requests. It does not publish the plugin or require Marketplace credentials.

## Versioning and release

1. Update `version = "x.y.z"` in `build.gradle.kts`.
2. Update the plugin description/change notes in
   `src/main/resources/META-INF/plugin.xml` if needed.
3. Run `./gradlew clean buildPlugin verifyPlugin`.
4. Install the ZIP locally and test the UI and editor scheme.
5. Commit and tag the release:

   ```bash
   git tag vx.y.z
   git push origin vx.y.z
   ```

6. Publish through the JetBrains Marketplace using your maintainer account.
   Supply credentials only through environment variables or your CI secret store:

   ```bash
   export JETBRAINS_MARKETPLACE_TOKEN='<marketplace token>'
   ./gradlew publishPlugin
   ```

Plugin signing is recommended for Marketplace distribution. Before enabling
publishing with signing, create a JetBrains certificate and provide
`CERTIFICATE_CHAIN`, `PRIVATE_KEY`, and `PRIVATE_KEY_PASSWORD` as environment
variables or CI secrets; do not add them to this repository.

Publishing is intentionally not triggered automatically. Review the ZIP and
test it before running `publishPlugin`. The version in `build.gradle.kts` must
match the Marketplace release and the Git tag.

## License and attribution

This repository adapts theme material from
[BeardedBear/bearded-theme](https://github.com/BeardedBear/bearded-theme). The
upstream project identifies its license as GPL-3.0 in its repository and license
file.

Because the original theme is GPL-3.0, this adapted plugin repository is also
distributed under GPL-3.0. The complete license is in `LICENSE`, and upstream
attribution is recorded in `NOTICE`.

No artwork or assets were copied from a separate package. The JetBrains plugin
icon is an original, simple SVG created for this plugin. If maintainers add
assets, they must verify the asset's own license and notice requirements before
committing or publishing it.