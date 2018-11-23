package graph;

import java.util.ArrayList;
import java.util.Random;

public class Continent implements IContinent {
	
	private ArrayList<INode> nodes;
	private int bonus;
	private String color;
	private int id;
	
	public Continent(ArrayList<INode> nodes, int bonus, int id) {
		this.nodes = nodes;
		this.bonus = bonus;
		this.id = id;
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

	@Override
	public int getId() {
		return id;
	}

}
