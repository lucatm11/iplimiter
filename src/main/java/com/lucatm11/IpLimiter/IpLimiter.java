package com.lucatm11.IpLimiter;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.lucatm11.IpLimiter.Commands.CommandRouter;
import com.lucatm11.IpLimiter.Listeners.Join;
import com.lucatm11.IpLimiter.Listeners.Leave;
import com.lucatm11.IpLimiter.Listeners.ServerReload;
import com.lucatm11.IpLimiter.Utils.Utils;

public class IpLimiter extends JavaPlugin {
  private final ServerReload serverReload;

  public HashMap<String, Integer> playersByIP;

  public Integer maxIpsAllowed;
  public String noPermission;
  public String tooManyConnections;
  public String offlinePlayer;
  public String checkConnections;
  public String help;
  public String reloaded;
  public boolean kickAllPlayersReload;
  public String serverReloadKick;

  public IpLimiter() {
    this.serverReload = new ServerReload(this);
  }

  public void onEnable() {
    saveDefaultConfig();
    loadConfiguration();

    if(kickAllPlayersReload) {
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
    maxIpsAllowed = getConfig().getInt("max-ips-allowed");
    noPermission = Utils.colorize(getConfig().getString("messages.no-permission"));
    tooManyConnections = Utils.colorize(getConfig().getString("messages.too-many-connections"));
    offlinePlayer = Utils.colorize(getConfig().getString("messages.offline-player"));
    checkConnections = Utils.colorize(getConfig().getString("messages.checkconnections"));
    help = Utils.colorize(getConfig().getString("messages.help"));
    reloaded = Utils.colorize(getConfig().getString("messages.reloaded"));
    kickAllPlayersReload = getConfig().getBoolean("kick-all-players-reload");
    serverReloadKick = Utils.colorize(getConfig().getString("messages.server-reload-kick"));
  }
}
