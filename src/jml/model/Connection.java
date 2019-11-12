package jml.model;


public class Connection {
	private double weight; 
	
	public Connection() {
		weight = Math.random();
	}
	
	public Connection(double Tweight) {
		weight = Tweight;
	}
	public void setWeight(double Tweight) {
		weight = Tweight;
	}
	
	public String toString() {
		return ("Weight: " + Double.toString(weight));
	}
}
