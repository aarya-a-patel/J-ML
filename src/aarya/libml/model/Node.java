package aarya.libml.model;

import java.util.ArrayList;
import java.util.Random;

public class Node {
	private ArrayList<Double> weights;
	
	public Node(int numInputs) {
		Random rand = new Random();
		
		weights = new ArrayList<Double>(numInputs + 1);
		
		for (int i = 0; i < numInputs + 1; i++) {
			weights.set(i, rand.nextDouble());
		}
	}
}
