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
		int test = 0;
		
		while (running) {

			Matrix predicted = this.feedForward(data.getInputs());
			double totalError = this.calculateError(predicted, data.getActuals());
			System.out.println(totalError);

			System.out.println(layers[1].toString());

			this.makeChanges(data.getActuals());
			data.increment();
			running = test < 100000;
			test++;
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
			weights[l] = Matrix.multiply(getWeightDerivative(l), baseDerivative);
			biases[l] = new Matrix(baseDerivative);
		} catch (InvalidMatrixDimensionsException e) {
			e.printStackTrace();
			return;
		}

		for (--l; l > 0; l--) {
			currDerivative = new Matrix(1, layers[l].getNumNodes());

//			for (int n = 0; n < layers[l].getNumNodes(); n++) {
//
//				currDerivative.getArray()[0][n] = 0;
//
//				for (int p = 0; p < baseDerivative.height(); p++) {
//					currDerivative.getArray()[0][n] += baseDerivative.getArray()[0][p]
//							* getPrevNodeDerivative(l + 1, p, n) * getNodeDerivative(l).getArray()[n][n];
//				}
//			}

			try {
				currDerivative = Matrix.multiply(getNodeSigmoidDerivative(l),
						Matrix.multiply(getPrevNodeDerivative(l), baseDerivative));
				weights[l] = Matrix.multiply(getWeightDerivative(l), currDerivative);
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
				layers[j].setWeights(Matrix.add(layers[j].getWeights(), weights[j]));
				layers[j].setBiases(Matrix.add(layers[j].getBiases(), biases[j]));
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
			return Matrix.multiply(2, Matrix.subtract(predicted, actual));
		} catch (InvalidMatrixDimensionsException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Matrix getWeightDerivative(int layerNum) {
		return Matrix.makeDiagonal(nodeOutputs[layerNum - 1].getArray()[0]);
	}

	public Matrix getNodeSigmoidDerivative(int layerNum) {

		double[] a = new double[layers[layerNum].getNumNodes()];

		for (int nodeNum = 0; nodeNum < layers[layerNum].getNumNodes(); nodeNum++) {
			double y = nodeOutputs[layerNum].getArray()[0][nodeNum];
			double z = -1 * Math.log((1 / y) - 1);
			a[nodeNum] = Math.pow(Math.E, -z) / Math.pow(1 + Math.pow(Math.E, -z), 2);
			//System.out.println(z + "  " + y);
		}

		return Matrix.makeDiagonal(a);
	}

	public Matrix getPrevNodeDerivative(int layerNum) {
		return layers[layerNum].getWeights();// .getNode(nodeNum).getWeights()[prevNodeNum];
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
