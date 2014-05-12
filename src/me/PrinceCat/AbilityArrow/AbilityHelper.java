package me.PrinceCat.AbilityArrow;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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
	
}
