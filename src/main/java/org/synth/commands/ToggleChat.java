package org.synth.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.synth.StaffChat;

public class ToggleChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(!p.isOp() || !p.hasPermission("staffchat.use")){
            p.sendMessage("You do not have permission to use this command.");
            return false;
        }else {
            if (strings.length == 0) {
                if (StaffChat.getInstance().getToggledPlayers().contains(p.getName())) {
                    StaffChat.getInstance().togglePlayerOut(p);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cStaffChat&8] &7You have toggled out of staff chat."));
                    for (String player : StaffChat.getInstance().getAdmins()) {
                        p.getServer().getPlayer(player).sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cStaffChat&8] &7" + p.getName() + " &fhas left the staff chat."));
                    }
                    return true;
                } else {
                    StaffChat.getInstance().togglePlayerIn(p);
                    for (String player : StaffChat.getInstance().getAdmins()) {
                        p.getServer().getPlayer(player).sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cStaffChat&8] &7" + p.getName() + " &fhas joined the staff chat."));
                    }
                    return true;
                }
            } else if (strings.length >= 1) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < strings.length; i++) {
                    sb.append(strings[i]).append(" ");
                }
                String message = sb.toString();
                for (String player : StaffChat.getInstance().getAdmins()) {
                    p.getServer().getPlayer(player).sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cStaffChat&8] &7" + p.getName() + " &f>> &c" + message));
                }
                return true;

            }
        }
        return true;
    }
}
