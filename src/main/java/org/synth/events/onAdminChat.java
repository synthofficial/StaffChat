package org.synth.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.synth.StaffChat;

import java.util.List;

public class onAdminChat implements Listener {

    @EventHandler
    public void adminChat(PlayerChatEvent e){
        Player p = e.getPlayer();
        if(StaffChat.getInstance().isPlayerToggledIn(p)){
            e.setCancelled(true);
            if(StaffChat.getInstance().getConfig().getBoolean("debug")){
                StaffChat.getInstance().getLogger().info("Player " + p.getName() + " is in staff chat");
            }
            for(String admins : StaffChat.getInstance().getAdmins()){
                Player admin = p.getServer().getPlayer(admins);
                    admin.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cStaffChat&8] &7" + p.getName() + " &f> &c" + e.getMessage()));
            }
        }else if(StaffChat.getInstance().isPlayerAdmin(p) && e.getMessage().startsWith("/sc ")){
            e.setCancelled(true);
            if(StaffChat.getInstance().getConfig().getBoolean("debug")){
                StaffChat.getInstance().getLogger().info("Player " + p.getName() + " is in staff chat");
            }
            for(String admins : StaffChat.getInstance().getAdmins()){
                Player admin = p.getServer().getPlayer(admins);
                admin.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cStaffChat&8] &7" + p.getName() + " &f> &c" + e.getMessage().replace("/sc ", "")));
            }
        }
    }

}
