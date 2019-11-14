package jml.trainer;

import jml.model.Model;
import jml.matrix.Matrix;

public class Trainer extends Model implements Runnable {
	
	private DataParser data;
	
	public Trainer(Model model, DataParser data){
		super(model);
		this.data = data;
	}

	public void run() {
		
	}
}
