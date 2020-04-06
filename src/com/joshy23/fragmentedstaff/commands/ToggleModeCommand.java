package com.joshy23.fragmentedstaff.commands;

import com.joshy23.fragmentedstaff.util.SavedElements;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ToggleModeCommand implements TabExecutor {
    private ItemStack freezerItem;
    private ItemStack vanishItem;
    private ItemStack seeInventoryItem;
    private HashMap<UUID, SavedElements> savedElements = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            PlayerInventory playerInv;
            if(p.hasPermission("fragmentedstaff.toggle.use")){
                playerInv = p.getInventory();
                savedElements.put(p.getUniqueId(), new SavedElements(playerInv.getArmorContents(), playerInv.getContents(),p.getGameMode(),
                        p.getActivePotionEffects(), p.getFoodLevel(), p.getHealth(), p.getMaxHealth(), p.isFlying()));
                for(Player online:Bukkit.getServer().getOnlinePlayers()){
                    online.hidePlayer(p);
                }
                if(p.hasPermission("fragmentedstaff.toggle.use.other")) {
                    for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                        if (args[1].equals(online.getName())) {
                            playerInv = online.getInventory();
                            savedElements.put(online.getUniqueId(), new SavedElements(playerInv.getArmorContents(), playerInv.getContents(), online.getGameMode(),
                                    online.getActivePotionEffects(), online.getFoodLevel(), online.getHealth(), online.getMaxHealth(), online.isFlying()));

                        }
                    }
                }
            }else{

            }
        }else{

        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }

}
