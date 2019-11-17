package jml.trainer;

import jml.matrix.Matrix;
import jml.matrix.exceptions.InvalidMatrixDimensionsException;
import jml.model.Model;

public class Trainer extends Model implements Runnable {

	private DataParser data;
	private Matrix[] nodeOutputs;
	private boolean running;
	private Thread thread;
	private double learningRate = 0.0001;

	public Trainer(Model model, DataParser data) {
		super(model);
		this.data = data;
		this.running = false;
		this.nodeOutputs = new Matrix[layers.length];
	}

	public void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		int test = 0;

		while (running) {

			Matrix predicted = this.feedForward(data.getInputs());
			double totalError = this.calculateError(predicted, data.getActuals());
			System.out.println("Error: " + totalError);
			System.out.println("Predicted: " + predicted.toString());
			System.out.println("Actual: " + data.getActuals().toString());
			for (int l = 1; l < layers.length; l++)
				System.out.println(layers[l].toString());

			this.makeChanges(data.getActuals());
			data.increment();
			running = test < 500000;
			test++;

			System.out.println("\n");
		}
	}

	public void makeChanges(Matrix actual) {
		Matrix baseDerivative;
		Matrix currDerivative;

		Matrix[] weights = new Matrix[layers.length];
		Matrix[] biases = new Matrix[layers.length];

		int l = layers.length - 1;

		try {
			baseDerivative = Matrix.multiply(getNodeSigmoidDerivative(l), getCostDerivative(actual, nodeOutputs[l]));
			weights[l] = Matrix.layerMultiply(getWeightDerivative(l), baseDerivative);
			biases[l] = new Matrix(baseDerivative);
		} catch (InvalidMatrixDimensionsException e) {
			e.printStackTrace();
			return;
		}

		for (--l; l > 0; l--) {
			currDerivative = new Matrix(1, layers[l].getNumNodes());

			try {
				currDerivative = Matrix.multiply(getNodeSigmoidDerivative(l),
						Matrix.multiply(getPrevNodeDerivative(l), baseDerivative));
				weights[l] = Matrix.layerMultiply(getWeightDerivative(l), currDerivative);
				biases[l] = new Matrix(currDerivative);
			} catch (InvalidMatrixDimensionsException e) {
				e.printStackTrace();
				return;
			}

			baseDerivative = currDerivative;
			currDerivative = null;
		}

		for (int j = 1; j < layers.length; j++) {
			try {
				layers[j]
						.setWeights(Matrix.subtract(layers[j].getWeights(), Matrix.multiply(learningRate, weights[j])));
				layers[j].setBiases(Matrix.subtract(layers[j].getBiases(), Matrix.multiply(learningRate, biases[j])));
			} catch (InvalidMatrixDimensionsException e) {
				e.printStackTrace();
			}
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

	public Matrix getCostDerivative(Matrix actual, Matrix predicted) {
		try {
			return Matrix.multiply(-2, Matrix.subtract(actual, predicted));
		} catch (InvalidMatrixDimensionsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Matrix getWeightDerivative(int layerNum) {
		return nodeOutputs[layerNum - 1];
	}

	public Matrix getNodeSigmoidDerivative(int layerNum) {

		double[] a = new double[layers[layerNum].getNumNodes()];

		if (useSigmoid) {
			for (int nodeNum = 0; nodeNum < layers[layerNum].getNumNodes(); nodeNum++) {
				double y = nodeOutputs[layerNum].getArray()[0][nodeNum];
				double z = -1 * Math.log((1 / y) - 1);
				a[nodeNum] = Math.pow(Math.E, -z) / Math.pow(1 + Math.pow(Math.E, -z), 2);
			}
		} else {
			for (int i = 0; i < a.length; i++) {
				a[i] = 1;
			}
		}

		return Matrix.makeDiagonal(a);
	}

	public Matrix getPrevNodeDerivative(int layerNum) {
		return layers[layerNum].getWeights();
	}

	public Matrix feedForward(Matrix inputs) {
		Matrix out = inputs;
		for (int i = 0; i < layers.length; i++) {
			out = layers[i].calculate(out);
			nodeOutputs[i] = new Matrix(out);
		}
		return out;
	}
}
