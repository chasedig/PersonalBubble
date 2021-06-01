package com.chasecarlson.personalbubble.commands;

import com.chasecarlson.personalbubble.BubbleManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandBubble implements CommandExecutor
{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
    {
        Player bubblePlayer = null;
        Player player;
        if (!BubbleManager.hasBubblePermission(sender))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to enable/disable bubbles!" + ChatColor.WHITE + " (personalbubble.bubble)");
            return true;
        }
        if (sender instanceof Player)
        {
            player = (Player)sender;
            if (args.length < 2)
            {
                bubblePlayer = player;
            }
        }
        boolean setOtherPlayer = false;
        if (args.length == 2)
        {
            bubblePlayer = Bukkit.getPlayer(args[1]);
            setOtherPlayer = true;
            if (!sender.hasPermission("personalbubble.bubble.others"))
            {
                sender.sendMessage(ChatColor.RED + "You do not have permission to enable or disable others' bubbles!" + ChatColor.WHITE + " (personalbubble.bubble.others)");
                return true;
            }
            if (bubblePlayer == null)
            {
                sender.sendMessage(ChatColor.RED + "The user "+args[1]+" is not online!");
                return true;
            }
            if (!BubbleManager.hasBubblePermission(bubblePlayer))
            {
                sender.sendMessage(ChatColor.RED + "The user "+bubblePlayer.getName()+" does not have bubble permissions!");
                return true;
            }
        }
        if (bubblePlayer != null)
        {
            boolean bubbleStatus = BubbleManager.getBubbleEnabled(bubblePlayer);
            if (args.length >= 1)
            {
                if (args[0].equals("enable"))
                {
                    enableBubble(bubblePlayer);
                    if (setOtherPlayer)
                    {
                        sender.sendMessage(ChatColor.GREEN + bubblePlayer.getName()+"'s bubble has been enabled!");
                    }
                    return true;
                }
                else if (args[0].equals("disable"))
                {
                    disableBubble(bubblePlayer);
                    if (setOtherPlayer)
                    {
                        sender.sendMessage(ChatColor.RED + bubblePlayer.getName()+"'s bubble has been disabled!");
                    }
                    return true;
                }
            }
            else
            {
                if (bubbleStatus)
                {
                    disableBubble(bubblePlayer);
                }
                else
                {
                    enableBubble(bubblePlayer);
                }
                return true;

            }
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "You must be a player to set your own bubble!");
        }
        return false;
    }

    public void enableBubble(Player p)
    {
        BubbleManager.setBubbleEnabled(p, true);
        p.sendMessage(ChatColor.GREEN + "Your bubble has been enabled!");
    }

    public void disableBubble(Player p)
    {
        BubbleManager.setBubbleEnabled(p, false);
        p.sendMessage(ChatColor.RED + "Your bubble has been disabled!");
    }
}
