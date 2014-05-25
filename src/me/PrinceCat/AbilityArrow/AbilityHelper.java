package me.PrinceCat.AbilityArrow;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AbilityHelper {

	public static HashMap<String, String> playerAbility = new HashMap<String, String>();

	// Set player's current ability
	public static void setAbility(String playerName, String abilityName) {
		playerAbility.put(playerName, abilityName);
	}

	// Get player's current ability
	public static String getAbility(String playerName) {
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
	public static void toggleAbility(Player player, String abilityName) {
		String playerName = player.getName();
		String selectedAbility = getAbility(playerName);

		if (selectedAbility != null) {
			if (selectedAbility.equalsIgnoreCase(abilityName)) {
				player.sendMessage(ChatColor.RED + abilityName + " disabled.");
				resetAbility(playerName);
			} else {
				player.sendMessage(ChatColor.GREEN + abilityName + " enabled.");
			}
		} else {
			player.sendMessage(ChatColor.GREEN + abilityName + " enabled.");
			setAbility(playerName ,abilityName);
		}
	}
	
	//TODO Fix bug where item is never fully removed from inventory?
	
	public static void removeAbilityItem(Player player, Material material, short damageValue) {
		if (player.getInventory().contains(new ItemStack(material, 1, damageValue))) {
			System.out.print("Inventory has item!");
			//player.getInventory().removeItem(new ItemStack(material, 1, damageValue));
		}
		
		/*
		for (int i = 0; i <= player.getInventory().getSize(); i++) {
			if (player.getInventory().getItem(i).getType().equals(material)) {
				System.out.print("Item is material!");
				if (player.getInventory().getItem(i).getDurability() == damageValue) {
					System.out.print("Item has damage value!");
					player.getInventory().removeItem(new ItemStack(player.getInventory().getItem(i).getType(), 1, damageValue));
					player.getInventory().
					break;
				}
			}
		}
		*/
		
	}

}
