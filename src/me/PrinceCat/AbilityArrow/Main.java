package me.PrinceCat.AbilityArrow;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("abilityshop")) {
				AbilityInventory.openShop(player);
				return true;
			}
		}
		return false; 
	}
	
	public void onEnable() {
		//Register events
		this.getServer().getPluginManager().registerEvents(new EventListener(), this);
	}

	public void onDisable() {
		getLogger().info("AbilityArrow disabled");
		AbilityHelper.playerAbility.clear();
		AbilityHelper.playerAbility = null;
	}

}
