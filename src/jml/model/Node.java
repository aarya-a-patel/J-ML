package jml.model;

public class Node {	
	
	private double total;
	
	private double[] weights;
	private double bias;
	
	public Node() {
		total = 0;
	}
	public Node(int numConnect) {
		weights = new double[numConnect];
		for(int i = 0; i < numConnect; i++) {
			weights[i] = Math.random();
		}
		
		bias = Math.random();
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
		double sum = bias;
		
		for (int i = 0; i < previousLayerOutputs.length; i++) {
			sum += weights[i] * previousLayerOutputs[i];
		}
		total = sigmoid(sum);
		
		return sigmoid(sum);
	}
	
	public double getSum() {
		return total;
	}
	
	public double[] getWeights() {
		return weights;
	}
	
	public double getBias() {
		return bias;
	}
	
	public String toString() {
		String returnString = "\n\n";
		for(int i = 0; i < weights.length - 1; i++) {
			returnString += "\nConnection " + Integer.toString(i+1) + ":   " + Double.toString(weights[i]);
		}
		returnString += "\nBias " + Double.toString(weights[weights.length-1]);
		returnString+= "\n\nTotal: " + Double.toString(total);
		returnString += "\n\n";
		return returnString;
	}
}