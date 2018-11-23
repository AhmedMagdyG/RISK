package agent;

import java.util.HashSet;
import java.util.List;

import controller.Attack;
import graph.IContinent;
import graph.IGraph;
import graph.INode;

public class AggressiveAgent implements IAgent {
	private AgentType agentType;
	private boolean player;
	private boolean lastTurnAttack;

	public AggressiveAgent(AgentType agentType, boolean player, IGraph graph) {
		this.agentType = agentType;
		this.player = player;
		this.lastTurnAttack = false;
	}

	@Override
	public AgentType getAgentType() {
		return this.agentType;
	}

	@Override
	public void setLastTurnAttack(boolean lastTurnAttack) {
		this.lastTurnAttack = lastTurnAttack;
	}

	@Override
	public Attack attack(IGraph graph) {
		// TODO - update last turn attack
		List<IContinent> continents = graph.getContinents();
		HashSet<Integer> enemyContinents = new HashSet<Integer>();
		for (IContinent continent : continents) {
			boolean allEnemy = true;
			for (INode node : continent.getNodes())
				allEnemy = allEnemy && (node.getOwnerType() != player);
			if (allEnemy)
				enemyContinents.add(continent.getContinentId());
		}

		Attack fullContinentAttack = handleFullContinent(graph, enemyContinents);

		if (fullContinentAttack != null) {
			this.lastTurnAttack = true;
			return fullContinentAttack;
		}

		INode from = null, to = null;
		int soldiers = 0;

		for (IContinent continent : graph.getContinents()) {
			for (INode node : continent.getNodes()) {
				if (node.getOwnerType() == player) {
					for (INode neighbor : node.getNeighbours()) {
						if (neighbor.getOwnerType() != player) {
							if (node.getSoldiers() - neighbor.getSoldiers() > 1) {
								if (from == null) {
									from = node;
									to = neighbor;
									soldiers = neighbor.getSoldiers();
								} else {
									if (neighbor.getSoldiers() > soldiers) {
										from = node;
										to = neighbor;
										soldiers = neighbor.getSoldiers();
									} else if ((neighbor.getSoldiers() == soldiers)
											&& (neighbor.getId() < to.getId())) {
										from = node;
										to = neighbor;
									}
								}
							}
						}
					}
				}
			}
		}
		if (from == null) {
			this.lastTurnAttack = false;
			return null;
		}
		this.lastTurnAttack = true;
		return new Attack(from, to, from.getSoldiers() - 1);
	}

	@Override
	public INode deploy(IGraph graph, int soldiers) {
		INode ret = null;
		for (IContinent continent : graph.getContinents()) {
			for (INode node : continent.getNodes()) {
				if (node.getOwnerType() != player)
					continue;
				if (ret == null || (node.getSoldiers() > ret.getSoldiers())) {
					ret = node;
				} else if ((node.getSoldiers() == ret.getSoldiers()) && (node.getId() < ret.getId())) {
					ret = node;
				}
			}
		}
		return ret;
	}

	@Override
	public boolean getPlayer() {
		return this.player;
	}

	@Override
	public boolean lastTurnAttack() {
		return this.lastTurnAttack;
	}

	private Attack handleFullContinent(IGraph graph, HashSet<Integer> enemyContinents) {
		if (enemyContinents.isEmpty())
			return null;

		List<IContinent> continents = graph.getContinents();
		INode from = null, to = null;
		int soldiers = 0; // number of soldiers in attacked node.
		int continentId = -1, continentBonus = -1; // bonus continent states

		for (IContinent continent : continents) {
			for (INode node : continent.getNodes()) {
				if (node.getOwnerType() == player) {
					for (INode neighbor : node.getNeighbours()) {
						if ((neighbor.getOwnerType() != player)
								&& enemyContinents.contains(neighbor.getContinent().getContinentId())
								&& (node.getSoldiers() - neighbor.getSoldiers() > 1)) {
							if (from == null) {
								from = node;
								to = neighbor;
								soldiers = neighbor.getSoldiers();
								continentId = neighbor.getContinent().getContinentId();
								continentBonus = continents.get(continentId).getBonus();
							} else {
								if (neighbor.getContinent().getContinentId() == continentId) {
									if (neighbor.getSoldiers() > soldiers) {
										from = node;
										to = neighbor;
										soldiers = neighbor.getSoldiers();
									} else if ((neighbor.getSoldiers() == soldiers)
											&& (neighbor.getId() < to.getId())) {
										from = node;
										to = neighbor;
									}
								} else {
									int newBonus = continents.get(neighbor.getContinent().getContinentId()).getBonus();
									if (newBonus > continentBonus) {
										from = node;
										to = neighbor;
										soldiers = neighbor.getSoldiers();
										continentId = neighbor.getContinent().getContinentId();
										continentBonus = continents.get(continentId).getBonus();
									} else if (newBonus == continentBonus) {
										if (neighbor.getSoldiers() > soldiers) {
											from = node;
											to = neighbor;
											soldiers = neighbor.getSoldiers();
											continentId = neighbor.getContinent().getContinentId();
										} else if ((neighbor.getSoldiers() == soldiers)
												&& (neighbor.getId() < to.getId())) {
											from = node;
											to = neighbor;
											continentId = neighbor.getContinent().getContinentId();
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (from == null)
			return null;
		return new Attack(from, to, from.getSoldiers() - 1);
	}

}
