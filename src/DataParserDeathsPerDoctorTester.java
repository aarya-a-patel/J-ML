import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import jml.matrix.Matrix;
import jml.trainer.DataParser;

public class DataParserDeathsPerDoctorTester implements DataParser {

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
	public Matrix getInputs() {

		double[][] retInputs = { { inputs.get(position) } };

		return new Matrix(retInputs);
	}

	public Matrix getActuals() {

		double[][] retInputs = { { actual.get(position) } };

		return new Matrix(retInputs);
	}

	public void increment() {
		position = (position + 1) % inputs.size();
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
