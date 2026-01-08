package com.lucatm11.connectionlimiter.utils.configurations;

import com.lucatm11.connectionlimiter.ConnectionLimiter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Settings {
    private final File settingsFile;
    private final ConnectionLimiter plugin;
    YamlConfiguration settings;

    public Settings(ConnectionLimiter plugin) {
        this.plugin = plugin;
        settingsFile = new File(plugin.getDataFolder(), "settings.yml");
    }

    public void load() {
        if(!settingsFile.exists()) {
            plugin.saveResource("settings.yml", true);
        }

        settings = YamlConfiguration.loadConfiguration(settingsFile);
    }

    public YamlConfiguration getConfig() {
        return settings;
    }
}
