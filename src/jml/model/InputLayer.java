package jml.model;

public class InputLayer extends Layer {
	
	public InputLayer(final int numInputs)  {
		super();
		nodes = new Node[numInputs];
		for (int i = 0; i < numInputs	; i++) {
			nodes[i] =  new Node(1);
		}
	}
	
	public double[] calculate(double[] previousLayerOutputs) {
		return previousLayerOutputs;
		
	}
}
