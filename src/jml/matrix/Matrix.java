package jml.matrix;

import matrix.exceptions.InvalidMatrixDimensionsException;

public class Matrix {
	private double array[][];

	// ----------------------------------------------------------------------------------------------------------------------------

	// Default Constructor of empty 1x1 matrix
	public Matrix() {
		array = new double[1][1];
	}

	// Creates empty Matrix with Dimensions of (width, height)
	public Matrix(final int width, final int height) {
		array = new double[width][height];
	}

	/*
	 * Creates Matrix with Dimensions of (x,y) Fills Matrix with random integer from
	 * 0 - range
	 */
	public Matrix(final int width, final int height, int range) {
		array = new double[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				array[i][j] = (int) (Math.random() * range);
			}
		}
	}

	// Sets Matrix to input array, including data
	public Matrix(double[][] input) {
		array = input.clone();
	}

	// Basic Copy constructor
	public Matrix(Matrix m) {
		array = m.getArray().clone();
	}

	// ----------------------------------------------------------------------------------------------------------------------------

	// Returns Matrix width (x)
	public int width() {
		return array.length;
	}

	// Returns Matrix height (y)
	public int height() {
		return array[0].length;
	}

	// ----------------------------------------------------------------------------------------------------------------------------

	// Returns scalar addition of each element of Matrix by double addition

	public Matrix Add(double addend) {
		double[][] returnArray = array;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				returnArray[i][j] += addend;
			}
		}
		return new Matrix(returnArray);
	}

	/*
	 * Adds each element of Matrix by equivalent element in addition array Arrays w/
	 * x or y sizes greater than Matrix objects will throw error Arrays w/ x or y
	 * sizes less than Matrix object will perform action and leave remaining Matrix
	 * elements the same
	 */
	public Matrix Add(double[][] addend) throws InvalidMatrixDimensionsException {

		double[][] returnArray = this.getArray();

		try {
			for (int i = 0; i < addend.length; i++) {
				for (int j = 0; j < addend[0].length; j++) {
					returnArray[i][j] += addend[i][j];
				}
			}
			return new Matrix(returnArray);
		} catch (Exception e) {
			throw new InvalidMatrixDimensionsException("Size Mismatch-  Input: " + "[" + Integer.toString(addend.length)
					+ "," + Integer.toString(addend[0].length) + "] -- Class Array: " + "["
					+ Integer.toString(array.length) + "," + Integer.toString(array[0].length) + "]");
		}
	}

	public Matrix Add(Matrix addend) throws InvalidMatrixDimensionsException {

		double[][] returnArray = this.getArray();

		try {
			for (int i = 0; i < addend.width(); i++) {
				for (int j = 0; j < addend.height(); j++) {
					returnArray[i][j] += addend.getArray(i, j);
				}
			}
			return new Matrix(returnArray);
		} catch (Exception e) {
			throw new InvalidMatrixDimensionsException("Size Mismatch-  Input: " + "["
					+ Integer.toString(addend.width()) + "," + Integer.toString(addend.height()) + "] -- Class Array: "
					+ "[" + Integer.toString(array.length) + "," + Integer.toString(array[0].length) + "]");
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------

	// Returns scalar subtraction of each element of Matrix by double subtraction
	public Matrix Subtract(double subtractor) {
		double[][] returnArray = array;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				returnArray[i][j] -= subtractor;
			}
		}
		return new Matrix(returnArray);
	}

	/*
	 * Subtracts each element of Matrix by equivalent element in addition array
	 * Arrays w/ x or y sizes greater than Matrix objects will throw error Arrays w/
	 * x or y sizes less than Matrix object will perform action and leave remaining
	 * Matrix elements the same
	 */
	public Matrix Subtract(double[][] subtractor) throws InvalidMatrixDimensionsException {

		double[][] returnArray = this.getArray();

		try {
			for (int i = 0; i < subtractor.length; i++) {
				for (int j = 0; j < subtractor[0].length; j++) {
					returnArray[i][j] -= subtractor[i][j];
				}
			}
			return new Matrix(returnArray);
		} catch (Exception e) {
			throw new InvalidMatrixDimensionsException(
					"Size Mismatch-  Input: " + "[" + Integer.toString(subtractor.length) + ","
							+ Integer.toString(subtractor[0].length) + "] -- Class Array: " + "["
							+ Integer.toString(array.length) + "," + Integer.toString(array[0].length) + "]");
		}
	}

	public Matrix Subtract(Matrix subtractor) throws InvalidMatrixDimensionsException {

		double[][] returnArray = this.getArray();

		try {
			for (int i = 0; i < subtractor.width(); i++) {
				for (int j = 0; j < subtractor.height(); j++) {
					returnArray[i][j] -= subtractor.getArray(i, j);
				}
			}
			return new Matrix(returnArray);
		} catch (Exception e) {
			throw new InvalidMatrixDimensionsException(
					"Size Mismatch-  Input: " + "[" + Integer.toString(subtractor.width()) + ","
							+ Integer.toString(subtractor.height()) + "] -- Class Array: " + "["
							+ Integer.toString(array.length) + "," + Integer.toString(array[0].length) + "]");
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------

	// Returns scalar multiplication of each element of Matrix by double
	// multiplication
	public Matrix ScalarMultiply(double multiplier) {
		double[][] returnArray = array;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				returnArray[i][j] *= multiplier;
			}
		}
		return new Matrix(returnArray);
	}

	/*
	 * Subtracts each element of Matrix by equivalent element in addition array
	 * Arrays w/ x or y sizes greater than Matrix objects will throw error Arrays w/
	 * x or y sizes less than Matrix object will perform action and leave remaining
	 * Matrix elements the same
	 */
	public Matrix ScalarMultiply(double[][] multiplier) throws InvalidMatrixDimensionsException {

		double[][] returnArray = this.getArray();

		try {
			for (int i = 0; i < multiplier.length; i++) {
				for (int j = 0; j < multiplier[0].length; j++) {
					returnArray[i][j] *= multiplier[i][j];
				}
			}
			return new Matrix(returnArray);
		} catch (Exception e) {
			throw new InvalidMatrixDimensionsException(
					"Size Mismatch-  Input: " + "[" + Integer.toString(multiplier.length) + ","
							+ Integer.toString(multiplier[0].length) + "] -- Class Array: " + "["
							+ Integer.toString(array.length) + "," + Integer.toString(array[0].length) + "]");
		}
	}

	public Matrix ScalarMultiply(Matrix multiplier) throws InvalidMatrixDimensionsException {

		double[][] returnArray = this.getArray();

		try {
			for (int i = 0; i < multiplier.width(); i++) {
				for (int j = 0; j < multiplier.height(); j++) {
					returnArray[i][j] *= multiplier.getArray(i, j);
				}
			}
			return new Matrix(returnArray);
		} catch (Exception e) {
			throw new InvalidMatrixDimensionsException(
					"Size Mismatch-  Input: " + "[" + Integer.toString(multiplier.width()) + ","
							+ Integer.toString(multiplier.height()) + "] -- Class Array: " + "["
							+ Integer.toString(array.length) + "," + Integer.toString(array[0].length) + "]");
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------

	// Returns dot multiplications of the two matrices

	public Matrix Multiply(double[][] multiplier) throws InvalidMatrixDimensionsException {
		if (this.height() == multiplier.length) {
			double[][] returnArray = new double[this.width()][multiplier[0].length];
			for (int i = 0; i < this.width(); i++) {
				for (int j = 0; j < multiplier[0].length; j++) {
					for (int k = 0; k < this.height(); k++) {
						returnArray[i][j] += array[i][k] * multiplier[k][j];
					}
				}
			}
			return new Matrix(returnArray);
		} else {
			throw new InvalidMatrixDimensionsException(
					"Size Mismatch-  Input: " + "[" + Integer.toString(multiplier.length) + ","
							+ Integer.toString(multiplier[0].length) + "] -- Class Array: " + "["
							+ Integer.toString(array.length) + "," + Integer.toString(array[0].length) + "]");
		}
	}
	
	public Matrix Multiply(Matrix multiplier) throws InvalidMatrixDimensionsException {
		if (this.height() == multiplier.width()) {
			double[][] returnArray = new double[this.width()][multiplier.height()];
			for (int i = 0; i < this.width(); i++) {
				for (int j = 0; j < multiplier.height(); j++) {
					for (int k = 0; k < this.height(); k++) {
						returnArray[i][j] += array[i][k] * multiplier.getArray(k, j);
					}
				}
			}
			return new Matrix(returnArray);
		} else {
			throw new InvalidMatrixDimensionsException(
					"Size Mismatch-  Input: " + "[" + Integer.toString(multiplier.width()) + ","
							+ Integer.toString(multiplier.height()) + "] -- Class Array: " + "["
							+ Integer.toString(array.length) + "," + Integer.toString(array[0].length) + "]");
		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------

	// Returns Transposed Array w/ size (y,x) from Matrix Object size of (x,y)
	public Matrix Transpose() {
		double[][] returnArray = new double[array[0].length][array.length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				returnArray[j][i] = array[i][j];
			}
		}
		return new Matrix(returnArray);
	}

	// ----------------------------------------------------------------------------------------------------------------------------

	// Returns double array in Class
	public double[][] getArray() {
		double[][] returnArray = new double[array.length][array[0].length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				returnArray[i][j] = array[i][j];
			}
		}
		return returnArray;
	}

	// Returns Matrix element at position (x,y)
	public double getArray(int x, int y) {
		try {
			return array[x][y];
		} catch (Exception e) {
			throw new IllegalArgumentException("Size Mismatch-  Input: " + "[" + Integer.toString(x) + ","
					+ Integer.toString(y) + "] -- Class Array: " + "[" + Integer.toString(array.length) + ","
					+ Integer.toString(array[0].length) + "]");
		}
	}
	
	// Sets Matrix element at position (x,y)
	public void setArray(int x, int y, double element) {
		array[x][y] = element;
	}

	// ----------------------------------------------------------------------------------------------------------------------------

	// Returns formated String of Matrix object, with each ordered element and row
	public String toString() {
		String returnString = "\n\n          [";
		for (int i = 0; i < array.length; i++) {
			if (i == 0) {
				returnString += "[";
			} else {
				returnString += "           [";
			}
			for (int j = 0; j < array[0].length - 1; j++) {
				if (array[i][j] >= 10) {
					returnString += Double.toString(array[i][j]) + ", ";
				} else {
					returnString += Double.toString(array[i][j]) + ",  ";
				}
			}
			if (array[i][array[0].length - 1] >= 10) {
				returnString += Double.toString(array[i][array[0].length - 1]) + "],\n";
			} else {
				returnString += Double.toString(array[i][array[0].length - 1]) + " ],\n";
			}
		}

		returnString = returnString.substring(0, returnString.length() - 2) + ']'
				+ returnString.substring(returnString.length() - 1 + 1);

		returnString += "\n\n";

		return returnString;
	}
}
