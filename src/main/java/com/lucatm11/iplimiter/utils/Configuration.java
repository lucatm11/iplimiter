package com.lucatm11.iplimiter.utils;

import org.bukkit.configuration.file.FileConfiguration;

public class Configuration {
    public class Messages {
        public final String noPermission;
        public final String tooManyConnections;
        public final String offlinePlayer;
        public final String checkConnections;
        public final String help;
        public final String reloaded;
        public final String serverReloadKick;

        public Messages(FileConfiguration config) {
            noPermission = Utils.colorize(config.getString("messages.no-permission"));
            tooManyConnections = Utils.colorize(config.getString("messages.too-many-connections"));
            offlinePlayer = Utils.colorize(config.getString("messages.offline-player"));
            checkConnections = Utils.colorize(config.getString("messages.checkconnections"));
            help = Utils.colorize(config.getString("messages.help"));
            reloaded = Utils.colorize(config.getString("messages.reloaded"));
            serverReloadKick = Utils.colorize(config.getString("messages.server-reload-kick"));
        }
    }

    public class Config {
        public final Integer maxIpsAllowed;
        public final Boolean kickAllPlayersReload;

        public Config(FileConfiguration config) {
            maxIpsAllowed = config.getInt("max-ips-allowed");
            kickAllPlayersReload = config.getBoolean("kick-all-players-reload");
        }
    }
}
