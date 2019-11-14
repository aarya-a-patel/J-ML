package jml.trainer;

import jml.model.Model;
import jml.matrix.Matrix;

public class Trainer extends Model {

	private Matrix inputs;
	private Matrix answers;
	
	public Trainer(Model model, DataParser data){
		super(model);
	}
}
