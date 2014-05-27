package me.PrinceCat.AbilityArrow;
import me.PrinceCat.AbilityArrow.Abilities.FeatherAbility;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

// RENAMED TO EnumAbility to make sure its clear that this is the enumeration of abilities and not a base class.


public enum EnumAbility {
	// instead of class we point it to the base ability class
	FEATHER((BaseAbility) new FeatherAbility());
	// we cast down to the most common class as we cant have the enum have different values.
	//private final String usableName;  THIS SHOULD BE IN THE ABILITY IT SELF
	private final BaseAbility base; // changed from className because its not really the name of the class! its the actual class (also class cant be used)
	
	private EnumAbility(BaseAbility base) {
		//this.usableName = usableName; THIS SHOULD BE IN THE ABILITY IT SELF AND NOT IN THE ENUM, BECAUSE IT IS ASSOCIATED WITH THE ABILITY
		// THINK OF A ENUM AS A POINTER FOR YOU TO USE, ITS HANDY IF YOU NEED TO CONVERT TEXT TO A VALUE AND SO YOU HAVE A CONSISTENT WAY TO GET VALUES OF THINGS
		this.base = base;
	}

	
	// ABILITY CLASS METHODS
	
	// REDUNDANT - this defeats the point of having it in an ENUM, you want a key that is consistent that points to a value that handles your methods
	public void doAbility(Player player, LivingEntity target) {
			base.activate(player, target);
	}
	
	// return the ability associated with this enum
	public BaseAbility getAbility()
	{
		return this.base;
	}
	
}
