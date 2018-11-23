package graph;


public class Edge implements IEdge{

	private INode u,v;
	private int id;
	
	public Edge(INode u, INode v, int id) {
		this.u = u;
		this.v = v;
		this.id = id;
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public INode getU() {
		return u;
	}

	@Override
	public INode getV() {
		return v;
	}

}
