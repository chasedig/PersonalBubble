package com.chasecarlson.personalbubble;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener
{
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        BubbleManager.playerInfoList.put(event.getPlayer(), new PlayerInfo(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event)
    {
        BubbleManager.playerInfoList.remove(event.getPlayer());
    }
}
