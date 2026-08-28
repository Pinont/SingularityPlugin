# AGENTS.md — SingularityPlugin

## What this is
SingularityPlugin is the **starter template** of the Singularity Project — a minimal PaperMC plugin showing how to consume SingularityLib. It's the "hello world" skeleton new plugin devs copy.

| Repo | Role |
|---|---|
| `SingularityLib` | Core framework library (dependency) |
| **SingularityPlugin** (this repo) | Template plugin extending `CorePlugin` |
| `Singularity-DevTool` | In-game debug tool (also built on the lib) |

## Build & run
```bash
mvn clean package          # shaded jar into target/
```
- Requires **JDK 25**, targets **Paper 26.2+** (`paper-api [26.2.build,)`, provided scope).
- Depends on `io.github.pinont:singularitylib:${singularity.version}` — releases from Maven Central, snapshots from maven.pinont.me.
- At runtime, expects the **SingularityLib jar installed as a separate server plugin**: `paper-plugin.yml` declares it as a required server dependency with `load: BEFORE` and **`join-classpath: true`** so the template can see lib classes without shading them.

## Code layout
Tiny repo:
- `src/main/java/com/example/SingularityPlugin.java` — extends `CorePlugin`; empty `onPluginStart()` / `onPluginStop()` overrides. That's the whole source.
- `src/main/resources/paper-plugin.yml` — plugin metadata + SingularityLib dependency wiring.

There is no test suite, no CI workflow file, and no additional resources (no config.yml).

## Coupling to SingularityLib (blast radius for lib rework)
The template only exercises:
- `CorePlugin` as base class with `onPluginStart`/`onPluginStop` lifecycle overrides
- `paper-plugin.yml` server-dependency declaration (`join-classpath: true`)

So the template breaks only if `CorePlugin`'s class name/package or lifecycle method signatures change. Any broader lib API change won't affect this repo directly.

## Known issues / tech debt (observed)
1. Package `com.example` — placeholder; should become something like `me.pinont.singularitytemplate` if this ships publicly.
2. `plugin.yml`-style version string `1.0-SNAPSHOT` hardcoded in `paper-plugin.yml` while pom is also `1.0-SNAPSHOT` — consider resource filtering to keep in sync (lib already does this pattern).
3. Empty lifecycle methods mean a fresh build produces a plugin that does nothing visible — fine for a template, but consider adding commented example code (a command, a menu) to demonstrate the lib.
4. No README instructions about needing SingularityLib installed on the server first (README only covers building).

## Agent guidance
- Keep this repo minimal — it's a template. Don't add real features here; prototype them in DevTool or the lib.
- If you change how SingularityLib is consumed (shaded vs join-classpath), update both `pom.xml` and `paper-plugin.yml` consistently and re-test on a live Paper 1.21+ server.
- When the lib rework lands LTS/rolling versions, this template should pin to the newest LTS line and document that choice in the README.
