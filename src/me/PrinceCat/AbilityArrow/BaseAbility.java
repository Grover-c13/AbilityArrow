package me.PrinceCat.AbilityArrow;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public abstract class BaseAbility {
	public abstract void activate(Player player, LivingEntity target);
	public abstract String getUsableName();
}
