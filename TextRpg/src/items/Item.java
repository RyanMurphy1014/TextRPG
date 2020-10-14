package items;

public class Item {
	private String name;
	private int cost;
	private String desc;
	
	/**
	 * @param name - Name of Item
	 * @param cost - Cost of Item
	 * @param desc - Description of Item
	 */
	public Item(String name, int cost, String desc) {
		this.name = name;
		this.cost = cost;
		this.desc = desc;
	}
}
