package com.lucatm11.iplimiter.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.lucatm11.iplimiter.IpLimiter;

public class Leave implements Listener{
    private final IpLimiter ipLimiter;

    public Leave(IpLimiter ipLimiter) {
        this.ipLimiter = ipLimiter;
    }

    @EventHandler
    public void LeaveEvent(PlayerQuitEvent event) {
        String ip = event.getPlayer().getAddress().getHostName();
        Integer connections = ipLimiter.playersByIP.get(ip);

        ipLimiter.playersByIP.put(ip, connections - 1);
    }
}
