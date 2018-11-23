package state;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class StateContainer {

	private Queue<State> states;
	private TreeMap<State, Integer> queueMap;

	public StateContainer() {
		states = new PriorityQueue<State>();
		queueMap = new TreeMap<State, Integer>();
	}

	public boolean exists(State s) {
		return queueMap.containsKey(s);
	}

	public boolean isEmpty() {
		return states.isEmpty();
	}

	public void add(State s) {
		if(exists(s)) {
			Integer currVal = queueMap.get(s);
			if(currVal > s.getCost()) {
				queueMap.put(s, s.getCost());
				states.add(s);
			}
		} else {
			queueMap.put(s, s.getCost());
			states.add(s);
		}
	}

	public State getMin() {
		while(!states.isEmpty()) {
			State s = states.remove();
			if(s.getCost() == queueMap.get(s))
				return s;
		}
		return null;
	}
}
