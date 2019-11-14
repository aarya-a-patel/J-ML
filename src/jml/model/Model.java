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
	
	public double[] feedForward(double[] inputs) {
		double[] out = inputs;
		for (int i = 0; i < layers.length; i++) {
			out = layers[i].calculate(out);
		}
		return out;
	}
	
	public String toString() {
		String returnString = "\n\n";
		for(int i = 0; i < layers.length; i++) {
			returnString += "\nLayer " + Integer.toString(i+1) + ":   " + layers[i].toString();
		}
		returnString += "\n\n";
		return returnString;
	}
}
