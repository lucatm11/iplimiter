package com.lucatm11.iplimiter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.lucatm11.iplimiter.IpLimiter;

public class CommandRouter implements CommandExecutor {
    private final IpLimiter ipLimiter;
    private final Help help;
    private final CheckConnections checkConnections;
    private final Reload reload;

    public CommandRouter(IpLimiter ipLimiter) {
        this.ipLimiter = ipLimiter;
        this.help = new Help(ipLimiter);
        this.checkConnections = new CheckConnections(ipLimiter);
        this.reload = new Reload(ipLimiter);
    }


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0 || args.length > 2) {
            return help.onCommand(sender, command, label, args);
        }

        switch (args[0].toLowerCase()) {
            case "checkconnections":
                return checkConnections.onCommand(sender, command, label, args);
            case "reload":
                return reload.onCommand(sender, command, label, args);
            default:
                return help.onCommand(sender, command, label, args);
        }
    }
}
