package com.joshy23.fragmentedstaff.util;

import com.joshy23.fragmentedstaff.FragmentedStaff;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class TextHelper {
    private FragmentedStaff plugin = FragmentedStaff.getPlugin();
    private FileConfiguration config = plugin.getConfig();

    public String getColor(String text){
        return ChatColor.translateAlternateColorCodes('&', text.replaceAll("%prefix%", config.getString("prefix")));
    }

    public List<String> getColorList(List<String> texts){
        List<String> messages = new ArrayList<>();
        for(String text:texts){
            messages.add(getColor(text));
        }
        return messages;
    }

    public String[] getColorList(String[] texts){
        String[] messages = new String[]{};
        int length = texts.length/2;
        if(texts.length == 0){
            messages[0] = getColor(texts[0]);
        }else {
            for (int i = 0, c = texts.length; i < length && c >= length; i++, c--) {
                messages[i] = getColor(texts[i]);
                messages[c] = getColor(texts[c]);
            }
        }
        return messages;
    }

    public void sendConsoleColor(String text){
        plugin.getServer().getConsoleSender().sendMessage(getColor(text));
    }

    public void sendConsoleColor(String[] texts){
        plugin.getServer().getConsoleSender().sendMessage(getColorList(texts));
    }

}
