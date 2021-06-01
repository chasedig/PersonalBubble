package com.chasecarlson.personalbubble;

import org.bukkit.entity.Player;

public class PlayerInfo
{
    Player player;
    boolean BubbleEnabled = true;

    public PlayerInfo(Player p)
    {
        this.player = p;
    }
}
