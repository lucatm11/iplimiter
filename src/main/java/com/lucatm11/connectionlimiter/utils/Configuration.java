package com.lucatm11.connectionlimiter.utils;

import org.bukkit.configuration.file.FileConfiguration;

public class Configuration {
    public class Messages {
        public final String noPermission;
        public final String tooManyConnections;
        public final String offlinePlayer;
        public final String checkConnections;
        public final String help;
        public final String pluginReloaded;
        public final String serverReloadKick;
        public final String incorrectUsage;

        public Messages(FileConfiguration config) {
            noPermission = Utils.colorize(config.getString("messages.no-permission"));
            tooManyConnections = Utils.colorize(config.getString("messages.too-many-connections-kick"));
            offlinePlayer = Utils.colorize(config.getString("messages.offline-player"));
            checkConnections = Utils.colorize(config.getString("messages.checkconnections"));
            help = Utils.colorize(config.getString("messages.help"));
            pluginReloaded = Utils.colorize(config.getString("messages.plugin-reloaded"));
            serverReloadKick = Utils.colorize(config.getString("messages.server-reload-kick"));
            incorrectUsage= Utils.colorize(config.getString("messages.incorrect-usage"));
        }
    }

    public class Config {
        public final Integer maxConnectionsAllowedPerIp;
        public final Boolean kickAllPlayersOnServerReload;

        public Config(FileConfiguration config) {
            maxConnectionsAllowedPerIp = config.getInt("max-connections-allowed-per-ip");
            kickAllPlayersOnServerReload = config.getBoolean("kick-all-players-on-server-reload");
        }
    }
}
