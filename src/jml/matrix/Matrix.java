package jml.matrix;

import jml.matrix.exceptions.InvalidMatrixDimensionsException;

public class Matrix {
	private double[][] array;

	// Default Constructor of empty 1x1 matrix
	public Matrix() {
		array = new double[1][1];
	}

	// Creates empty Matrix with Dimensions of (width, height)
	public Matrix(final int width, final int height) {
		array = new double[width][height];
		
		for (int x = 0; x < this.width(); x++) {
			for (int y = 0; y < this.height(); y++) {
				array[x][y] = 0;
			}
		}
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

	// Returns Matrix width (x)
	public int width() {
		return array.length;
	}

	// Returns Matrix height (y)
	public int height() {
		return array[0].length;
	}

	// Returns a copy of the array inside Matrix class
	public double[][] getArray() {
		return array.clone();
	}

	// -------------------------------------------------------------------------------------------------------------
	// Static functions
	
	
	/** 
	 * Adds two Matrices and returns the sum
	*/
	public static Matrix add(Matrix m1, Matrix m2) throws InvalidMatrixDimensionsException {

		if (m1.width() < m2.width() || m1.height() < m2.height()) {
			throw new InvalidMatrixDimensionsException("Matrix 2 is bigger than Matrix 1 -- Cannot Add");
		}

		double[][] a1 = m1.getArray();
		double[][] a2 = m2.getArray();
		double[][] out = new double[m1.width()][m1.height()];
		
		for (int x = 0; x < m1.width(); x++) {
			for (int y = 0; y < m1.height(); y++) {
				if (x < m2.width() && y < m2.height()) {
					out[x][y] = a1[x][y] + a2[x][y];
				} else {
					out[x][y] = a1[x][y];
				}
			}
		}
		return new Matrix(out);
	}
	
	/** 
	 * Finds the difference between two Matrices and returns the difference
	*/
	public static Matrix subtract(Matrix m1, Matrix m2) throws InvalidMatrixDimensionsException {

		if (m1.width() < m2.width() || m1.height() < m2.height()) {
			throw new InvalidMatrixDimensionsException("Matrix 2 is bigger than Matrix 1 -- Cannot Subtract");
		}

		double[][] a1 = m1.getArray();
		double[][] a2 = m2.getArray();
		double[][] out = new double[m1.width()][m1.height()];
		
		for (int x = 0; x < m1.width(); x++) {
			for (int y = 0; y < m1.height(); y++) {
				if (x < m2.width() && y < m2.height()) {
					out[x][y] = a1[x][y] - a2[x][y];
				} else {
					out[x][y] = a1[x][y];
				}
			}
		}
		return new Matrix(out);
	}
	
	/** 
	 * Finds the product of two Matrices and returns it
	*/
	public static Matrix multiply(Matrix m1, Matrix m2) throws InvalidMatrixDimensionsException {

		if (m1.width() != m2.height()) {
			throw new InvalidMatrixDimensionsException("Matrix 2 is bigger than Matrix 1 -- Cannot Multiply");
		}

		double[][] a1 = m1.getArray();
		double[][] a2 = m2.getArray();
		double[][] out = new double[m2.width()][m1.height()];
		
		for (int x2 = 0; x2 < m2.width(); x2++) {
			for (int x1 = 0; x1 < m1.width(); x1++) {
				for (int y = 0; y < m1.height(); y++) {
					out[x2][y] += a1[x1][y] * a2[x2][x1];
				}
			}
		}

		return new Matrix(out);
	}
}
