package jml.model;

import jml.matrix.Matrix;
import jml.matrix.exceptions.InvalidMatrixDimensionsException;

public class Layer {
	protected Matrix weights;
	protected Matrix biases;
	
	public Layer() {
		
	}
	
	public Layer(final int numNodes, final int previousLayer) {
		weights = new Matrix(previousLayer, numNodes, 1);
		biases = new Matrix(1, numNodes, 1);
	}
	
	public int getNumNodes() {
		return weights.height();
	}
	
	public Matrix calculate(Matrix x) {
		try {
			return Matrix.add(Matrix.multiply(weights, x), biases);
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
