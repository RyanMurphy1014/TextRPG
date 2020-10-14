package root;
import java.util.Random;

import items.*;
import rooms.Map;
import rooms.Room;

public class Player {
	private int hp;
	private int mp;
	private int stam;
	
	private String name;
	private String race;
	
	private Weapon weapon;
	private Armor armor;
	
	private boolean inCombat = false;
	private int attackBonus;
	
	private Map map;
	private Room currentRoom;
	
	public Player(int hp, int mp, int stam, String name, String race, Weapon weapon, Armor armor) {
		this.map = new Map();
		this.currentRoom = map.pond;
		
		
		this.hp = hp;
		this.mp = mp;
		this.stam = stam;
		this.name = name;
		this.race = race;
		this.weapon = weapon;
		this.armor = armor;
		this.attackBonus = getAttackBonus();
	}
	
	
	
	public void attackMelee(Creature target) {
		Random rand = new Random();
		if(rand.nextInt(20) + 1 + this.attackBonus >= target.getArmor().getAc()) {
			int hit = this.weapon.getDamageNum();
			target.dealDamage(hit, weapon.getDamageType());
			System.out.println("\t" + this.name + " hits " + target.getName() + " for " + hit + " " + this.getWeapon().getDamageType() + " damage.\t" + target.getHp() + " hp remaining.");
		}else {
			System.out.println("\tThe attack by " + this.name + " missed.");
		}
	}
	
	public void dealCrit(Creature target, int multiple) {
		int hit = this.weapon.getDamageNum() * (multiple + 1);
		target.dealDamage(hit, weapon.getDamageType());
		System.out.println("\t" + this.name + " hits " + target.getName() + " for " + hit + " " + this.getWeapon().getDamageType() + " CRITICAL damage.\t" + target.getHp() + " hp remaining.");
	}

	public Room getCurrentRoom() {
		return this.currentRoom;
	}
	
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
	
	public int getHp() {
		return hp;
	}

	public int getAttackBonus() {
		int output = 0;
		switch(this.stam) {
		case 0:
			output =-5;
			break;
		case 1:
			output = -5;
			break;
		case 2:
			output = -4;
			break;
		case 3:
			output = -4;
			break;
		case 4:
			output = -3;
			break;
		case 5:
			output = -3;
			break;
		case 6:
			output = -2;
			break;
		case 7:
			output = -2;
			break;
		case 8:
			output = -1;
			break;
		case 9:
			output = -1;
			break;
		case 10:
			output = 0;
			break;
		case 11:
			output = 0;
			break;
		case 12:
			output = 1;
			break;
		case 13:
			output = 1;
			break;
		case 14:
			output = 2;
			break;
		case 15:
			output = 2;
			break;
		case 16:
			output = 3;
			break;
		case 17:
			output = 3;
			break;
		case 18:
			output = 4;
			break;
		case 19:
			output = 4;
			break;
		case 20:
			output = 5;
			break;
		case 21:
			output = 5;
			break;
		case 22:
			output = 6;
			break;
		case 23:
			output = 6;
			break;
		case 24:
			output = 7;
			break;
		case 25:
			output = 7;
			break;
		case 26:
			output = 8;
			break;
		case 27:
			output = 8;
			break;
		case 28:
			output = 9;
			break;
		case 29:
			output = 9;
			break;
		case 30:
			output = 10;
			break;
		default:
			output =  -5;
			break;
		
		}
		return output;
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

	public int getStam() {
		return stam;
	}

	public boolean isAlive() {
		if(this.hp > 0) {
			return true;
		}else {
			return false;
		}
	}
	public void setStam(int stam) {
		this.stam = stam;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
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

	public void dealDamage(int hit) {
		this.hp -= hit;
		
	}

	public boolean isInCombat() {
		return inCombat;
	}

	public void setInCombat(boolean inCombat) {
		this.inCombat = inCombat;
	}
}
