import jml.model.Model;

public class ModelTester {
	public static void main(String[] args) {
		int[] nodes = {2,3,3,1};
		Model m = new Model(nodes);
		double[] input = {1,2};
		m.feedForward(input);
		System.out.println(m.toString());
	}
}
