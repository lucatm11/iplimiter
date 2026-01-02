package com.lucatm11.iplimiter;

import java.util.HashMap;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.lucatm11.iplimiter.commands.CommandRouter;
import com.lucatm11.iplimiter.listeners.Join;
import com.lucatm11.iplimiter.listeners.Leave;
import com.lucatm11.iplimiter.listeners.ServerReload;
import com.lucatm11.iplimiter.utils.Configuration;

public class IpLimiter extends JavaPlugin {
  private final ServerReload serverReload;

  public Configuration.Messages messages;
  public Configuration.Config config;

  public HashMap<String, Integer> playersByIP;

  public IpLimiter() {
    this.serverReload = new ServerReload(this);
  }

  public void onEnable() {
    saveDefaultConfig();
    loadConfiguration();

    Metrics metrics = new Metrics(this, 28657);

    if(config.kickAllPlayersReload) {
      if(!Bukkit.getOnlinePlayers().isEmpty()) {
        serverReload.onReload();
      }
    }

    playersByIP = new HashMap<String, Integer>();

    getServer().getPluginManager().registerEvents(new Join(this), this);
    getServer().getPluginManager().registerEvents(new Leave(this), this);
    getCommand("iplimiter").setExecutor(new CommandRouter(this));
  }

  public void loadConfiguration() {
    Configuration configuration = new Configuration();
    configuration.new Config(getConfig());
    configuration.new Messages(getConfig());
  }
}
