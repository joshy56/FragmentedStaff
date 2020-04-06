package com.joshy23.fragmentedstaff.util;

import org.bukkit.GameMode;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

public class SavedElements {
    private ItemStack[] savedEquipment;
    private ItemStack[] savedInventory;
    private GameMode savedGameMode;
    private Collection<PotionEffect> savedPotionEffects;
    private int savedHunger;
    private double savedLife;
    private double savedTotalLife;
    private boolean savedFlyState;

    public SavedElements(ItemStack[] equipment, ItemStack[] inventory, GameMode gameMode, Collection<PotionEffect> potionEffects, int hunger, double life, double totalLife, boolean flyState){
        savedEquipment = equipment;
        savedInventory = inventory;
        savedGameMode = gameMode;
        savedPotionEffects = potionEffects;
        savedHunger = hunger;
        savedLife = life;
        savedTotalLife = totalLife;
        savedFlyState = flyState;
    }

    public ItemStack[] getSavedEquipment() {
        return savedEquipment;
    }

    public void setSavedEquipment(ItemStack[] savedEquipment) {
        this.savedEquipment = savedEquipment;
    }

    public ItemStack[] getSavedInventory() {
        return savedInventory;
    }

    public void setSavedInventory(ItemStack[] savedInventory) {
        this.savedInventory = savedInventory;
    }

    public GameMode getSavedGameMode() {
        return savedGameMode;
    }

    public void setSavedGameMode(GameMode savedGameMode) {
        this.savedGameMode = savedGameMode;
    }

    public Collection<PotionEffect> getSavedPotionEffects() {
        return savedPotionEffects;
    }

    public void setSavedPotionEffects(Collection<PotionEffect> savedPotionEffects) {
        this.savedPotionEffects = savedPotionEffects;
    }

    public int getSavedHunger() {
        return savedHunger;
    }

    public void setSavedHunger(int savedHunger) {
        this.savedHunger = savedHunger;
    }

    public double getSavedLife() {
        return savedLife;
    }

    public void setSavedLife(double savedLife) {
        this.savedLife = savedLife;
    }

    public double getSavedTotalLife() {
        return savedTotalLife;
    }

    public void setSavedTotalLife(double savedTotalLife) {
        this.savedTotalLife = savedTotalLife;
    }

    public boolean isSavedFlyState() {
        return savedFlyState;
    }

    public void setSavedFlyState(boolean savedFlyState) {
        this.savedFlyState = savedFlyState;
    }

}
