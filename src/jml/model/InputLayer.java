package jml.model;

import jml.matrix.Matrix;

public class InputLayer extends Layer {

	public InputLayer(final int numInputs) {
		super();
	}

	public Matrix calculate(Matrix previousLayerOutputs) {
		return previousLayerOutputs;

	}
}
