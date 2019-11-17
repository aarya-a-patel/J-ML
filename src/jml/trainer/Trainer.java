package jml.trainer;

import jml.matrix.Matrix;
import jml.matrix.exceptions.InvalidMatrixDimensionsException;
import jml.model.Model;

public class Trainer extends Model implements Runnable {

	private DataParser data;
	private Matrix[] nodeOutputs;
	private boolean running;
	private Thread thread;

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
		while (running) {
			System.out.println(this.calculateError(this.feedForward(data.getInputs()), data.getActuals()));
			this.makeChanges(data.getActuals());
			data.increment();
			running = false;
		}
	}

	public void makeChanges(Matrix actual) {
		Matrix baseDerivative;
		Matrix currDerivative;

		Matrix[] weights = new Matrix[layers.length];
		Matrix[] biases = new Matrix[layers.length];

		int l = layers.length - 1;

		weights[l] = new Matrix(layers[l].getWeights().width(), layers[l].getNumNodes());
		biases[l] = new Matrix(1, layers[l].getNumNodes());

		baseDerivative = new Matrix(1, layers[l].getNumNodes());

		try {
			baseDerivative = Matrix.multiply(getNodeDerivative(l), getCostDerivative(actual, nodeOutputs[l]));
			weights[l] = Matrix.multiply(getWeightDerivative(l), baseDerivative);
			biases[l] = new Matrix(baseDerivative);
		} catch (InvalidMatrixDimensionsException e1) {
			e1.printStackTrace();
			return;
		}

		for (--l; l > 0; l--) {
			weights[l] = new Matrix(layers[l].getNumNodes(), layers[l].getWeights().width());
			biases[l] = new Matrix(layers[l].getNumNodes(), 1);

			currDerivative = new Matrix(1, layers[l].getNumNodes());

			for (int n = 0; n < layers[l].getNumNodes(); n++) {

				currDerivative.getArray()[0][n] = 0;

				for (int p = 0; p < baseDerivative.height(); p++) {
					currDerivative.getArray()[0][n] += baseDerivative.getArray()[0][p]
							* getPrevNodeDerivative(l + 1, p, n) * getNodeDerivative(l).getArray()[n][n];
				}
			}

			try {
				weights[l] = Matrix.multiply(getWeightDerivative(l), currDerivative);
				biases[l] = new Matrix(currDerivative);
			} catch (InvalidMatrixDimensionsException e1) {
				e1.printStackTrace();
				return;
			}
			
			try {
				layers[l].setWeights(Matrix.add(layers[l].getWeights(), weights[l]));
				layers[l].setBiases(Matrix.add(layers[l].getBiases(), biases[l]));
			} catch (InvalidMatrixDimensionsException e) {
				e.printStackTrace();
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

	public Matrix getCostDerivative(Matrix actual, Matrix predicted) {
		try {
			return Matrix.multiply(2, Matrix.subtract(predicted, actual));
		} catch (InvalidMatrixDimensionsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Matrix getWeightDerivative(int layerNum) {
		return Matrix.makeDiagonal(nodeOutputs[layerNum - 1].getArray()[0]);
	}

	public Matrix getNodeDerivative(int layerNum) {

		double[] a = new double[layers[layerNum].getNumNodes()];

		for (int nodeNum = 0; nodeNum < layers[layerNum].getNumNodes(); nodeNum++) {
			double y = nodeOutputs[layerNum].getArray()[0][nodeNum];
			double z = -Math.log((1 - y) / y);
			a[nodeNum] = Math.pow(Math.E, -z) / Math.pow(1 + Math.pow(Math.E, -z), 2);
		}

		return Matrix.makeDiagonal(a);
	}

	public double getPrevNodeDerivative(int layerNum, int nodeNum, int prevNodeNum) {
		return layers[layerNum].getWeights().getArray()[prevNodeNum][nodeNum];// .getNode(nodeNum).getWeights()[prevNodeNum];
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
