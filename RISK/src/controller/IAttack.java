package controller;

import graph.INode;

public interface IAttack {
	public INode getFrom();
	
	public INode getTo();
	
	public int getSoldiers();
	
}
