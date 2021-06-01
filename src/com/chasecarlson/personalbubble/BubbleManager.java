package com.chasecarlson.personalbubble;

import org.bukkit.permissions.Permissible;
import org.jetbrains.annotations.Nullable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class BubbleManager
{
    public static Map<Player, PlayerInfo> playerInfoList = new HashMap<>();

    public static List<Player> getBubblePlayers()
    {
        Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        List<Player> bubblePlayers = new ArrayList<>();
        for (Player p : players)
        {
            if (BubbleManager.hasBubblePermission(p))
            {
                if (playerInfoList.get(p).BubbleEnabled)
                {
                    bubblePlayers.add(p);
                }
            }
        }
        return bubblePlayers;
    }

    public static List<Player> getBubbleAffectedPlayers()
    {
        Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        List<Player> affectedPlayers = new ArrayList<>();

        for (Player p : players)
        {
            if (!p.hasPermission("personalbubble.bubble.bypass"))
            {
                affectedPlayers.add(p);
            }
        }
        return affectedPlayers;
    }

    public static boolean hasBubblePermission(Permissible p)
    {
        String p1 = "personalbubble.bubble";
        String p2 = "personalbubble.bubble.strength.";
        String p3 = "personalbubble.bubble.size.";

        for (PermissionAttachmentInfo perms : p.getEffectivePermissions())
        {
            String permissionStr = perms.getPermission();
            if (permissionStr.equals(p1) || permissionStr.startsWith(p2) || permissionStr.startsWith(p3))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean getBubbleEnabled(Player p)
    {
        return getPlayerInfo(p).BubbleEnabled;
    }

    public static void setBubbleEnabled(Player p, boolean enabled)
    {
        getPlayerInfo(p).BubbleEnabled = enabled;
    }

    public static int getBubbleStrength(Player p)
    {
        int default_strength = 1;
        int max_strength = 10;
        return Math.min(max_strength, getIntegerFromPermission(p, "personalbubble.bubble.strength.", default_strength));
    }

    public static int getBubbleSize(Player p)
    {
        int default_size = 4;
        int max_size = 100;
        return Math.min(max_size, getIntegerFromPermission(p, "personalbubble.bubble.size.", default_size));
    }


    private static int getIntegerFromPermission(Player p, String prefixString, int default_value)
    {
        for (PermissionAttachmentInfo perms : p.getEffectivePermissions())
        {
            if (perms.getPermission().startsWith(prefixString))
            {
                return Integer.parseInt(perms.getPermission().split(Pattern.quote(prefixString))[1]);
            }
        }
        return default_value;
    }

    private static PlayerInfo getPlayerInfo(Player p)
    {
        return playerInfoList.get(p);
    }


}
