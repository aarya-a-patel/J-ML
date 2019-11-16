import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import jml.trainer.DataParser;

public class DataParserTester implements DataParser {

	private ArrayList<Double> inputs;
	private ArrayList<Double> actual;
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

		double[] retInputs = { inputs.get(position) };

		return retInputs;
	}

	public double[] getActuals() {

		double[] retInputs = { actual.get(position) };

		return retInputs;
	}
	
	public void increment() {
		if (position == inputs.size() - 2) {
			position = 0;
		} else {
			position++;
		}
	}

	public void openFile(String tfileName) {
		
		inputs = new ArrayList<Double>();
		actual = new ArrayList<Double>();
		
		try {
			Scanner reader = new Scanner(new File(tfileName));

			int i = 0;

			while (reader.hasNext()) {

				inputs.add(reader.nextDouble());
				actual.add(reader.nextDouble());

				i++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getDataLength() {
		return inputs.size();
	}

}
