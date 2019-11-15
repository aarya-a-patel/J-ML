package jml.trainer;

import jml.model.Model;

public class Trainer extends Model implements Runnable {

	private DataParser data;
	private double[][] nodeOutputs;
	private boolean running;
	private Thread thread;

	public Trainer(Model model, DataParser data) {
		super(model);
		this.data = data;
		this.running = false;
		this.nodeOutputs = new double[layers.length][];
	}

	public void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		while (running) {
			System.out.println("running -- Placeholder");

		}
	}

	public void makeChanges(double[] actual) {
		double[] baseDerivative;
		double[] currDerivative;
		
		int l = layers.length - 1;
		
		baseDerivative = new double[layers[l].getNumNodes()];
		
		for (int n = 0; n < layers[l].getNumNodes(); n++) {
			baseDerivative[n] = getCostDerivative(actual[n], nodeOutputs[l][n]) * getNodeDerivative(l, n);
		}
		
		
		for (--l; l > 0; l--) {
			currDerivative = new double[layers[l].getNumNodes()];
			
			for (int n = 0; n < layers[l].getNumNodes(); n++) {
				
				currDerivative[n] = 0;
				
				for (int p = 0; p < baseDerivative.length; p++) {
					currDerivative[n] += baseDerivative[p] * getPrevNodeDerivative(l + 1, p, n) * getNodeDerivative(l, n);
				}
				
				currDerivative[n] /= baseDerivative.length;
				
				
				// Compute Changes to weights and biases here or add to Matrix for change later
				
			}
			
			baseDerivative = currDerivative;
			currDerivative = null;
		}
	}

	public void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
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
		return layers[layerNum].getNode(nodeNum).getWeights()[prevNodeNum];
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
