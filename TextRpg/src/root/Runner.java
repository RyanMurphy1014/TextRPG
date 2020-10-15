package root;

import java.util.Scanner;

import items.Armor;
import items.Weapon;
import lists.ArmorList;
import lists.CreatureList;
import lists.WeaponList;
import rooms.Map;

public class Runner {
	public static void main(String [] args) {
		
		
		/*
		 * Uncomment line 19 to use a premade character and comment line 20.
		 */
		//Player player = new Player(15, 0, 15, "Ryan", "Human", WeaponList.ironDagger, ArmorList.leather);
		Player player = characterCreator();
		
				
				
		while(!gameOver) {
			travel(player);
		}
		
		
	}
	
	public static boolean gameOver = false;
	public static Player characterCreator() {
		Scanner scan = new Scanner(System.in);
		System.out.println("What is the name of your character: ");
		String name = scan.nextLine();
		System.out.println("What is your Race - Human, Elf, Orc, Dwarf: ");
		String race = scan.nextLine();
		System.out.print("What weapon do you weild - Iron Dagger: ");
		Weapon equiped;
		switch(scan.nextLine()) {
		case "Iron Dagger":
			equiped = WeaponList.ironDagger;
			break;
		default:
			equiped = WeaponList.ironDagger;
		}
		System.out.println("What armor do you weild - Leather");
		Armor worn;
		switch(scan.nextLine()) {
		case "Leather":
			worn = ArmorList.leather;
			break;
		default:
			worn = ArmorList.leather;
		}
		
		int hp = 0;
		int mp = 0;
		int stam = 0;
		System.out.println("Would you like to BUY points or take standard ARRAY");
		boolean useArray = false;
		if(scan.nextLine().toUpperCase().equals("BUY")) {
			System.out.println("HP is your health, MP is your magic points - spend these to cast spells, Stamina is your likelyhood to hit a target - spend to do critical attacks.");
			boolean doneCreating = false;
			while(doneCreating == false) {
				int pointsRemaining = 30;
				System.out.println("How many points to spend in HP\tPoints Remaining: " + pointsRemaining);
				hp = scan.nextInt();
				pointsRemaining -= hp;
				
				System.out.println("How many points to spend in MP\tPoints Remaining: " + pointsRemaining);
				mp = scan.nextInt();
				pointsRemaining -= mp;
				
				System.out.println("Stamina will use the remaining balance of " + pointsRemaining + " points");
				stam = pointsRemaining;
				pointsRemaining -= stam;
				
				if(stam < 0) {
					System.out.println("You have made an error with creationg your character. Please restart.\n");
				}else {
					System.out.println("\nAre you happy with your choices y/n?");
					System.out.println("HP: " + hp + "\tMP: " + mp +"\tStamina: " + stam);
					
					if(scan.nextLine().equals("y")){
						System.out.println("Your character has been created.");
						doneCreating = true;
					}else {
						System.out.println("You may restart your character creation.");
					}
				}
			}
		}else {
			hp = 15;
			mp = 5;
			stam = 10;
			System.out.println("Your standard array stats are: \nHP: " + hp + "\tMP: " + mp +"\tStamina: " + stam);
		}
		return new Player(hp, mp, stam, name, race, equiped, worn);
	}
	
	public static boolean autoFight(Player player, Creature creature) {
		while(creature.getHp() > 0 && player.getHp() > 0) {
			if(player.isAlive()) {
				player.attackMelee(creature);
			}
			if(creature.isAlive()) {
				creature.attackMelee(player);
			}
		}
		if(player.getHp() < 0) {
			System.out.println("\nGoblin wins");
			return false;
		}else {
			System.out.println("\nRyan wins");
			printStats(player);
			return true;
		}
	}
	
	public static boolean fight(Player player, Creature creature) {
		player.setInCombat(true);
		boolean output = true;
		Scanner scan = new Scanner(System.in);
		while(player.isInCombat()) {
			System.out.println("\nAttack, Magic, Strong Attack, or Flee");
			
			switch(scan.nextLine().toLowerCase()) {
			case "attack":
				if(creature.getHp() > 0 && player.getHp() > 0) {
					if(player.isAlive()) {
						player.attackMelee(creature);
					}
					if(creature.isAlive()) {
						creature.attackMelee(player);
					}
					
					if(!player.isAlive()) {
						System.out.println("\nGoblin wins");
						output = false;
						player.setInCombat(false);
						break;
					}
					
					if(!creature.isAlive()) {
						System.out.println("\nRyan wins");
						printStats(player);
						player.setInCombat(false);
						output = true;
						break;
					}
				}
				
				break;
			case "magic":
				System.out.println("This part of the game is under construction.");
				break;
			case "strong attack":
				System.out.println("How many points of stamina to spend? - " + player.getStam() + " points available. (One point of stamina multiplies the damage and is a guaranteed hit.)");
				int pointsSpent = scan.nextInt();
				scan.nextLine();
				if(pointsSpent < player.getStam()) {
					player.dealCrit(creature, pointsSpent);
					player.setStam(player.getStam() - pointsSpent);
					if(!creature.isAlive()) {
						System.out.println("\nRyan wins");
						printStats(player);
						player.setInCombat(false);
						output = true;
					}
					
				}else {
					System.out.println("That is not a valid number of points.");
				}
				
				break;
			case "flee":
				int hit = creature.getWeapon().getDamageNum();
				player.dealDamage(hit);
				System.out.println("\nThe enemy lands a free hit on you as you disengage. You are hurt but you are able to flee.");
				System.out.println("You take " + hit + " damage");
				player.setInCombat(false);
				printStats(player);
				break;
			default:
				System.out.println("That is not a valid option");
			}
		}
		return output;
	}
	
	
	public static void travel(Player player) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		boolean hasEnemy = false;
		Scanner scan = new Scanner(System.in);
		System.out.println(player.getCurrentRoom().getDescription());
		System.out.print("Which way do you travel, North, South, East, West ");
		if(player.getCurrentRoom().hasEnemy()) {
			hasEnemy = true;
			System.out.print(". Fight, or AutoFight.\n");
			System.out.println("\t" + player.getCurrentRoom().listEnemy() + " appears!");
		}
		String direction = scan.nextLine().toLowerCase();
		if(direction.equals("autofight")) {
			if(player.getCurrentRoom().hasEnemy()) {
				if(autoFight(player, player.getCurrentRoom().getEnemies().get(0)) == true) {
					player.getCurrentRoom().getEnemies().clear();
				}else {
					System.out.println("\n\nGame Over");
					gameOver = true;
					
				}
			}else {
				System.out.println("There are no enemies in this area.");
			}
			
		}
		else if(direction.contentEquals("fight")){
			if(player.getCurrentRoom().hasEnemy()) {
				if(fight(player, player.getCurrentRoom().getEnemies().get(0)) == true) {
					player.getCurrentRoom().getEnemies().clear();
				}else {
					System.out.println("\n\nGame Over");
					gameOver = true;
					
				}
			}else {
				System.out.println("There are no enemies in this area.");
			}
		}else {
			if(player.getCurrentRoom().hasNextRoom(direction)) {
				if(player.getCurrentRoom().hasEnemy()) {
					if(player.getCurrentRoom().getEnemies().get(0).attackMelee(player)) {
						System.out.println("The enemy prevents you from escaping.");
					}else {
						System.out.println("You have fled sucessfully.");
					}
				}else {
					player.setCurrentRoom(player.getCurrentRoom().go(direction));
				}
			}else {
				System.out.println("There is nothing in that direction.");
			}
		}
	}
	public static void printStats(Player player) {
		System.out.println("\nHP: " + player.getHp() + "\tMP: " + player.getMp() + "\tStamina: " + player.getStam() + "\n");
	}
}
