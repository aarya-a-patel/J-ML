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
	public double[] getInputs() {

		double[] retInputs = { inputs[position] };

		return retInputs;
	}

	public double[] getActuals() {

		double[] retInputs = { actual[position] };

		return retInputs;
	}
	
	public void increment() {
		if (position == inputs.length - 2) {
			position = 0;
		} else {
			position++;
		}
	}

	public void openFile(String tfileName) {
		try {
			Scanner reader = new Scanner(new File(tfileName));

			int i = 0;

			while (reader.hasNext()) {

				inputs[i] = reader.nextDouble();
				actual[i] = reader.nextDouble();

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
