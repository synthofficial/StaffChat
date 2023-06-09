package org.synth;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.synth.events.onAdminChat;
import org.synth.events.playerJoin;
import org.synth.events.playerQuit;

import java.util.*;

public final class StaffChat extends JavaPlugin {

    private static StaffChat plugin;

    //Storage for users with staffchat.use permission
    HashMap<Player, UUID> admins = new HashMap<>();
    HashMap<Player, UUID> toggledChat = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        //Create config file
        saveDefaultConfig();
        //Register events
        getServer().getPluginManager().registerEvents(new playerJoin(), this);
        getServer().getPluginManager().registerEvents(new onAdminChat(), this);
        getServer().getPluginManager().registerEvents(new playerQuit(), this);
        //Register commands
        this.getCommand("staffchat").setExecutor(new org.synth.commands.ToggleChat());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static StaffChat getInstance(){
        return plugin;
    }

    public void addAdmin(Player player){
        admins.put(player, player.getUniqueId());
    }

    public void removeAdmin(Player player){
        admins.remove(player);
    }

    public boolean isPlayerAdmin(Player player){
        if(admins.containsKey(player)){
            return true;
        } else {
            return false;
        }
    }

    public boolean isPlayerToggledIn(Player player){
        if(toggledChat.containsKey(player)){
            return true;
        } else {
            return false;
        }
    }

    public void togglePlayerIn(Player player){
        toggledChat.put(player, player.getUniqueId());
    }
    public void togglePlayerOut(Player player){
        toggledChat.remove(player);
    }

    public List<String> getToggledPlayers(){
        List<String> toggledList = new ArrayList<>();
        for(Player p : toggledChat.keySet()){
            toggledList.add(p.getName());
        }
        return toggledList;
    }

    public List<String> getAdmins(){
        List<String> adminList = new ArrayList<>();
        for(Player p : admins.keySet()){
            adminList.add(p.getName());
        }
        return adminList;
    }
}
