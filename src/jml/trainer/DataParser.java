package jml.trainer;

public interface DataParser {
	public int[] getBatches(String pathOfData);
	public double[] getBatchData(int batchNumber);
	public double[] parseData(int position);
	public void openFile(String fileName);
}
