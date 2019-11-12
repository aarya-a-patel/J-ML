package jml.model;

public class Node {	
	
	private double total;
	
	private Connection[] connections;
	
	public Node() {
		total = 0;
	}
	public Node(int numConnect) {
		connections = new Connection[numConnect];
		for(int i = 0; i < numConnect; i++) {
			connections[i] = new Connection();
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
	public double sigmoid() {
		return (1/(1 + Math.pow(Math.E, total)));
	}
	public void setInpPerceptron() {
		for(int i = 0; i < connections.length; i++) {
			connections[i].setWeight(1);
		}
	}
	
	public String toString() {
		String returnString = "";
		for(int i = 0; i < connections.length; i++) {
			returnString += "\nConnection " + Integer.toString(i+1) + ":   " + connections[i].toString();
		}
		return returnString;
	}
}