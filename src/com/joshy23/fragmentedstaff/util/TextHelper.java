package com.joshy23.fragmentedstaff.util;

import com.joshy23.fragmentedstaff.FragmentedStaff;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextHelper {
    private FragmentedStaff plugin = FragmentedStaff.getPlugin();
    private FileConfiguration config = plugin.getConfig();

    public String getColor(String text){
        return ChatColor.translateAlternateColorCodes('&', text.replaceAll("%prefix%", checkString(config.getString("prefix"), config.getDefaults().getString("prefix"))));
    }

    public List<String> getColorList(List<String> strings){
        List<String> messages = new ArrayList<>();
        for(int i = 0, size = strings.size(); i < size; i++){
            messages.add(i, getColor(strings.get(i)));
        }
        return messages;
    }

    public void sendConsoleColor(String text){
        plugin.getServer().getConsoleSender().sendMessage(getColor(text));
    }

    public void sendConsoleColorList(List<String> strings){
        plugin.getServer().getConsoleSender().sendMessage(getColorList(strings).toArray(new String[]{}));
    }

    public String checkString(String text, String defText){
        if(text == null){
            return getColor(defText);
        }else{
            return getColor(text);
        }
    }

    public List<String> checkStringList(List<String> strings, List<String> defStrings){
        if(strings.isEmpty()){
            return getColorList(strings);
        }else{
            return getColorList(defStrings);
        }
    }

}
