package jml.trainer;

import jml.model.Model;

public class Trainer extends Model implements Runnable {

	private DataParser data;
	private double[][] nodeOutputs;

	public Trainer(Model model, DataParser data) {
		super(model);
		this.data = data;
	}

	public void run() {
		// Training -- Need to make other methods to support gradient descent and
		// dataparser
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

	public double[] feedForward(double[] inputs) {
		double[] out = inputs;
		for (int i = 0; i < layers.length; i++) {
			out = layers[i].calculate(out);
			nodeOutputs[i] = out;
		}
		return out;
	}
}
