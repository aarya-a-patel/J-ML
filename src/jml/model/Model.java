package jml.model;

import java.util.ArrayList;

public class Model {
	private Layer[] layers;
	private InputLayer inputLayer;

	public Model(final int layerSizes[], final int sizeInputLayer) {
		layers = new Layer[layerSizes.length];
		inputLayer = new InputLayer(sizeInputLayer);
		
		for (int i = 0; i < layerSizes.length; i++) {
			if (i == 0) {
				layers[i] = new Layer(layerSizes[i], sizeInputLayer);
			} else {
				layers[i] = new Layer(layerSizes[i], layerSizes[i - 1]);
			}
		}
	}
	
	public void feedForward() {
		layers[0].calculate(inputLayer.getInputs());
		for(int i = 1; i < layers.length; i++) {
			layers[i].calculate(layers[i-1].getNodeWeights());
		}
	}
}
