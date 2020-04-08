package com.joshy23.fragmentedstaff.commands;

import com.joshy23.fragmentedstaff.FragmentedStaff;
import com.joshy23.fragmentedstaff.util.TextHelper;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandBase implements TabExecutor {
    private FragmentedStaff plugin = FragmentedStaff.getPlugin();
    private TextHelper textHelper = new TextHelper();
    private ToggleModeCommand toggleModeCommand = new ToggleModeCommand();
    private VanishCommand vanishCommand = new VanishCommand();
    private FreezeCommand freezeCommand = new FreezeCommand();
    private VisualizeInventoryCommand visualizeInventoryCommand = new VisualizeInventoryCommand();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            ClickEvent clickEvent;
            TextComponent textComponent;
            sender.sendMessage(textHelper.getColor("&e&m-----[&r%prefix%&e&m]-----"));
            if(sender instanceof Player) {
                Player p = (Player) sender;
                for (String alias : plugin.getCommand(command.getName()).getAliases()) {
                    clickEvent = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/fs " + alias);
                    textComponent = new TextComponent(textHelper.getColor("&e/fs " + alias));
                    textComponent.setClickEvent(clickEvent);
                    p.spigot().sendMessage(textComponent);
                }
            }else{
                for(String alias : plugin.getCommand(command.getName()).getAliases()){
                    sender.sendMessage(textHelper.getColor("&e/fs "+alias));
                }
            }
            sender.sendMessage(textHelper.getColor("&e&m-----[&r%prefix%&e&m]-----"));
        }else{
            toggleModeCommand.onCommand(sender, command, label, args);
            vanishCommand.onCommand(sender, command, label, args);
            freezeCommand.onCommand(sender, command, label, args);
            visualizeInventoryCommand.onCommand(sender, command, label, args);
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
        list.addAll(toggleModeCommand.onTabComplete(sender, command, label, args));
        list.addAll(vanishCommand.onTabComplete(sender, command, label, args));
        list.addAll(freezeCommand.onTabComplete(sender, command, label, args));
        list.addAll(visualizeInventoryCommand.onTabComplete(sender, command, label, args));
        return list;
    }
}
