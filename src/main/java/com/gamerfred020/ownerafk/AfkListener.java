package com.gamerfred020.ownerafk;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AfkListener implements Listener {

    private final OwnerAfkPlugin plugin;

    public AfkListener(OwnerAfkPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // When a player joins, they'll automatically get the sidebar in the check task
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Clean up when player leaves
        Player player = event.getPlayer();
        // The main task will handle removal
    }
}
