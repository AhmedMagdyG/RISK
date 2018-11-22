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
		switch (type) {
			case PASSIVE:
				return new PassiveAgent(type);
			case AGGRESSIVE:
				return new AggressiveAgent(type);
			case PACIFIST:
				return new PacifistAgent(type);
			default:
				return null;
		}	
	}

}
