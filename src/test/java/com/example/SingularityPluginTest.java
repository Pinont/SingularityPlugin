package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;

/**
 * Smoke tests: template plugin boots correctly under a mocked Paper 26.2 server
 * (bootstrap model — depends on SingularityLib for its plugin base class).
 */
public class SingularityPluginTest {

    private SingularityPlugin plugin;

    @BeforeEach
    public void setUp() {
        MockBukkit.mock();
        this.plugin = MockBukkit.load(SingularityPlugin.class);
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    @DisplayName("Template loads and is enabled")
    public void loads() {
        Assertions.assertNotNull(plugin, "plugin should load under MockBukkit");
        Assertions.assertTrue(plugin.isEnabled(), "plugin should be enabled");
    }

    @Test
    @DisplayName("Template extends CorePlugin (bootstrap model)")
    public void extendsCorePlugin() {
        Assertions.assertTrue(
                com.github.pinont.singularitylib.plugin.CorePlugin.class.isAssignableFrom(plugin.getClass()),
                "template should be a CorePlugin");
    }

    @Test
    @DisplayName("Template writes & loads config.yml on first boot (CorePlugin lifecycle)")
    public void configCreated() {
        // CorePlugin.onEnable creates config.yml with a default 'debug' key on first load
        java.io.File configFile = new java.io.File(plugin.getDataFolder(), "config.yml");
        Assertions.assertTrue(configFile.exists(), "config.yml created on enable");
        var cfg = plugin.getConfig();
        Assertions.assertNotNull(cfg, "config loaded");
        Assertions.assertFalse(cfg.getBoolean("debug", true), "debug defaults to false");
    }

    @Test
    @DisplayName("Template registers itself in the lib PluginRegistry")
    public void registersInPluginRegistry() {
        Assertions.assertTrue(
                com.github.pinont.singularitylib.api.registry.PluginRegistry.find("SingularityPlugin").isPresent(),
                "template discovered via lib registry");
    }
}