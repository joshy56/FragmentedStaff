package com.joshy23.fragmentedstaff;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class FragmentedStaff extends JavaPlugin {
    private static FragmentedStaff plugin;
    private static HashMap<UUID, Boolean[]> playerStates = new HashMap<>();

    public void onEnable() {
        plugin = this;
    }

    public void onDisable() {

    }

    public static FragmentedStaff getPlugin() {
        return plugin;
    }

    public static HashMap<UUID, Boolean[]> getPlayerStates() {
        return playerStates;
    }

    public static void setPlayerStates(HashMap<UUID, Boolean[]> playerStates) {
        FragmentedStaff.playerStates = playerStates;
    }
}
