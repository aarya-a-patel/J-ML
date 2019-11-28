package jml.examples.MNIST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import jml.matrix.Matrix;
import jml.model.Model;
import jml.trainer.DataParser;
import jml.trainer.Trainer;

public class Train {

	public static void main(String[] args) {
		int[] layerSizes = { 784, 16, 16, 10 };

		Model m = new Model(layerSizes, true);
		Parser dp = new Parser();
		Trainer t = new Trainer(m, dp);

		dp.openFile("src\\jml\\examples\\MNIST\\mnist_train.csv");

		t.start();
	}

	private static class Parser implements DataParser {

		ArrayList<Matrix> inputs = new ArrayList<Matrix>();
		ArrayList<Matrix> actuals = new ArrayList<Matrix>();

		int index = 0;

		public int[] getBatches(String pathOfData) {
			return null;
		}

		public double[] getBatchData(int batchNumber) {
			return null;
		}

		public Matrix getInputs() {
			return inputs.get(index);
		}

		public Matrix getActuals() {
			return actuals.get(index);
		}

		public void increment() {
			index = (index + 1) % actuals.size();
		}

		public void openFile(String fileName) {
			double[][] input = new double[1][784];
			double[][] actual = new double[1][10];
			String[] data;

			try {
				Scanner file = new Scanner(new File(fileName));

				for (int i = 0; file.hasNextLine(); i++) {
					data = file.nextLine().split(",");

					actual = new double[1][10];
					input = new double[1][784];

					for (int j = 0; j < 10; j++) {
						actual[0][j] = 0;
					}

					actual[0][Integer.parseInt(data[0])] = 1;

					for (int j = 0; j < 784; j++) {
						input[0][j] = Integer.parseInt(data[j + 1]);
					}

					inputs.add(new Matrix(input));
					actuals.add(new Matrix(actual));
				}

				file.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		public int getDataLength() {
			return actuals.size();
		}
	}
}
