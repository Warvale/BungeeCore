package net.warvale.bungeecore;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class AlertCommand extends Command {

    public AlertCommand() {
        super("wvalert","warvale.alert","broadcast", "bc", "bcast", "alert");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(new TextComponent(ChatColor.RED+"Usage: /alert <message>"));
            return;
        }
        String msg = "";
        for (int i = 0; i < args.length; i++) {
            msg+=" "+args[i];
        }
        ProxyServer.getInstance().broadcast(new TextComponent(ChatColor.translateAlternateColorCodes('&',"&b&l[&cAlert&b&l]&f"+msg)));
    }
}
