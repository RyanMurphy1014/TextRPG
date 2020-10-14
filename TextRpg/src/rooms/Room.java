package rooms;

import java.util.ArrayList;
import java.util.Collection;

import root.Creature;

public class Room {
	
	private String description;
	private String roomType;
	
	private Room north = null;
	private Room south = null;
	private Room west = null;
	private Room east = null;
	private ArrayList<Creature> enemies = new ArrayList<Creature>();
	
	
	public Room(String description, String roomType) {
		this.description = description;
		this.roomType = roomType;
	}
	
	public void addEnemy(Creature enemy) {
		enemies.add(enemy);
	}
	
	public ArrayList<Creature> getEnemies(){
		return enemies;
	}
	
	public boolean hasEnemy() {
		if(enemies.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

	public String listEnemy() {
		String output = "";
		int count = 0;
		for(int i = 0 ; i < enemies.size(); i++) {
			if(output.equals("")) {
				if(enemies.size() == 1) {
					return "1 " + enemies.get(0).getName();
				}else {
					if(enemies.get(i + 1).equals(enemies.get(i))){
						count++;
					}else {
						output += count + enemies.get(i - 1).getName();
					}
				}
			}else {
				count = 0; 
				if(enemies.get(i + 1).equals(enemies.get(i))){
					count++;
				}else {
					output += count + enemies.get(i - 1).getName();
				}
			}
		}
		return output;
	}
	
	
	public String getDescription() {
		return description;
	}



	public String getRoomType() {
		return roomType;
	}





	public Room getNorth() {
		return north;
	}

	public Room go(String direction) {
		Room output = null;
		switch (direction) {
		case "north":
			output = this.north;
			break;
		case "south":
			output = this.south;
			break;
		case "east":
			output = this.east;
			break;
		case "west":
			output = this.west;
			break;
		}
		return output;
	}

	public boolean hasNextRoom(String direction) {
		if(direction.equals("north") && this.north != null) {
			return true;
		}else if(direction.equals("south") && this.south != null) {
			return true;
		}else if(direction.equals("west") && this.west != null) {
			return true;
		}else if(direction.equals("east") && this.east != null) {
			return true;
		}else {
			return false;
		}
	}

	public void setNorth(Room north) {
		this.north = north;
	}





	public Room getSouth() {
		return south;
	}





	public void setSouth(Room south) {
		this.south = south;
	}





	public Room getWest() {
		return west;
	}





	public void setWest(Room west) {
		this.west = west;
	}





	public Room getEast() {
		return east;
	}





	public void setEast(Room east) {
		this.east = east;
	}
	
	
	
}
