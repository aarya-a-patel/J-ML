import jml.model.Model;
import jml.trainer.Trainer;

public class ModelTester {
	public static void main(String[] args) {
		int[] nodes = { 2, 1 };
		Model m = new Model(nodes);

		System.out.println(m.toString());

		DataParserTester dp = new DataParserTester();

		Trainer t = new Trainer(m, dp);

		Thread thread = new Thread(t);
		thread.start();

	}
}
