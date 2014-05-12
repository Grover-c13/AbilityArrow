package me.PrinceCat.AbilityArrow.Abilities;

import java.util.List;

import me.PrinceCat.AbilityArrow.AbilityHelper;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class FeatherAbility extends AbilityHelper{

	public static void activate(Player player, LivingEntity target) {
		String playerName = player.getName();
		
		// Remove item from player's inventory if they are in survival
		 if (player.getGameMode() == GameMode.SURVIVAL) {
			 if (player.getInventory().containsAtLeast(new ItemStack(Material.FEATHER), 4)) {
				 player.getInventory().removeItem(new ItemStack(Material.FEATHER, 4));
			 } else {
				 player.sendMessage(ChatColor.RED + "Whirlwind failed...");
   			     resetAbility(playerName);
   			 return;
			 }
		 }
			 
		 target.setVelocity(new Vector(0, 1, 0));
		 target.damage(0D);
			 
		 List<Entity> areaEffect = target.getNearbyEntities(5, 5, 5);
		 for (Entity subject : areaEffect) {
			 if (subject instanceof LivingEntity) {
				 if (subject instanceof Player) {
					 Player targetPlayer = (Player) subject;
					 if (targetPlayer.getName().equals(playerName)) {
						 continue;
					 }
					 
					 if (targetPlayer.getGameMode().equals(GameMode.CREATIVE)) {
						 continue;
					 }
				 }
				 
			 LivingEntity areaTarget = (LivingEntity) subject;
			 areaTarget.setVelocity(new Vector(0, 1, 0));
       		 	 areaTarget.damage(0D); //Interesting bug, setVelocity() only works with this?
			 }
	     }
			 player.sendMessage(ChatColor.GOLD + "Used Whirlwind!");
			 resetAbility(playerName);
	}
	
}
