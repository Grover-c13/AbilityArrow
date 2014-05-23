package me.PrinceCat.AbilityArrow;

import me.PrinceCat.AbilityArrow.Abilities.FeatherAbility;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.LOW)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

	// Check if the entity is a living entity
         if (!(event.getEntity() instanceof LivingEntity)){
        	 return;
         }
         
         // Check if the damage was dealt by an arrow
         if (!(event.getDamager() instanceof Arrow)){
        	 return;
         }
        
         Projectile pro = (Projectile) event.getDamager();
         
         // Check if player hit themselves
         if (pro.getShooter() instanceof Player) {
        	 if (event.getEntity().equals(pro.getShooter())) {
        		 return;
        	 }
         } else {
         	 return;
         }
        
         // Declarations
         final Player player = (Player)pro.getShooter();
         final String playerName = player.getName();
         final LivingEntity target = (LivingEntity) event.getEntity();
         
         String selectedAbility = AbilityHelper.getAbility(playerName);
         
         if (selectedAbility != null) {
        	 if (selectedAbility.equalsIgnoreCase("Whirlwind")) {
        		 FeatherAbility.activate(player, target);
        	 }
         }
	}
	
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			if (player.getItemInHand().getItemMeta().equals(null)) {
				return;
			}
			
			if(player.getItemInHand().getType().equals(Material.FEATHER)) {
				if (player.getItemInHand().getDurability() == 1) {
					AbilityHelper.toggleAbility(player, "Whirlwind");
					}
				}
			}
		}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {

		//Check if the inventory is the Ability Shop
		if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Ability Shop")) {
			return;
		}
		
		Player player = (Player) event.getWhoClicked();
		ItemStack clickedItem = event.getCurrentItem();
		if (clickedItem.getItemMeta().equals(null) || clickedItem.getItemMeta().getDisplayName().equals(null)) {
			return;
		}
			String itemName = clickedItem.getItemMeta().getDisplayName();
			itemName = ChatColor.stripColor(itemName);
		
		//Check if the clicked item is not null or air
		if (clickedItem.equals(null) || clickedItem.getType().equals(Material.AIR)) {
			return;
		}
		
		switch (itemName) {
		case "Whirlwind":
			if (player.getGameMode() == GameMode.SURVIVAL) {
				if (player.getInventory().containsAtLeast(new ItemStack(Material.FEATHER), 4)) {
					player.getInventory().removeItem(new ItemStack(Material.FEATHER, 4));
				} else {
					player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You don't have enough to buy this item!");
					event.setCancelled(true);
					return;
				}
			}
				 player.getInventory().addItem(clickedItem);
				 player.updateInventory();
				 event.setCancelled(true);
		}
		 AbilityInventory.drawShopItems(player);
	}
}
