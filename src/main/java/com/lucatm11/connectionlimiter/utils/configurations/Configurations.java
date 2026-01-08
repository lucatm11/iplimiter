package com.lucatm11.connectionlimiter.utils.configurations;

import com.lucatm11.connectionlimiter.utils.Utils;
import org.bukkit.configuration.file.YamlConfiguration;

public class Configurations {
    public class Messages {
        public final String noPermission;
        public final String tooManyConnections;
        public final String offlinePlayer;
        public final String checkConnections;
        public final String help;
        public final String pluginReloaded;
        public final String serverReloadKick;
        public final String incorrectUsage;
        public final String incorrectCommand;

        public Messages(YamlConfiguration config) {
            noPermission = Utils.colorize(config.getString("no-permission"));
            tooManyConnections = Utils.colorize(config.getString("too-many-connections-kick"));
            offlinePlayer = Utils.colorize(config.getString("offline-player"));
            checkConnections = Utils.colorize(config.getString("checkconnections"));
            help = Utils.colorize(config.getString("help"));
            pluginReloaded = Utils.colorize(config.getString("plugin-reloaded"));
            serverReloadKick = Utils.colorize(config.getString("server-reload-kick"));
            incorrectUsage = Utils.colorize(config.getString("incorrect-usage"));
            incorrectCommand = Utils.colorize(config.getString("incorrect-command"));
        }
    }

    public class Settings {
        public final Integer maxConnectionsAllowedPerIp;
        public final Boolean kickAllPlayersOnServerReload;
        public final Boolean customPunishmentEnable;
        public final String customPunishmentCommand;

        public Settings(YamlConfiguration config) {
            maxConnectionsAllowedPerIp = config.getInt("general.max-connections-allowed-per-ip");
            kickAllPlayersOnServerReload = config.getBoolean("general.kick-all-players-on-server-reload");
            customPunishmentEnable = config.getBoolean("general.custom-punishment.enable");
            customPunishmentCommand = config.getString("general.custom-punishment.command");
        }
    }
}
