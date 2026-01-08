package com.lucatm11.connectionlimiter.utils.configurations;

import com.lucatm11.connectionlimiter.ConnectionLimiter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Messages {
    private final File messagesFile;
    private final ConnectionLimiter plugin;
    YamlConfiguration messages;

    public Messages(ConnectionLimiter plugin) {
        this.plugin = plugin;
        messagesFile = new File(plugin.getDataFolder(), "messages.yml");
    }

    public void load() {
        if(!messagesFile.exists()) {
            plugin.saveResource("messages.yml", true);
        }

        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public YamlConfiguration getConfig() {
        return messages;
    }
}
