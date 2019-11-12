package jml.model;

public class Node {	
	
	private double total;
	
	private double[] weights;
	
	public Node() {
		total = 0;
	}
	public Node(int numConnect) {
		weights = new double[numConnect];
		for(int i = 0; i < numConnect + 1; i++) {
			weights[i] = Math.random();
		}
	}
	public void setSum(double[] input) {
		total = 0;
		for(double part:input) {
			total += part;
		}
	}
	public void setSum(double input) {
		total = input;
	}
	public double sigmoid(double x) {
		return (1/(1 + Math.pow(Math.E, -x)));
	}
	
	public double calculate(double[] previousLayerOutputs) {
		double sum = weights[weights.length - 1];
		
		for (int i = 0; i < previousLayerOutputs.length; i++) {
			sum += weights[i] * previousLayerOutputs[i];
		}
		
		return sigmoid(sum);
		
	}
	
	public String toString() {
		String returnString = "";
		for(int i = 0; i < weights.length; i++) {
			returnString += "\nConnection " + Integer.toString(i+1) + ":   " + Double.toString(weights[i]);
		}
		return returnString;
	}
}