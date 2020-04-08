package com.joshy23.fragmentedstaff.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class VanishCommand implements TabExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        return list;
    }
}
