package org.synth.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.synth.StaffChat;

public class playerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("staffchat.use")){
            StaffChat.getInstance().removeAdmin(p);
            if(StaffChat.getInstance().getConfig().getBoolean("debug")){
                StaffChat.getInstance().getLogger().info("Removed " + p.getName() + " from admin list.");
            }
            for(String admin : StaffChat.getInstance().getAdmins()){
                if(p.getServer().getPlayer(admin) != p){
                    p.getServer().getPlayer(admin).sendMessage("§8[§cStaffChat§8] §7" + p.getName() + " §fhas left the staff chat.");
                }
            }
        }
        if(StaffChat.getInstance().getConfig().getBoolean("debug")){
            StaffChat.getInstance().getLogger().info("Removed " + p.getName() + " from the admin list.");
        }
    }

}
