import jml.trainer.DataParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DataParserTester implements DataParser {
	
	private double[] inputs;
	private double[] actual;
	private int position = 0;
	
	@Override
	public int[] getBatches(String pathOfData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getBatchData(int batchNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] parseData(int position) {

		return null;
	}
	
	public void openFile(String tfileName) {
		try {
			Scanner reader = new Scanner(new File(tfileName));
			
			int i = 0;
			
			while(reader.hasNext()) {
			
				inputs[i] = reader.nextDouble();
				inputs[i] = reader.nextDouble();
				
				i++;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getDataLength() {
		return inputs.length;
	}

}
