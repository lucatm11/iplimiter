package com.lucatm11.iplimiter;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.lucatm11.iplimiter.bstats.Metrics;
import com.lucatm11.iplimiter.commands.CommandRouter;
import com.lucatm11.iplimiter.listeners.Join;
import com.lucatm11.iplimiter.listeners.Leave;
import com.lucatm11.iplimiter.utils.Configuration;

public class IpLimiter extends JavaPlugin {
  public Configuration.Messages messages;
  public Configuration.Config config;

  private HashMap<String, Integer> playersByIP;

  public void addIPConnection(String ipAddress) {
    if (getConnections(ipAddress) == null) {
      playersByIP.put(ipAddress, 1);
    } else {
      playersByIP.put(ipAddress, getConnections(ipAddress) + 1);
    }
  }

  public void removeIPConnection(String ipAddress) {
    playersByIP.put(ipAddress, getConnections(ipAddress) - 1);
  }

  public Integer getConnections(String ipAddress) {
    return playersByIP.get(ipAddress);
  }

  public void onEnable() {
    saveDefaultConfig();
    loadConfiguration();

    @SuppressWarnings("unused")
    Metrics metrics = new Metrics(this, 28657);

    if (config.kickAllPlayersReload) {
      if (!Bukkit.getOnlinePlayers().isEmpty()) {
        for (Player player : Bukkit.getOnlinePlayers()) {
          player.kickPlayer(messages.serverReloadKick);
        }
      }
    }

    playersByIP = new HashMap<String, Integer>();

    getServer().getPluginManager().registerEvents(new Join(this), this);
    getServer().getPluginManager().registerEvents(new Leave(this), this);
    getCommand("iplimiter").setExecutor(new CommandRouter(this));
  }

  public void loadConfiguration() {
    Configuration configuration = new Configuration();
    config = configuration.new Config(getConfig());
    messages = configuration.new Messages(getConfig());
  }
}
