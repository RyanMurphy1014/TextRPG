package rooms;

import lists.CreatureList;

public class Map {
	
	
	
	public Room startingArea = new Room("You are at the front gates of the town of Drellin's Ferry", "Town");
	
	public Room oldTree = new Room("The path forks at an old dead willow tree that has burn marks and a piece broken off as if it was struck by lightening"
			+ " at one point. The path splits north and south.", "Forest");
	
	public Room footOfMountain = new Room("This path makes its way towards to foothills of a nearby mountain range", "Forest");
	
	public Room pond = new Room("A small pond is found while walking along the country side", "Forest");
	
	
	public Map() {
		
		setRelation(startingArea, oldTree, "east");
		setRelation(oldTree, pond, "south");
		setRelation(oldTree, footOfMountain, "north");
		pond.addEnemy(CreatureList.goblin);
		//System.out.println("\n\t\t " + pond.getNorth().getDescription()+ "\n");
		
		
	}
	/**
	 * Sets the pathway leading form one room to another.
	 * 
	 * @param from - The room the player would currently be in
	 * @param to - The room the player will travel to
	 * @param directionTraveling - What direction you have to travel out of the from room to get to the to room
	 */
	public void setRelation(Room from, Room to, String directionTraveling) {
		switch(directionTraveling) {
			case "east":
				from.setEast(to);
				to.setWest(from);
				break;
			case "west":
				from.setWest(to);
				to.setEast(from);
				break;
			case "north":
				from.setNorth(to);
				to.setSouth(from);
				break;
			case "south":
				from.setSouth(to);
				to.setNorth(from);
				break;
		}
	}
}
