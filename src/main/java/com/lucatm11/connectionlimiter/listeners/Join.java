package com.lucatm11.connectionlimiter.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import com.lucatm11.connectionlimiter.ConnectionLimiter;

public class Join implements Listener {
    private final ConnectionLimiter plugin;

    public Join(ConnectionLimiter plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        String ip = event.getAddress().getHostName();
        plugin.connection.initializeConnection(ip);

        if(plugin.connection.getConnections(ip) < plugin.settings.maxConnectionsAllowedPerIp) {
            plugin.connection.addConnection(ip);
            return;
        }

        if(event.getPlayer().hasPermission("connectionlimiter.exempt")) {
            plugin.connection.addConnection(ip);
            return;
        }

        if (plugin.settings.punishmentEnabled) {
            String cmd = plugin.settings.punishmentCommand.replace("{player}", event.getPlayer().getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);

            if (!plugin.settings.punishmentDisallowEntry) return;
        }

        event.disallow(Result.KICK_OTHER, plugin.messages.tooManyConnections);

        if(plugin.settings.notificationsEnabled) {
            String message = plugin.messages.notifyMessage.replace("{player}", event.getPlayer().getName());

            Bukkit.getConsoleSender().sendMessage(message);

            for (Player player : Bukkit.getOnlinePlayers()) {
                if(player.hasPermission("connectionlimiter.notify")) {
                    player.sendMessage(message);
                }
            }
        }
    }
}
