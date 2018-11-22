package graph;

import java.util.ArrayList;
import java.util.Random;

public class Continent implements IContinent {
	
	private ArrayList<INode> nodes;
	private int bonus;
	private String color;
	
	public Continent(ArrayList<INode> nodes, int bonus) {
		this.nodes = nodes;
		this.bonus = bonus;
		
		Random random = new Random();
		color = "rgb(" + String.valueOf(random.nextInt(256))
			+ "," + String.valueOf(random.nextInt(256)) + "," + String.valueOf(random.nextInt(256)) +")";
	}
	
	@Override
	public ArrayList<INode> getNodes() {
		return nodes;
	}

	@Override
	public int getBonus() {
		return bonus;
	}

	@Override
	public String getColor() {
		return color;
	}

}
