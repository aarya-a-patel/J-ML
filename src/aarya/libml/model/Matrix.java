public class Matrix {
	private double array[][];

	//----------------------------------------------------------------------------------------------------------------------------
	
	// Default Constructor of empty 1x1 matrix
	public Matrix() {
		array = new double[1][1];
	}

	// Creates empty Matrix with Dimensions of (x,y)
	public Matrix(int x, int y) {
		array = new double[x][y];
	}

	/*
	 * Creates Matrix with Dimensions of (x,y) Fills Matrix with random integer from
	 * 0 - range
	 */
	public Matrix(int x, int y, int range) {
		array = new double[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				array[i][j] = (int) (Math.random() * range);
			}
		}
	}

	// Sets Matrix to input array, including data
	public Matrix(double[][] inp) {
		array = inp;
	}

	// Basic Copy constructor
	public Matrix(Matrix m) {
		array = m.getArray();
	}

	//----------------------------------------------------------------------------------------------------------------------------
	
	// Returns Matrix length (x)
	public int length() {
		return array.length;
	}

	// Returns Matrix height (y)
	public int height() {
		return array[0].length;
	}

	//----------------------------------------------------------------------------------------------------------------------------
	
	// Returns scalar addition of each element of Matrix by double addition

	public Matrix Add(double addition) {
		double[][] returnArray = array;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				returnArray[i][j] += addition;
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
	public Matrix Add(double[][] addition) {

		double[][] returnArray = this.getArray();

		try {
			for (int i = 0; i < addition.length; i++) {
				for (int j = 0; j < addition[0].length; j++) {
					returnArray[i][j] += addition[i][j];
				}
			}
			return new Matrix(returnArray);
		} catch (Exception e) {
			System.out.println("Size Mismatch-  Input: " + "[" + Integer.toString(addition.length) + ","
					+ Integer.toString(addition[0].length) + "] -- Class Array: " + "[" + Integer.toString(array.length)
					+ "," + Integer.toString(array[0].length) + "]");
			return null;
		}
	}

	public Matrix Add(Matrix addition) {

		double[][] returnArray = this.getArray();

		try {
			for (int i = 0; i < addition.length(); i++) {
				for (int j = 0; j < addition.height(); j++) {
					returnArray[i][j] += addition.getArray(i, j);
				}
			}
			return new Matrix(returnArray);
		} catch (Exception e) {
			System.out.println("Size Mismatch-  Input: " + "[" + Integer.toString(addition.length())
					+ "," + Integer.toString(addition.height()) + "] -- Class Array: " + "["
					+ Integer.toString(array.length) + "," + Integer.toString(array[0].length) + "]");
			return null;
		}
	}
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	// Returns scalar subtraction of each element of Matrix by double subtraction
	public Matrix Subtract(double subtraction) {
		double[][] returnArray = array;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				returnArray[i][j] -= subtraction;
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
	public Matrix Subtract(double[][] subtraction) {

		double[][] returnArray = this.getArray();

		try {
			for (int i = 0; i < subtraction.length; i++) {
				for (int j = 0; j < subtraction[0].length; j++) {
					returnArray[i][j] -= subtraction[i][j];
				}
			}
			return new Matrix(returnArray);
		} catch (Exception e) {
			System.out.println("Size Mismatch-  Input: " + "[" + Integer.toString(subtraction.length)
					+ "," + Integer.toString(subtraction[0].length) + "] -- Class Array: " + "["
					+ Integer.toString(array.length) + "," + Integer.toString(array[0].length) + "]");
			return null;
		}
	}

	public Matrix Subtract(Matrix subtraction) {

		double[][] returnArray = this.getArray();

		try {
			for (int i = 0; i < subtraction.length(); i++) {
				for (int j = 0; j < subtraction.height(); j++) {
					returnArray[i][j] -= subtraction.getArray(i, j);
				}
			}
			return new Matrix(returnArray);
		} catch (Exception e) {
			System.out.println("Size Mismatch-  Input: " + "[" + Integer.toString(subtraction.length())
					+ "," + Integer.toString(subtraction.height()) + "] -- Class Array: " + "["
					+ Integer.toString(array.length) + "," + Integer.toString(array[0].length) + "]");
			return null;
		}
	}
	
	//----------------------------------------------------------------------------------------------------------------------------

	// Returns scalar multiplication of each element of Matrix by double multiplication
		public Matrix ScalarMultiply(double multiplication) {
			double[][] returnArray = array;
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					returnArray[i][j] *= multiplication;
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
		public Matrix ScalarMultiply(double[][] multiplication) {

			double[][] returnArray = this.getArray();

			try {
				for (int i = 0; i < multiplication.length; i++) {
					for (int j = 0; j < multiplication[0].length; j++) {
						returnArray[i][j] *= multiplication[i][j];
					}
				}
				return new Matrix(returnArray);
			} catch (Exception e) {
				System.out.println("Size Mismatch-  Input: " + "[" + Integer.toString(multiplication.length)
						+ "," + Integer.toString(multiplication[0].length) + "] -- Class Array: " + "["
						+ Integer.toString(array.length) + "," + Integer.toString(array[0].length) + "]");
				return null;
			}
		}
		
		public Matrix ScalarMultiply(Matrix multiplication) {

			double[][] returnArray = this.getArray();

			try {
				for (int i = 0; i < multiplication.length(); i++) {
					for (int j = 0; j < multiplication.height(); j++) {
						returnArray[i][j] *= multiplication.getArray(i, j);
					}
				}
				return new Matrix(returnArray);
			} catch (Exception e) {
				System.out.println("Size Mismatch-  Input: " + "[" + Integer.toString(multiplication.length())
						+ "," + Integer.toString(multiplication.height()) + "] -- Class Array: " + "["
						+ Integer.toString(array.length) + "," + Integer.toString(array[0].length) + "]");
				return null;
			}
		}
	
	//----------------------------------------------------------------------------------------------------------------------------
		
		//Proper Matrix Dot Product Returned
		public Matrix Multiply(Matrix multiply) {
			if(this.height() == multiply.length()) {
				double[][] returnArray = new double[this.length()][multiply.height()];
		        for(int i = 0; i < this.length(); i++) {
		            for (int j = 0; j < multiply.height(); j++) {
		                for (int k = 0; k < this.height(); k++) {
		                	returnArray[i][j] += array[i][k] * multiply.getArray(k,j);
		                }
		            }
		        }
		        return new Matrix(returnArray);
			}
			else{
				System.out.println("Cannot Multiply");
				return null;
			}
		}
	//----------------------------------------------------------------------------------------------------------------------------
		
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

	//----------------------------------------------------------------------------------------------------------------------------
	
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

	// Returns Matrix element as position (x,y)
	public double getArray(int x, int y) {
		try {
			return array[x][y];
		} catch (Exception e) {
			System.out.println("Size Mismatch-  Input: " + "[" + Integer.toString(x) + ","
					+ Integer.toString(y) + "] -- Class Array: " + "[" + Integer.toString(array.length)
					+ "," + Integer.toString(array[0].length) + "]");
			return 0;
		}
	}
	
	//----------------------------------------------------------------------------------------------------------------------------

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
