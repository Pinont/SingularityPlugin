# SingularityPlugin
![](https://img.shields.io/badge/paperapi_version-26.2-green) ![](https://img.shields.io/badge/jdk-25-blue)

A starter template for Minecraft plugin development using **SingularityLib**. Designed for
**Paper 26.2+** (and Folia), bootstrap plugin model — copy this repo to kick off a new plugin.

## Features
- Easy setup for new plugins
- Uses SingularityLib for extended functionality
- Configurable via `paper-plugin.yml`

## Requirements
- Paper 26.2+ / Folia 26.x, **JDK 25**
- **SingularityLib** installed on the server (it's a bootstrap dependency, not bundled here)

## Installation
1. Build the plugin:
   ```bash
   mvn clean package        # requires JDK 25
   ```
2. Place `target/singularityplugin-*.jar` into your server's `plugins/` folder.
3. Make sure `SingularityLib` is in `plugins/` too (download from
   [central.sonatype.com/artifact/io.github.pinont/singularitylib](https://central.sonatype.com/artifact/io.github.pinont/singularitylib)
   or add the repo to your own project).

## Example dependency (add to your own plugin's pom)
```xml
<dependency>
    <groupId>io.github.pinont</groupId>
    <artifactId>singularitylib</artifactId>
    <version>2.0.0</version>
    <scope>provided</scope>   <!-- never bundle the lib -->
</dependency>
```

## License
This project is licensed under the MIT License. See the LICENSE file for details.