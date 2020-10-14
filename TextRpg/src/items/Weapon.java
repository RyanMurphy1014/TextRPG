package items;

import java.util.Random;

public class Weapon extends Item{
	
	private String damageType;
	private	String damage;
	
	public Weapon(String name, int cost, String desc, String damageType, String damage) {
		super(name, cost, desc);
		this.damage = damage;
		this.damageType = damageType;
	}

	public String getDamageType() {
		return damageType;
	}

	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}
	
	public int getDamageNum() {
		int diceQuantity = Integer.parseInt(damage.substring(0, 1));
		int maxRoll = Integer.parseInt(damage.substring(2));
		int output = 0;
		Random rand = new Random();
		for(int i = 0; i < diceQuantity; i++) {
			output += rand.nextInt(maxRoll) + 1;
		}
		return output;
	}
	
	
	
}
