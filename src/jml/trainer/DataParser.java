package jml.trainer;

public interface DataParser {
	public int[] getBatches(String pathOfData);
	public double[] getBatchData(int batchNumber);
	public double[] parseData();
	public void openFile(String fileName);
	public int getDataLength();
}
