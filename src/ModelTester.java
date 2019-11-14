import java.util.Arrays;

import jml.model.Model;

public class ModelTester {
	public static void main(String[] args) {
		int[] nodes = { 2, 1 };
		Model m = new Model(nodes);
		double[] input = { 1 , 3 };
		System.out.println(Arrays.toString(m.feedForward(input)));
		input[0] = 2;
		System.out.println(Arrays.toString(m.feedForward(input)));
		input[0] = 3;
		System.out.println(Arrays.toString(m.feedForward(input)));
		System.out.println(m.toString());
	}
}
