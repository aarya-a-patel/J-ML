package jml.trainer;

import jml.model.Model;

public class Trainer extends Model implements Runnable {

	private DataParser data;
	private double[][] nodeOutputs;
	private boolean running;

	public Trainer(Model model, DataParser data) {
		super(model);
		this.data = data;
		running = false;
	}

	public void run() {
		
	}

	public double getBiasDerivative() {
		return 1;
	}

	public double getCostDerivative(double actual, double predicted) {
		return 2 * (predicted - actual);
	}

	public double getWeightDerivative(int layerNum, int weightNum) {
		return nodeOutputs[layerNum - 1][weightNum];
	}

	public double getNodeDerivative(int layerNum, int nodeNum) {
		double y = nodeOutputs[layerNum][nodeNum];
		double z = -Math.log((1 - y) / y);
		return Math.pow(Math.E, -z) / Math.pow(1 + Math.pow(Math.E, -z), 2);
	}
	
	public double getPrevNodeDerivative(int layerNum, int nodeNum, int prevNodeNum) {
		return layers[layerNum].getNode(nodeNum).getWeight(prevNodeNum);
	}

	public double[] feedForward(double[] inputs) {
		double[] out = inputs;
		for (int i = 0; i < layers.length; i++) {
			out = layers[i].calculate(out);
			nodeOutputs[i] = out;
		}
		return out;
	}
}
