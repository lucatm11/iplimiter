package com.lucatm11.connectionlimiter;

import com.lucatm11.connectionlimiter.utils.configurations.Messages;
import com.lucatm11.connectionlimiter.utils.configurations.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.lucatm11.connectionlimiter.bstats.Metrics;
import com.lucatm11.connectionlimiter.commands.CommandRouter;
import com.lucatm11.connectionlimiter.listeners.Join;
import com.lucatm11.connectionlimiter.listeners.Leave;
import com.lucatm11.connectionlimiter.utils.configurations.Configurations;
import com.lucatm11.connectionlimiter.utils.Connection;

public class ConnectionLimiter extends JavaPlugin {
  public Connection connection;
  public Configurations.Messages messages;
  public Configurations.Settings settings;

  public void onEnable() {
    loadConfiguration();

    @SuppressWarnings("unused")
    Metrics metrics = new Metrics(this, 28657);

    if (settings.kickAllPlayersOnServerReload) {
      if (!Bukkit.getOnlinePlayers().isEmpty()) {
        for (Player player : Bukkit.getOnlinePlayers()) {
          player.kickPlayer(messages.serverReloadKick);
        }
      }
    }

    connection = new Connection();
    connection.initializeMap();

    getServer().getPluginManager().registerEvents(new Join(this), this);
    getServer().getPluginManager().registerEvents(new Leave(this), this);
    getCommand("connectionlimiter").setExecutor(new CommandRouter(this));
  }

  public void loadConfiguration() {
    Settings settingsLoader = new Settings(this);
    Messages messagesLoader = new Messages(this);

    settingsLoader.load();
    messagesLoader.load();

    Configurations configurations = new Configurations();

    settings = configurations.new Settings(settingsLoader.getConfig());
    messages = configurations.new Messages(messagesLoader.getConfig());
  }
}
