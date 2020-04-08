package com.joshy23.fragmentedstaff.commands;

import com.joshy23.fragmentedstaff.FragmentedStaff;
import com.joshy23.fragmentedstaff.util.SavedElements;
import com.joshy23.fragmentedstaff.util.TextHelper;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ToggleModeCommand implements TabExecutor {
    //private ItemStack freezerItem;
    //private ItemStack vanishItem;
    //private ItemStack seeInventoryItem;
    private HashMap<UUID, SavedElements> savedElements = new HashMap<>();
    private TextHelper textHelper = new TextHelper();
    private FragmentedStaff plugin = FragmentedStaff.getPlugin();
    private FileConfiguration config = plugin.getConfig();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            //PlayerInventory playerInv;
            if(p.hasPermission("fragmentedstaff.staffmode.use")){
                toggleStaffMode(p);
                /*
                if(p.hasPermission("fragmentedstaff.staffmode.use.other")) {
                    for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                        if (args[1].equals(online.getName())) {
                            playerInv = online.getInventory();
                            savedElements.put(online.getUniqueId(), new SavedElements(playerInv.getArmorContents(), playerInv.getContents(), online.getGameMode(),
                                    online.getActivePotionEffects(), online.getFoodLevel(), online.getHealth(), online.getMaxHealth(), online.isFlying()));
                            p.sendMessage(textHelper.getColor("%prefix% "+textHelper.checkStringList(config.getStringList("messages.staff-mode.successfully-toggled-other"), new ArrayList<String>(){}.add()).replaceAll("%player%", online.getName()).replaceAll("%displayname%", online.getDisplayName())));
                        }
                    }
                }else{
                    p.sendMessage(textHelper.getColor("%prefix% "+textHelper.checkString("messages.staff-mode.toggle-other-no-permission-error-message", "&cYou don't have the permission required for this").replaceAll("%player%", p.getName()).replaceAll("%displayname%", p.getDisplayName())));
                }
                */
            }else{
                p.sendMessage(textHelper.getColorList(textHelper.checkStringList(config.getStringList("messages.staff-mode.error-player-no-permission"),
                        config.getDefaults().getStringList("messages.staff-mode.error-player-no-permission"))).toArray(new String[]{}));
            }
        }else{
            if(args[1] == null){
                textHelper.sendConsoleColorList(textHelper.checkStringList(config.getStringList("messages.staff-mode.console-error-null-player"),
                        config.getDefaults().getStringList("messages.staff-mode.console-error-null-player")));
            }else{
                for(Player online:Bukkit.getServer().getOnlinePlayers()){
                    if(online.getName().equals(args[1])){
                        if(online.hasPermission("fragmentedstaff.staffmode.use")){
                            toggleStaffMode(online);
                        }else{
                            textHelper.sendConsoleColorList(textHelper.checkStringList(config.getStringList("messages.staff-mode.console-error-player-no-permission"),
                                    config.getDefaults().getStringList("messages.staff-mode.error-player-no-permission")));
                        }
                    }else{
                        textHelper.sendConsoleColorList(textHelper.checkStringList(config.getStringList("messages.staff-mode.console-error-player-not-found"),
                                config.getDefaults().getStringList("messages.staff-mode.console-error-player-not-found")));
                    }
                }
            }
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        for(Player online:Bukkit.getServer().getOnlinePlayers()){
            list.add(online.getName());
        }
        return list;
    }

    public void toggleStaffMode(Player p){
        //states info: [active],[vanish]
        ArrayList<Boolean> states = new ArrayList<>(2);
        if(FragmentedStaff.getPlayerStates().containsKey(p.getUniqueId())){
            PlayerInventory playerInventory = p.getInventory();
            if(FragmentedStaff.getPlayerStates().get(p.getUniqueId()).get(1)){
                playerInventory.clear();
                p.setGameMode(savedElements.get(p.getUniqueId()).getSavedGameMode());
                p.setFoodLevel(savedElements.get(p.getUniqueId()).getSavedHunger());
                p.setMaxHealth(savedElements.get(p.getUniqueId()).getSavedTotalLife());
                p.setHealth(savedElements.get(p.getUniqueId()).getSavedLife());
                playerInventory.setArmorContents(savedElements.get(p.getUniqueId()).getSavedEquipment());
                playerInventory.setContents(savedElements.get(p.getUniqueId()).getSavedInventory());
                p.addPotionEffects(savedElements.get(p.getUniqueId()).getSavedPotionEffects());
                p.setAllowFlight(savedElements.get(p.getUniqueId()).isSavedFlyState());
                p.setFlying(savedElements.get(p.getUniqueId()).isSavedFlyState());
                p.sendMessage(textHelper.getColorList(textHelper.checkStringList(config.getStringList("messages.staff-mode."),
                        config.getDefaults().getStringList("messages.staff-mode.successfully-disabled"))).toArray(new String[]{}));
                states.set(1, false);
            }else{
                savedElements.put(p.getUniqueId(), new SavedElements(playerInventory.getArmorContents(), playerInventory.getContents(), p.getGameMode(),
                        p.getActivePotionEffects(), p.getFoodLevel(), p.getHealth(), p.getMaxHealth(), p.isFlying()));
                p.setGameMode(GameMode.ADVENTURE);
                p.setFoodLevel(20);
                p.setMaxHealth(20);
                p.setHealth(20);
                playerInventory.clear();
                for(PotionEffect potionEffect:p.getActivePotionEffects()){
                    p.removePotionEffect(potionEffect.getType());
                }
                p.setAllowFlight(true);
                p.setFlying(true);
                for(Player online:Bukkit.getServer().getOnlinePlayers()){
                    if(!online.hasPermission("fragmentedstaff.vanish.see")){
                        online.hidePlayer(p);
                    }
                }
                p.sendMessage(textHelper.getColorList(textHelper.checkStringList(config.getStringList("messages.staff-mode.successfully-enabled"),
                        config.getDefaults().getStringList("messages.staff-mode.successfully-enabled"))).toArray(new String[]{}));
                states.set(1, true);
            }
            FragmentedStaff.getPlayerStates().replace(p.getUniqueId(), states);
        }else{
            FragmentedStaff.getPlayerStates().put(p.getUniqueId(), states);
            toggleStaffMode(p);
        }
    }

}
