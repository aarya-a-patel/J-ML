package jml.model;

import jml.matrix.Matrix;

public class Model {
	protected Layer[] layers;
	protected boolean useSigmoid;

	public Model(final int layerSizes[], boolean useSigmoid) {
		layers = new Layer[layerSizes.length];
		layers[0] = new InputLayer(layerSizes[0]);

		for (int i = 1; i < layerSizes.length; i++) {
			layers[i] = new Layer(layerSizes[i], layerSizes[i - 1], useSigmoid);

		}
		
		this.useSigmoid = useSigmoid;
	}

	public Model(Model model) {
		this.layers = model.getLayers();
	}

	public Layer[] getLayers() {
		return this.layers;
	}

	public Matrix feedForward(Matrix inputs) {
		Matrix out = inputs;
		for (int i = 0; i < layers.length; i++) {
			out = layers[i].calculate(out);
		}
		return out;
	}

	public String toString() {
		String returnString = "\n\n";
		for (int i = 0; i < layers.length; i++) {
			returnString += "\nLayer " + Integer.toString(i + 1) + ":   " + layers[i].toString();
		}
		returnString += "\n\n";
		return returnString;
	}

	public double calculateError(Matrix predicted, Matrix actual) {

		double squaredSum = 0;

		for (int i = 0; i < predicted.height(); i++) {
			squaredSum += Math.pow(actual.getArray()[0][i] - predicted.getArray()[0][i], 2);
		}

		return squaredSum / predicted.height();

	}
}
