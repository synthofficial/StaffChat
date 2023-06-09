package org.synth.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.synth.StaffChat;

import java.util.List;

public class playerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("staffchat.use") || p.isOp()){
            StaffChat.getInstance().addAdmin(p);
            StaffChat.getInstance().togglePlayerIn(p);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cStaffChat&8] &fYou have auto-joined the staff chat."));
            List<String> adminsOnline = StaffChat.getInstance().getAdmins();
            for(String admin : adminsOnline){
                if(p.getServer().getPlayer(admin) != p){
                    p.getServer().getPlayer(admin).sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cStaffChat&8] &7" + p.getName() + " &fhas joined the staff chat."));
                }
            }
            if(StaffChat.getInstance().getConfig().getBoolean("debug")){
                StaffChat.getInstance().getLogger().info("Added " + p.getName() + " to admin list.");
            }
        }
    }

}
