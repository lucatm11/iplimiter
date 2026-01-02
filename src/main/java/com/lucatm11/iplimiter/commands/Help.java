package com.lucatm11.iplimiter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.lucatm11.iplimiter.IpLimiter;

public class Help implements CommandExecutor {
    private final IpLimiter ipLimiter;

    public Help(IpLimiter ipLimiter) {
        this.ipLimiter = ipLimiter;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("iplimiter.help")) {
            sender.sendMessage(ipLimiter.messages.noPermission);
            return true;
        }

        sender.sendMessage(ipLimiter.messages.help);

        return true;
    }

}
