package state;

import java.util.ArrayList;

public class State implements Comparable<State> {
	private int cost;
	private ArrayList<Integer> soldiers;
	private ArrayList<Boolean> nodeOwner;
	private State parent;

	private int lastDeploy;
	private int lastAttackFrom, lastAttackTo, lastAttackSoldiers;

	public State(int cost, ArrayList<Integer> soldiers, ArrayList<Boolean> nodeOwner, int lastDeploy,
			int lastAttackFrom, int lastAttackTo, int lastAttackSoldiers) {
		this.cost = cost;
		this.soldiers = soldiers;
		this.nodeOwner = nodeOwner;
		this.lastDeploy = lastDeploy;
		this.lastAttackFrom = lastAttackFrom;
		this.lastAttackTo = lastAttackTo;
		this.lastAttackSoldiers = lastAttackSoldiers;
	}

	public State getParent() {
		return parent;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public ArrayList<Integer> getSoldiers() {
		return soldiers;
	}

	public ArrayList<Boolean> getNodeOwner() {
		return nodeOwner;
	}

	public boolean equals(State s) {
		return cost == s.getCost() && soldiers.equals(s.getSoldiers()) && nodeOwner.equals(s.getNodeOwner());
	}

	public boolean gameOver() {
		for (int i = 1; i < nodeOwner.size(); ++i)
			if (nodeOwner.get(0) != nodeOwner.get(i))
				return false;
		return true;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}

	public int getLastDeploy() {
		return lastDeploy;
	}

	public int getLastAttackFrom() {
		return lastAttackFrom;
	}

	public int getLastAtatckTo() {
		return lastAttackTo;
	}

	public int getLastAttackSoldiers() {
		return lastAttackSoldiers;
	}

	@Override
	public int compareTo(State arg0) {
		if (cost < arg0.cost)
			return -1;
		else if (cost > arg0.cost)
			return 1;
		else if (equals(arg0))
			return 0;
		int soldierCompare = (this.getSoldiers().toString()).compareTo(arg0.getSoldiers().toString());
		if (soldierCompare != 0)
			return soldierCompare;
		int ownerCompare = this.getNodeOwner().toString().compareTo(arg0.getNodeOwner().toString());
		if (ownerCompare != 0)
			return ownerCompare;
		return 0;
	}

}
