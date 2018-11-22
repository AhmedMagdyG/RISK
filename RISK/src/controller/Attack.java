package controller;

import graph.INode;

public class Attack {
	private INode from, to;
	private int soldiers;

	public Attack(INode from, INode to, int soldiers) {
		this.from = from;
		this.to = to;
		this.soldiers = soldiers;
	}
	
	public INode getFrom() {
		return from;
	}
	
	public INode getTo() {
		return to;
	}
	
	public int getSoldiers() {
		return soldiers;
	}
	
}
