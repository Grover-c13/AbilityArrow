package me.PrinceCat.AbilityArrow.Abilities;

public enum Ability {
	FEATHER("Whirlwind");
	
	private final String usableName;
	
	private Ability(String usableName) {
		this.usableName = usableName;
	}
	
	public String getUsableName() {
		return usableName;
	}
}
