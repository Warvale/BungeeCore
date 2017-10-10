package net.warvale.bungeecore;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MSGCommand extends Command {

    public MSGCommand() {
        super("msg","warvale.msg","pm","dm","message","tell");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("This is a player-only command."));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (args.length < 2) {
            sender.sendMessage(new TextComponent(ChatColor.RED+"Usage: /msg <player> <message>"));
            return;
        }
        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(new TextComponent(ChatColor.RED+"Cannot find that player!"));
            return;
        }
        if (target.getName().equals(sender.getName())) {
            sender.sendMessage(new TextComponent(ChatColor.RED+"You cannot message yourself."));
            return;
        }
        String msg = "";
        for (int i = 1; i < args.length; i++) {
            msg+=" "+args[i];
        }
        target.sendMessage(new TextComponent(ChatColor.AQUA+"From "+sender.getName()+":"+ChatColor.WHITE+msg));
        sender.sendMessage(new TextComponent(ChatColor.AQUA+"To "+target.getName()+":"+ChatColor.WHITE+msg));
        MessageMan.setLast(player, target);
        MessageMan.setLast(target, player);
    }
}
