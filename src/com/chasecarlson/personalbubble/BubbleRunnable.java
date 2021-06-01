package com.chasecarlson.personalbubble;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class BubbleRunnable implements Runnable
{
    public void run()
    {
        List<Player> players = BubbleManager.getBubbleAffectedPlayers();
        List<Player> bubblePlayers = BubbleManager.getBubblePlayers();

        for (Player p : players)
        {
            for (Player bp : bubblePlayers)
            {
                if (bp != p)
                {
                    Vector playerVector = (p.getLocation().toVector().subtract(bp.getLocation().toVector()));
                    double distance = playerVector.length();

                    if (distance < BubbleManager.getBubbleSize(bp))
                    {
                        p.setVelocity(playerVector.normalize().multiply(BubbleManager.getBubbleStrength(bp)));
                    }
                }
            }
        }
    }
}
