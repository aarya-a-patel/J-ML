package jml.model;

public class Model {
	private Layer[] layers;

	public Model(final int layerSizes[]) {
		layers = new Layer[layerSizes.length];
		layers[0] = new InputLayer(layerSizes[0]);

		for (int i = 1; i < layerSizes.length; i++) {
			layers[i] = new Layer(layerSizes[i], layerSizes[i - 1]);

		}
	}
	
	public double[] calculate(double[] inputs) {
		double[] out = inputs;
		for (int i = 0; i < layers.length; i++) {
			out = layers[i].calculate(out);
		}
		return out;
	}
	
	public void feedForward() {
		for (int i = 0; i < layers.length; i++) {
			layers[i].calculate(layers[i - 1].getNodeWeights());
		}
	}
}
