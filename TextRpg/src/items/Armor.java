package items;

public class Armor extends Item{
	private int ac;

	public Armor(String name, int cost, String desc, int ac) {
		super(name, cost, desc);
		this.ac = ac;
	}

	public int getAc() {
		return ac;
	}

	public void setAc(int ac) {
		this.ac = ac;
	}
	
	
}
