package agent;

public class AgentFactory implements IAgentFactory {
	
	private static AgentFactory singleAgentFactor = new AgentFactory();
	
	private AgentFactory() {
		
	}
	
	public static AgentFactory getInstance() {
		return singleAgentFactor;
	}
	
	@Override
	public IAgent getAgent(AgentType type, boolean player) {
		switch (type) {
			case HUMAN:
				return new HumanAgent(type, player);
			case PASSIVE:
				return new PassiveAgent(type, player);
			case AGGRESSIVE:
				return new AggressiveAgent(type, player);
			case PACIFIST:
				return new PacifistAgent(type, player);
			default:
				return null;
		}	
	}

}
