package jml.model;

import jml.matrix.Matrix;

public class Layer {
	protected Node[] nodes;
	protected Matrix weights;
	protected Matrix biases;
	
	public Layer() {
		
	}
	
	public Layer(final int numNodes, final int previousLayer) {
		nodes = new Node[numNodes];
		for (int i = 0; i < numNodes; i++) {
			nodes[i] =  new Node(previousLayer);
		}
		weights = new Matrix(numNodes, previousLayer);
		biases = new Matrix(numNodes, 1);
		
		for (int i = 0; i < numNodes; i++) {
			weights.getArray()[i] = nodes[i].getWeights();
			biases.getArray()[i][0] = nodes[i].getBias();
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
	public double[] getNodeOutputs() {
		double returnWeights[] = new double[nodes.length];
		for(int i = 0; i < nodes.length; i++) {
			returnWeights[i] = nodes[i].getSum();
		}
		return returnWeights;
	}
	
	public void setValues(final double[] inp) {
		for(int i = 0; i < inp.length; i++) {
			nodes[i].setSum(inp[i]);
		}
	}
	
	public Node getNode(int index) {
		return nodes[index];
	}
	
	public Matrix getWeights() {
		return weights;
	}
	
	public Matrix getBiases() {
		return biases;
	}
	
	public String toString() {
		String returnString = "\n\n";
		for(int i = 0; i < nodes.length; i++) {
			returnString += "\nNeuron " + Integer.toString(i+1) + ":   " + nodes[i].toString();
		}
		returnString += "\n\n";
		return returnString;
	}
}
