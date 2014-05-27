package me.PrinceCat.AbilityArrow;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AbilityHelper {

	public static HashMap<String, Ability> playerAbility = new HashMap<String, Ability>();

	// Set player's current ability
	public static void setAbility(String playerName, Ability abilityName) {
		playerAbility.put(playerName, abilityName);
	}

	// Get player's current ability
	public static Ability getAbility(String playerName) {
		if (playerAbility.containsKey(playerName)) {
			return playerAbility.get(playerName);
		}
		return null;
	}

	// Remove player from ability HashMap
	public static void resetAbility(String playerName) {
		if (playerAbility.containsKey(playerName)) {
			playerAbility.remove(playerName);
		}
	}

	// Toggle a player's selected ability
	public static void toggleAbility(Player player, Ability abilityName) {
		String playerName = player.getName();
		Ability selectedAbility = getAbility(playerName);
		
		if (selectedAbility == null) {
			player.sendMessage(ChatColor.GREEN + abilityName.getUsableName() + " enabled.");
			setAbility(playerName ,abilityName);
			return;
		}
		
		String usableName = selectedAbility.getUsableName();

		if (selectedAbility.equals(abilityName)) {
			player.sendMessage(ChatColor.RED + usableName + " disabled.");
			resetAbility(playerName);
		} else {
			player.sendMessage(ChatColor.GREEN + usableName + " enabled.");
		}
	}
	
	//TODO Make removeAbilityItem accept ItemStack as its second parameter
	
	// Remove the ability item from the player's inventory
	public static void removeAbilityItem(Player player, Material material, short damage) {
		//Very messy fix, but works?
		for (int i = 0; i <= player.getInventory().getSize(); i++) {
			if (player.getInventory().getItem(i).getType().equals(material) && player.getInventory().getItem(i).getDurability() == damage) {
				int itemAmount = player.getInventory().getItem(i).getAmount();
				if (itemAmount > 1) {
					player.getInventory().getItem(i).setAmount(itemAmount - 1);
				} else {
					player.getInventory().removeItem(new ItemStack(player.getInventory().getItem(i)));
				}
				return;
			}
		}
	}

}
