package aarya.libml.model;

import java.util.ArrayList;

public class Model {
	private ArrayList<Layer> layers;
	private InputLayer inputLayer;

	public Model(int layerSizes[], int sizeInputLayer) {
		layers = new ArrayList<Layer>(layerSizes.length);
		inputLayer = new InputLayer(sizeInputLayer);
		
		for (int i = 0; i < layerSizes.length; i++) {
			if (i == 0) {
				layers.set(i, new Layer(layerSizes[i], sizeInputLayer));
			} else {
				layers.set(i, new Layer(layerSizes[i], layerSizes[i - 1]));
			}
		}
	}
}
