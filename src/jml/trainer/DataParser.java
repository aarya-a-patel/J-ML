package jml.trainer;

import jml.matrix.Matrix;

public interface DataParser {
	public int[] getBatches(String pathOfData);

	public double[] getBatchData(int batchNumber);

	public Matrix getInputs();

	public Matrix getActuals();

	public void increment();

	public void openFile(String fileName);

	public int getDataLength();
}
