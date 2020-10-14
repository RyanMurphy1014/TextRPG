package lists;

import root.Creature;

public class CreatureList {
	//public static Creature example = new Creature(name, description,             hp, mp, weapon, attackBonus, armor, immunities, resistances, weakness, id);
	public static Creature goblin = new Creature("Goblin", "A bog standard goblin", 12, 0, WeaponList.ironDagger, 2, ArmorList.leather, "", "", "", 1);
	public static Creature kobold = new Creature("Kobold", "A small red lizardman", 5, 0, WeaponList.ironDagger, 4, ArmorList.cloth, "", "", "", 2);
}
