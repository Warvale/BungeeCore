package net.warvale.bungeecore;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class StaffChatCommand extends Command {
    public StaffChatCommand() {
        super("staffchat","warvale.staff","sc");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(new TextComponent(ChatColor.RED+"Usage: /staffchat <message>"));
            return;
        }
        String msg = "";
        for (int i = 0; i < args.length; i++) {
            msg+=" "+args[i];
        }
        msg = ChatColor.translateAlternateColorCodes('&',msg);
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            if (!player.hasPermission("warvale.staff")) return;
            player.sendMessage(new TextComponent(ChatColor.AQUA+"[Staff]"+ChatColor.RESET+msg));
        }
    }
}
