package graph;

public class Node implements INode{
	boolean ownerType;
	int value;
	
	public Node(boolean ownerType, int value) {
		this.value = value;
		this.ownerType = ownerType; 
	}
	
	public Node(){}
	
	@Override
	public boolean getOwnerType() {
		return ownerType;
	}

	@Override
	public void setOwnerType(boolean curOwnerType) {
		ownerType = curOwnerType;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public void setValue(int value) {
		this.value = value;
	}
	
	
}
