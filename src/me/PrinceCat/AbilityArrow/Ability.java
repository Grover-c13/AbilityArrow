package me.PrinceCat.AbilityArrow;

import java.lang.reflect.InvocationTargetException;

import me.PrinceCat.AbilityArrow.Abilities.FeatherAbility;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;


public enum Ability {
	FEATHER("Whirlwind", FeatherAbility.class);
	
	private final String usableName;
	private final Class<?> className;
	
	private Ability(String usableName, Class<?> className) {
		this.usableName = usableName;
		this.className = className;
	}
	
	public String getUsableName() {
		return usableName;
	}
	
	public Class<?> getClassName() {
		return className;
	}
	
	// ABILITY CLASS METHODS
	
	public void doAbility(Player player, LivingEntity target) {
		try {
			this.getClassName().getDeclaredMethod("activate", Player.class, LivingEntity.class).invoke(null, player, target);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
	}
}
