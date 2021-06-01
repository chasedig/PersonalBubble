package com.chasecarlson.personalbubble;

import com.chasecarlson.personalbubble.commands.CommandBubble;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;

public class PersonalBubble extends JavaPlugin
{
    public static Plugin plugin;

    public void onEnable()
    {
        plugin = this;

        BukkitScheduler scheduler = this.getServer().getScheduler();
        BubbleRunnable bubbleRunnable = new BubbleRunnable();
        scheduler.scheduleSyncRepeatingTask(this, bubbleRunnable, 0L, 5L);

        getServer().getPluginManager().registerEvents(new EventListener(), this);
        this.getCommand("bubble").setExecutor(new CommandBubble());

        for (Player p : new ArrayList<>(this.getServer().getOnlinePlayers()))
        {
            BubbleManager.playerInfoList.put(p, new PlayerInfo(p));
        }
    }

    public void onDisable()
    {

    }
}
