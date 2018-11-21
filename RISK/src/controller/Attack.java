package controller;

import graph.INode;

public class Attack {
	INode from, to;
	int numberOfArmies;

	public Attack(INode from, INode to, int numberOfArmies) {
		this.from = from;
		this.to = to;
		this.numberOfArmies = numberOfArmies;
	}
}
