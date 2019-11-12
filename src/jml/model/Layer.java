package jml.model;

import java.util.ArrayList;

public class Layer {
	private ArrayList<Node> nodes;
	
	public Layer(int numNodes, int previousLayer) {
		nodes = new ArrayList<Node>(numNodes);
		for (int i = 0; i < numNodes; i++) {
			nodes.set(i, new Node(previousLayer));
		}
	}
	
	public int getNumNodes() {
		return nodes.size();
	}
}
