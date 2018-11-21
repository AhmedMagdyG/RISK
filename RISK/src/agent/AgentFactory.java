package agent;

public class AgentFactory implements IAgentFactory {
	
	private static AgentFactory singleAgentFactor = new AgentFactory();
	
	private AgentFactory() {
		
	}
	
	public static AgentFactory getInstance() {
		return singleAgentFactor;
	}
	
	@Override
	public IAgent getAgent(AgentType type) {
		// TODO Auto-generated method stub
		return null;
	}

}
