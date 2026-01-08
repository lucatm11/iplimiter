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
        Player player = event.getPlayer();
        String ip = event.getAddress().getHostName();

        plugin.connection.initializeConnection(ip);

        if (plugin.connection.getConnections(ip) >= plugin.settings.maxConnectionsAllowedPerIp) {
            if(!player.hasPermission("connectionlimiter.bypass")) {
                if(plugin.settings.punishmentEnabled) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), plugin.settings.punishmentCommand.replace("{player}", player.getName()));
                    if(plugin.settings.punishmentDisallowEntry) {
                        event.disallow(Result.KICK_OTHER, plugin.messages.tooManyConnections);
                    }
                } else {
                    event.disallow(Result.KICK_OTHER, plugin.messages.tooManyConnections);
                }
            }
        } else {
            plugin.connection.addConnection(ip);
        }
    }
}
