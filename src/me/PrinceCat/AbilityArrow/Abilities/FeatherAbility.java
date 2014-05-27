package me.PrinceCat.AbilityArrow.Abilities;

import java.util.List;

import me.PrinceCat.AbilityArrow.AbilityHelper;
import me.PrinceCat.AbilityArrow.BaseAbility;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

// we are no longer static, it doesnt work well for inheriting from base class - also not neccessary as the ENUM these clases are loaded to should be static.
public class FeatherAbility extends BaseAbility {

	// Activates this ability
	// removed static
	public void activate(Player player, LivingEntity target) {
		String playerName = player.getName();

		// Remove ability arrow from player's inventory
		if (player.getGameMode() == GameMode.SURVIVAL) {
			AbilityHelper.removeAbilityItem(player, Material.FEATHER,(short) 1);
		}
		
		 // Apply to original target
		 target.setVelocity(new Vector(0, 1, 0));
		 target.damage(0D);

		 List<Entity> areaEffect = target.getNearbyEntities(2, 2, 2);
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
			 AbilityHelper.resetAbility(playerName);
	}
	
	public String toString()
	{
		return "Feather Ability Object";
	}

	@Override
	public String getUsableName() {
		return "Whirlwind";
	}
}
