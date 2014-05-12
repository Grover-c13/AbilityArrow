package me.PrinceCat.AbilityArrow;

import me.PrinceCat.AbilityArrow.Abilities.FeatherAbility;

import org.bukkit.Bukkit;
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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			if(player.getItemInHand().getType().equals(Material.FEATHER)) {
				AbilityHelper.toggleAbility(player, "Whirlwind");
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.LOW)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		
		// Check if the entity is a living entity
         if (!(event.getEntity() instanceof LivingEntity)){
        	 return;
         }
        
         // Check if the damage was dealt by a projectile
         if (!(event.getDamager() instanceof Projectile)){
        	 return;
         }
        
         // Check if the player fired the projectile
         Projectile pro = (Projectile)event.getDamager();
         if (!(pro.getShooter() instanceof Player)){
        	 return;
         }
         
         // Check if projectile is an arrow
         if (!(pro instanceof Arrow)) {
        	 return;
         }
         
         // Check if player didn't hit themselves
         if (pro.getShooter() instanceof Player) {
        	 if (event.getEntity().equals(pro.getShooter())) {
        		 return;
        	 }
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
	
	public void onEnable() {
		getLogger().info("AbilityArrow enabled");
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, this);
	}
	
	public void onDisable() {
		getLogger().info("AbilityArrow disabled");
	}
	
}
