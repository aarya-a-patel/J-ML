package jml.model;

import java.util.ArrayList;

public class Layer {
	private Node[] nodes;
	
	public Layer(final int numNodes, final int previousLayer) {
		nodes = new Node[numNodes];
		for (int i = 0; i < numNodes; i++) {
			nodes[i] =  new Node(previousLayer);
		}
	}
	
	public int getNumNodes() {
		return nodes.length;
	}
}
