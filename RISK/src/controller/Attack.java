package controller;

import graph.INode;

public class Attack implements IAttack{
	private INode from, to;
	private int soldiers;

	public Attack(INode from, INode to, int soldiers) {
		this.from = from;
		this.to = to;
		this.soldiers = soldiers;
	}

	@Override
	public INode getFrom() {
		return from;
	}

	@Override
	public INode getTo() {
		return to;
	}

	@Override
	public int getSoldiers() {
		return soldiers;
	}
}
