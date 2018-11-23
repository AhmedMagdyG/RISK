package agent;

import java.util.ArrayList;
import java.util.Collections;

import controller.Attack;
import graph.IContinent;
import graph.IGraph;
import graph.INode;
import state.State;
import state.StateContainer;

public class RTAStarAgent implements IAgent{

	private final int DEPTH_LIMIT = 2;
	private AgentType agentType;
	private boolean player;
	private boolean lastTurnAttack;

	// The best sequence of moves found by the search algorithm
	private int deployIndex, attackIndex;
	private ArrayList<INode> deploySequence;
	private ArrayList<Attack> attackSequence;

	public RTAStarAgent(AgentType agentType, boolean player, IGraph graph) {
		this.agentType = agentType;
		this.player = player;
		this.lastTurnAttack = false;
		
		deployIndex = attackIndex = 0;
		deploySequence = new ArrayList<INode>();
		attackSequence = new ArrayList<Attack>();
		
		preprocess(graph);
	}
	
	@Override
	public AgentType getAgentType() {
		return this.agentType;
	}

	@Override
	public Attack attack(IGraph graph) {
		return attackSequence.get(attackIndex++);
	}

	@Override
	public INode deploy(IGraph graph, int soldiers) {
		return deploySequence.get(deployIndex++);
	}

	@Override
	public boolean getPlayer() {
		return player;
	}

	@Override
	public boolean lastTurnAttack() {
		return lastTurnAttack;
	}
	
	@Override
	public void setLastTurnAttack(boolean lastTurnAttack) {
		this.lastTurnAttack = lastTurnAttack;
	}
	
	private State getBestNextState(State S, 
							StateContainer visited, StateContainer visited1, IGraph graph) {
		
		if(S == null) return null;

		State bestState = S;
		StateContainer frontier = new StateContainer();
		frontier.add(S);
		for(int depth = 1; depth < DEPTH_LIMIT; ++depth) {
			StateContainer nextFrontier = new StateContainer();
			while (!frontier.isEmpty()) {
				State cur = frontier.getMin();
				
				if(cur.getCost() < bestState.getCost())
					bestState = cur;
				if (cur.gameOver()) 
					continue;
				
				visited1.add(cur);
	
				ArrayList<State> neighbours = getNeighbours(cur, graph);
				for (State s : neighbours)
					if (!frontier.exists(s) && !nextFrontier.exists(s) && !visited.exists(s) && !visited1.exists(s)) {
						s.setParent(cur);
						nextFrontier.add(s);
					}
			}
			frontier = nextFrontier;
		}

		return bestState;
	}
	
	private boolean RTA(State cur, IGraph graph, StateContainer visited) {
		if(cur == null || visited.exists(cur)) 
			return false;
		if(cur.gameOver()) {
			buildPlayStrategy(cur, graph);
			System.out.print("Greedy agent found a strategy in ");
			System.out.print(deploySequence.size());
			System.out.println(" steps.");
			return true;
		}
		
		visited.add(cur);
		boolean canReach = false;
		while(!canReach) {
			State nextState = null;
			
			ArrayList<State> neighbours = getNeighbours(cur, graph);
			for(State neighbour: neighbours) {
				if(!visited.exists(neighbour)) {
					neighbour.setParent(cur);
					State curState = getBestNextState(neighbour, visited, new StateContainer(), graph);
					if(nextState == null || curState.getCost() < nextState.getCost())
						nextState = curState;
				}
			}
			
			canReach |= RTA(nextState, graph, visited);
			if(nextState == null) break;
		}
		
		return canReach;
	}

	private void preprocess(IGraph graph) {
		StateContainer visited = new StateContainer();
		State initialState = getInitialState(graph);
		if (player)
			makePassiveMove(initialState, graph);
		
		RTA(initialState, graph, visited);
	}


	// Makes 2 moves, one for greedy and one for passive
	private ArrayList<State> getNeighbours(State cur, IGraph graph) {
		ArrayList<Integer> playerNodes = new ArrayList<Integer>();
		for (int i = 0; i < cur.getNodeOwner().size(); ++i)
			if (player == cur.getNodeOwner().get(i))
				playerNodes.add(i);

		int bonus = Math.max(3, playerNodes.size() / 3) + getContinentBonus(playerNodes, graph);
		if (cur.getLastAttackSoldiers() > 0)
			bonus += 2;

		int cost = graph.getNodes().size() - playerNodes.size();

		ArrayList<State> ret = new ArrayList<State>();
		for (int deployNode : playerNodes) {
			// Skip Attack
			ArrayList<Integer> newSoldiers = cloneInt(cur.getSoldiers());
			ArrayList<Boolean> newOwners = cloneBool(cur.getNodeOwner());
			newSoldiers.set(deployNode, newSoldiers.get(deployNode) + bonus);
			State newState = new State(cost, newSoldiers, newOwners, deployNode, 0, 0, 0);
			makePassiveMove(newState, graph);
			ret.add(newState);

			// Attack
			for (int i : playerNodes) {
				INode from = graph.getNodeById(i);
				for (INode to : from.getNeighbours()) {
					if (cur.getNodeOwner().get(from.getId()) == cur.getNodeOwner().get(to.getId()))
						continue;
					int soldiers = cur.getSoldiers().get(from.getId()) - cur.getSoldiers().get(to.getId());
					if (from.getId() == deployNode)
						soldiers += bonus;
					if (soldiers <= 1)
						continue;
					for (int movedSoldiers = 1; movedSoldiers < soldiers; ++movedSoldiers) {
						newSoldiers = cloneInt(cur.getSoldiers());
						newOwners = cloneBool(cur.getNodeOwner());
						newSoldiers.set(deployNode, newSoldiers.get(deployNode) + bonus);
						newSoldiers.set(from.getId(), soldiers - movedSoldiers);
						newSoldiers.set(to.getId(), movedSoldiers);
						newOwners.set(to.getId(), player);
						newState = new State(cost - 1, newSoldiers, newOwners, deployNode, from.getId(), to.getId(),
								cur.getSoldiers().get(to.getId()) + movedSoldiers);
						if (!newState.gameOver())
							makePassiveMove(newState, graph);
						ret.add(newState);
					}
				}
			}
		}
		return ret;
	}

	private ArrayList<Boolean> cloneBool(ArrayList<Boolean> a) {
		ArrayList<Boolean> ret = new ArrayList<Boolean>();
		for (boolean b : a)
			ret.add(b);
		return ret;
	}

	private ArrayList<Integer> cloneInt(ArrayList<Integer> a) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int b : a)
			ret.add(b);
		return ret;
	}

	private void makePassiveMove(State cur, IGraph graph) {
		ArrayList<Integer> playerNodes = new ArrayList<Integer>();
		for (int i = 0; i < cur.getNodeOwner().size(); ++i)
			if (player != cur.getNodeOwner().get(i))
				playerNodes.add(i);

		int bonus = Math.max(3, playerNodes.size() / 3) + getContinentBonus(playerNodes, graph);

		int deployIndex = playerNodes.get(0);
		int min = cur.getSoldiers().get(deployIndex);
		for (int i : playerNodes)
			if (min > cur.getSoldiers().get(i)) {
				min = cur.getSoldiers().get(i);
				deployIndex = i;
			} else if (min == cur.getSoldiers().get(i) && i < deployIndex)
				deployIndex = i;
		cur.getSoldiers().set(deployIndex, cur.getSoldiers().get(deployIndex) + bonus);
	}

	private int getContinentBonus(ArrayList<Integer> nodes, IGraph graph) {
		int ret = 0;
		for (IContinent continent : graph.getContinents()) {
			boolean owned = true;
			for (INode node : continent.getNodes())
				if (!nodes.contains(node.getId())) {
					owned = false;
					break;
				}
			if (owned)
				ret += continent.getBonus();
		}
		return ret;
	}

	private void buildPlayStrategy(State lastState, IGraph graph) {
		while (lastState.getParent() != null) {
			deploySequence.add(graph.getNodeById(lastState.getLastDeploy()));
			if (lastState.getLastAttackSoldiers() > 0)
				attackSequence.add(new Attack(graph.getNodeById(lastState.getLastAttackFrom()),
						graph.getNodeById(lastState.getLastAtatckTo()), lastState.getLastAttackSoldiers()));
			else
				attackSequence.add(null);
			lastState = lastState.getParent();
		}
		Collections.reverse(deploySequence);
		Collections.reverse(attackSequence);
	}

	private State getInitialState(IGraph graph) {
		int cost = 0;
		ArrayList<Integer> soldiers = new ArrayList<Integer>();
		ArrayList<Boolean> ownerTypes = new ArrayList<Boolean>();
		for (INode node : graph.getNodes()) {
			if (node.getOwnerType())
				++cost;
			soldiers.add(node.getSoldiers());
			ownerTypes.add(node.getOwnerType());
		}
		State ret = new State(cost, soldiers, ownerTypes, 0, 0, 0, 0);
		return ret;
	}

}
