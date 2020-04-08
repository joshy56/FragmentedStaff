package com.joshy23.fragmentedstaff;

import com.joshy23.fragmentedstaff.commands.CommandBase;
import com.joshy23.fragmentedstaff.util.TextHelper;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class FragmentedStaff extends JavaPlugin {
    private static FragmentedStaff plugin;
    private static HashMap<UUID, ArrayList<Boolean>> playerStates = new HashMap<>();
    private TextHelper textHelper = new TextHelper();

    public void onEnable() {
        plugin = this;
        setCommands();
        textHelper.sendConsoleColor("&7[&8Fragmented&eStaff&7]&r &aHi! Hi!");
    }

    public void onDisable() {
        textHelper.sendConsoleColor("&7[&8Fragmented&eStaff&7]&r &eGoodbye!");
    }

    public static FragmentedStaff getPlugin() {
        return plugin;
    }

    public static HashMap<UUID, ArrayList<Boolean>> getPlayerStates() {
        return playerStates;
    }

    public static void setPlayerStates(HashMap<UUID, ArrayList<Boolean>> playerStates) {
        FragmentedStaff.playerStates = playerStates;
    }

    private void setCommands(){
        getCommand("fs").setExecutor(new CommandBase());
        getCommand("fs").setTabCompleter(new CommandBase());
    }

}
