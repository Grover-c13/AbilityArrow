package me.PrinceCat.AbilityArrow;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class AbilityInventory{
	
	// Create the shop inventory
	static final Inventory shopInv = Bukkit.createInventory(null, 18, ChatColor.BOLD + "Ability Shop");
	
	// Open shop for specified player
	public static void openShop(Player player) {
		
		drawShopItems(player);
		player.openInventory(shopInv);
		
	}
	
	// Draw each of the shop items
	public static void drawShopItems(Player player) {
		ItemStack featherItem = ShopItemMeta(new ItemStack(Material.FEATHER, 1, (short)1),ChatColor.GOLD + "" + ChatColor.BOLD +  "Whirlwind", 
			Arrays.asList(
				ChatColor.AQUA + "Throws nearby enemies into the air.",
				" ",
				ChatColor.RESET + "" + ChatColor.BOLD +  "Cost:",
				displayCost(player, new ItemStack(Material.FEATHER), 4)));
		shopInv.setItem(0, featherItem);
	}
	
	// Helper method for applying Item Meta
	public static ItemStack ShopItemMeta(ItemStack itemStack, String itemName, List<String> lore) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(itemName);
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	// Helper method for calculating cost display
	public static String displayCost(Player player, ItemStack itemStack, int numItems) {
		String itemName = WordUtils.capitalizeFully(itemStack.getType().toString().replaceAll("_", " "));
		if (player.getGameMode() == GameMode.SURVIVAL) {
			if (player.getInventory().containsAtLeast(itemStack, numItems)) {
				return ChatColor.GREEN + "" + numItems + " x " + itemName;
			} else {
				return ChatColor.RED + "" + numItems + " x " + itemName;
			}
		} else {
			return ChatColor.GREEN + "" + numItems + " x " + itemName;
		}
	}
}
