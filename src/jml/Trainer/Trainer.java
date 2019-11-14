package jml.Trainer;

import jml.model.Model;
import jml.matrix.Matrix;

public class Trainer extends Model {

	private Matrix inputs;
	private Matrix answers;
	
	public Trainer(final int layerSizes[], final int numDataPieces){
		super(layerSizes);
	}
}
