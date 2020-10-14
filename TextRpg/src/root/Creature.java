package root;

import java.util.Random;

import items.Armor;
import items.Weapon;

public class Creature {
	private String name;
	private String description;
	
	private int hp;
	private int mp;
	
	
	private Weapon weapon;
	private Armor armor;
	
	private String immunities = "";
	private String resistances = "";
	private String weakness = "";
	
	private int attackBonus;
	
	private int id;
	
	public Creature(String name, String description, int hp, int mp, Weapon weapon, int attackBonus, Armor armor,
			String immunities, String resistances, String weakness, int id) {
		this.name = name;
		this.description = description;
		this.hp = hp;
		this.mp = mp;
		this.weapon = weapon;
		this.armor = armor;
		this.immunities = immunities;
		this.resistances = resistances;
		this.weakness = weakness;
		this.id = id;
		this.attackBonus = attackBonus;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public String getImmunities() {
		return immunities;
	}

	public boolean isAlive() {
		if(this.hp > 0) {
			return true;
		}else {
			return false;
		}
	}
	public void setImmunities(String immunities) {
		this.immunities = immunities;
	}

	public String getResistances() {
		return resistances;
	}

	public void setResistances(String resistances) {
		this.resistances = resistances;
	}

	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}
	
	public void dealDamage(int damage, String damageType) {
		if(this.weakness.equals(damageType)) {
			System.out.println(this.name + " is weak to " + damageType + " damage.");
			this.hp -= 2 * damage;
		}else if(resistances.equals(damageType)) {
			System.out.println(this.name + " is resistant to " + damageType + " damage.");
			this.hp -= damage / 2;
		}else if(immunities.equals(damageType)){
			System.out.println(this.name + " is immune to " + damageType + " damage.");
		}else {
			this.hp -= damage;
		}
		
	}
	
	public boolean attackMelee(Player target) {
		Random rand = new Random();
		if(rand.nextInt(20) + 1 + attackBonus>= target.getArmor().getAc()) {
			int hit = this.weapon.getDamageNum();
			target.dealDamage(hit);
			System.out.println("\t"+ this.name + " hits " + target.getName() + " for " + hit + " " + this.getWeapon().getDamageType() + " damage.\t" + target.getHp() + " hp remaining.");
			return true;
		}else {
			System.out.println("\tThe attack by " + this.name + " missed.");
			return false;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
