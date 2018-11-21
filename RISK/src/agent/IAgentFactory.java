package agent;

public interface IAgentFactory {
	IAgent getAgent(AgentType type);
}
