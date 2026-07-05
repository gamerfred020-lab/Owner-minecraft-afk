package com.gamerfred020.ownerafk;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

public class OwnerAfkPlugin extends JavaPlugin {

    private String ownerName;
    private Map<Player, Scoreboard> playerScoreboards = new HashMap<>();

    @Override
    public void onEnable() {
        // Create default config if not exists
        saveDefaultConfig();
        
        ownerName = getConfig().getString("owner-name", "YourName");
        
        getLogger().info("Owner AFK Plugin enabled!");
        getLogger().info("Monitoring owner: " + ownerName);
        
        // Register listeners
        new AfkListener(this);
        
        // Start the check task
        startAfkCheckTask();
    }

    @Override
    public void onDisable() {
        getLogger().info("Owner AFK Plugin disabled!");
        // Clear scoreboards
        playerScoreboards.clear();
    }

    private void startAfkCheckTask() {
        // Run check every 20 ticks (1 second)
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            Player owner = Bukkit.getPlayer(ownerName);
            
            if (owner != null && owner.isOnline()) {
                // Check if owner is in spectator mode
                if (owner.getGameMode().toString().equals("SPECTATOR")) {
                    // Show AFK message to all online players
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (!playerScoreboards.containsKey(player)) {
                            showAfkSidebar(player);
                        }
                    }
                } else {
                    // Remove AFK message from all players
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (playerScoreboards.containsKey(player)) {
                            removeAfkSidebar(player);
                        }
                    }
                }
            } else {
                // Remove AFK message if owner is not online
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (playerScoreboards.containsKey(player)) {
                        removeAfkSidebar(player);
                    }
                }
            }
        }, 0, 20); // Run every 20 ticks (1 second)
    }

    private void showAfkSidebar(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        
        String title = getConfig().getString("sidebar.title", "&c&lOWNER STATUS");
        String message = getConfig().getString("sidebar.message", "&e&lOwner is AFK");
        String info = getConfig().getString("sidebar.info", "&7They will be back shortly!");
        
        title = title.replace("&", "§");
        message = message.replace("&", "§");
        info = info.replace("&", "§");
        
        Objective objective = scoreboard.registerNewObjective("afkstatus", "dummy", title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        
        objective.getScore(message).setScore(2);
        objective.getScore(info).setScore(1);
        
        player.setScoreboard(scoreboard);
        playerScoreboards.put(player, scoreboard);
    }

    private void removeAfkSidebar(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        playerScoreboards.remove(player);
    }

    public String getOwnerName() {
        return ownerName;
    }
}