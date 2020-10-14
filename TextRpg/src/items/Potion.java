package items;

public class Potion extends Item{
	private String target;
	private int amount;
	
	public Potion(String name, int cost, String desc, String target, int amount) {
		super(name, cost, desc);
		this.target = target;
		this.amount = amount;
	}
}
