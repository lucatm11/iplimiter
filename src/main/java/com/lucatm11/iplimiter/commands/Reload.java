package com.lucatm11.iplimiter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.lucatm11.iplimiter.IpLimiter;

public class Reload implements CommandExecutor {
    private final IpLimiter ipLimiter;

    public Reload(IpLimiter ipLimiter) {
        this.ipLimiter = ipLimiter;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("iplimiter.reload")) {
            sender.sendMessage(ipLimiter.messages.noPermission);
            return true;
        }

        ipLimiter.reloadConfig();
        ipLimiter.loadConfiguration();

        sender.sendMessage(ipLimiter.messages.reloaded);

        return true;
    }
}
