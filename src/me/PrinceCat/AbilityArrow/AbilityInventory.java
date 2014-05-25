package me.PrinceCat.AbilityArrow;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class AbilityInventory{
	static final Inventory shopInv = Bukkit.createInventory(null, 18, ChatColor.BOLD + "Ability Shop");
	
	public static void openShop(Player player) {
		
		drawShopItems(player);
		player.openInventory(shopInv);
		
	}
	
	public static void drawShopItems(Player player) {
		ItemStack featherItem = ShopItemMeta(new ItemStack(Material.FEATHER, 1,(short) 1),ChatColor.GOLD + "" + ChatColor.BOLD +  "Whirlwind", 
				Arrays.asList(
				ChatColor.AQUA + "Throws nearby enemies into the air.",
				" ",
				ChatColor.RESET + "" + ChatColor.BOLD +  "Cost:",
				displayCost(player, new ItemStack(Material.FEATHER), 4)));

shopInv.setItem(0, featherItem);
	}
	
	public static ItemStack ShopItemMeta(ItemStack itemStack, String itemName, List<String> lore) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(itemName);
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static String displayCost(Player player, ItemStack itemStack, int numItems) {
		String itemName = WordUtils.capitalizeFully(itemStack.getType().toString().replace("_", " "));
		if (player.getInventory().containsAtLeast(itemStack, numItems)) {
			return ChatColor.GREEN + "" + numItems + " x " + itemName;
		} else {
			return ChatColor.RED + "" + numItems + " x " + itemName;
		}
	}
}
