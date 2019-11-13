package jml.model;

import java.util.ArrayList;

public class Layer {
	protected Node[] nodes;
	
	public Layer() {
		
	}
	
	public Layer(final int numNodes, final int previousLayer) {
		nodes = new Node[numNodes];
		for (int i = 0; i < numNodes; i++) {
			nodes[i] =  new Node(previousLayer);
		}
	}
	
	public int getNumNodes() {
		return nodes.length;
	}
	
	public double[] calculate(double[] previousLayerOutputs) {
		
		double[] out = new double[nodes.length];
		
		for (int i = 0; i < out.length; i++) {
			out[i] = nodes[i].calculate(previousLayerOutputs);
		}
		
		return out;
	}
}
