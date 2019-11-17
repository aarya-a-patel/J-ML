package jml.model;

import jml.matrix.Matrix;
import jml.matrix.exceptions.InvalidMatrixDimensionsException;

public class Layer {
	protected Matrix weights;
	protected Matrix biases;
	protected boolean useSigmoid;

	public Layer() {

	}

	public Layer(final int numNodes, final int previousLayer, boolean useSigmoid) {
		weights = new Matrix(previousLayer, numNodes, 1);
		biases = new Matrix(1, numNodes, 1);
		this.useSigmoid = useSigmoid;
	}

	public int getNumNodes() {
		return weights.height();
	}

	public Matrix calculate(Matrix x) {
		try {
			if (useSigmoid) {
				return Matrix.applySigmoid(Matrix.add(Matrix.multiply(weights, x), biases));
			} else {
				return Matrix.add(Matrix.multiply(weights, x), biases);
			}
		} catch (InvalidMatrixDimensionsException e) {
			e.printStackTrace();

			return null;
		}
	}

	public String toString() {
		return "Layer [" + (weights != null ? "weights=" + weights + ", " : "")
				+ (biases != null ? "biases=" + biases + ", " : "") + "getNumNodes()=" + getNumNodes() + "]";
	}

	public Matrix getWeights() {
		return weights;
	}

	public Matrix getBiases() {
		return biases;
	}

	public void setWeights(Matrix weights) {
		this.weights = weights;
	}

	public void setBiases(Matrix biases) {
		this.biases = biases;
	}
}
