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
			//calculate function sets the totals for each node in this Layer to the correct values based on the inputs
		}
		
		return out;
	}
	public double[] getNodeWeights() {
		double returnWeights[] = new double[nodes.length];
		for(int i = 0; i < nodes.length; i++) {
			returnWeights[i] = nodes[i].getSum();
		}
		return returnWeights;
	}
}
