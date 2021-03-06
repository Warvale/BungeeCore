package net.warvale.bungeecore;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReplyCommand extends Command {

    public ReplyCommand() {
        super("reply","warvale.reply","r");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("This is a player-only command."));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (args.length < 1) {
            sender.sendMessage(new TextComponent(ChatColor.RED+"Usage: /reply <message>"));
            return;
        }
        if (MessageMan.getLast(player) == null) {
            sender.sendMessage(new TextComponent(ChatColor.RED+"Cannot find that player!"));
            return;
        }
        ProxiedPlayer target = MessageMan.getLast(player);
        if (target.getName().equals(sender.getName())) {
            sender.sendMessage(new TextComponent(ChatColor.RED+"You cannot message yourself."));
            return;
        }
        String msg = "";
        for (int i = 0; i < args.length; i++) {
            msg+=" "+args[i];
        }
        target.sendMessage(new TextComponent(ChatColor.AQUA+"From "+sender.getName()+":"+ChatColor.WHITE+msg));
        sender.sendMessage(new TextComponent(ChatColor.AQUA+"To "+target.getName()+":"+ChatColor.WHITE+msg));
    }
}
