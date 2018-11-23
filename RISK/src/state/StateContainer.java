package state;

import java.util.ArrayList;

public class StateContainer {

	private ArrayList<State> states;

	public StateContainer() {
		states = new ArrayList<State>();
	}

	public boolean exists(State s) {
		for (State a : states)
			if (a.equals(s))
				return true;
		return false;
	}

	public boolean isEmpty() {
		return states.isEmpty();
	}

	public void add(State s) {
		states.add(s);
	}

	public void decreaseKey(State s, int cost) {
		for (State a : states)
			if (a.equals(s))
				a.setCost(Math.min(a.getCost(), cost));
		return;
	}

	public State getMin() {
		if (states.isEmpty())
			return null;
		for (int i = 0; i < states.size() - 1; ++i)
			if (states.get(i).getCost() < states.get(states.size() - 1).getCost()) {
				State cur = states.get(i);
				State last = states.get(states.size() - 1);
				states.set(i, last);
				states.set(states.size() - 1, cur);
			}
		State last = states.get(states.size() - 1);
		states.remove(states.size() - 1);
		return last;
	}
}
